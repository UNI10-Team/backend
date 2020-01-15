package com.uni10.backend.configuration;

import com.uni10.backend.entity.Attachment;
import com.uni10.backend.entity.Comment;
import com.uni10.backend.entity.Course;
import com.uni10.backend.entity.Role;
import com.uni10.backend.entity.User;
import com.uni10.backend.repository.AttachmentRepository;
import com.uni10.backend.repository.CommentRepository;
import com.uni10.backend.repository.CourseRepository;
import com.uni10.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JPARunner implements CommandLineRunner {

    private CommentRepository commentRepository;
    private AttachmentRepository attachmentRepository;
    private CourseRepository courseRepository;
    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) {
        User user = new User();
        user.setUsername("Alexandru");
        user.setFirstName("Alexandru");
        user.setLastName("Craciun");
        user.setPassword("password");
        user.setEmail(user.getUsername() + "@scs.ubbcluj.ro");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_STUDENT);
        //userRepository.save(user);

        //comments();
        //commentsAttachmentToSubject();

        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_RESET = "\u001B[0m";
        System.out.println();
        System.out.println(ANSI_BLUE);
        System.out.println("De la Silviu:");
        System.out.println("If you want an account for yourself, go in com.uni10.backend.configuration.JPARunner");
        System.out.println("Change the username and password and uncomment //userRepository.save(user);");
        System.out.println(ANSI_RESET);
        System.out.println();
    }

    private void comments() {
        List<User> students = userRepository.findAll().stream().filter(user -> user.getRole().equals(Role.ROLE_STUDENT)).collect(Collectors.toList());
        List<Course> courses = courseRepository.findAll().stream().filter(course -> course.getType().equals("Curs")).collect(Collectors.toList());

        Random random = new Random();
        final String[] texts = {
                "Cea mai faina materie!",
                "Nu am prea inteles acest curs...",
                "Sa va faceti temele din timp, ajuta!",
                "Sa nu uitati sa va faceti prezentele!",
                "Doamne ajuta, inca o materie la care am trecut!"
        };
        byte[] bytes = "Silviu e tare".getBytes();
        for (Course course : courses) {
            final String name = course.getSubject().getName();
            Attachment attachment = new Attachment()
                    .setCourseId(course.getId())
                    .setData(bytes)
                    .setType("text/txt")
                    .setName(name);
            attachmentRepository.save(attachment);
            for (User student : students) {
                final String text = texts[random.nextInt(texts.length)];

                Comment comment = new Comment()
                        .setAttachmentId(attachment.getId())
                        .setUserId(student.getId())
                        .setAccepted(true).setText(text);
                commentRepository.save(comment);
            }
        }
    }

    private void commentsAttachmentToSubject() {

        List<Comment> comments = commentRepository.findAll();

        Map<Long, Attachment> attachmentMap = new HashMap<>();
        Map<Long, Course> courseMap = new HashMap<>();

        for (Comment comment : comments) {
            long attachmentId = comment.getAttachmentId();
            if (!attachmentMap.containsKey(attachmentId)) {
                attachmentMap.put(attachmentId, attachmentRepository.findById(attachmentId).get());
            }

            long courseId = attachmentMap.get(attachmentId).getCourseId();
            if (!courseMap.containsKey(courseId)) {
                courseMap.put(courseId, courseRepository.findById(courseId).get());
            }

            comment.setSubjectId(courseMap.get(courseId).getSubjectId()).setAttachmentId(-1);

        }
        commentRepository.saveAll(comments);
    }
}
