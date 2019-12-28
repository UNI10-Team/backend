package com.uni10.backend.configuration;

import com.uni10.backend.entity.Course;
import com.uni10.backend.entity.Role;
import com.uni10.backend.entity.Schedule;
import com.uni10.backend.entity.Subject;
import com.uni10.backend.entity.User;
import com.uni10.backend.repository.CourseRepository;
import com.uni10.backend.repository.ScheduleRepository;
import com.uni10.backend.repository.SubjectRepository;
import com.uni10.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Silviu-Mihnea Cucuiet
 * @since 30-Sep-19
 * All rights reserved
 */

/*
// @Component
@AllArgsConstructor
public class HttpParserRunner implements CommandLineRunner {

    private static final String I3_URL = "http://www.cs.ubbcluj.ro/files/orar/2019-1/tabelar/I3.html";
    private static final String IE3_URL = "http://www.cs.ubbcluj.ro/files/orar/2019-1/tabelar/IE3.html";

    private static final String FILE = "C:\\Users\\cucuis\\personal\\university\\pc\\backend\\src\\main\\resources\\subjects.csv";

    private UserRepository userRepository;
    private CourseRepository courseRepository;
    private SubjectRepository subjectRepository;
    private ScheduleRepository scheduleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Data
    @Accessors(chain = true)
    private static class _Subject {
        String teacher;
        String name;
        String type;
        String room;
        String day;
        String time;

        // not used
        String frequency;
        String format;
    }

    @Override
    public void run(String... args) throws Exception {
        users();
        System.out.println("USERS");
        System.out.println("USERS");
        System.out.println("USERS");
        System.out.println("USERS");
        System.out.println("USERS");
        subjects();
        System.out.println("SUBJECTS");
        System.out.println("SUBJECTS");
        System.out.println("SUBJECTS");
        System.out.println("SUBJECTS");
        System.out.println("SUBJECTS");
        courses();
        System.out.println("COURSES");
        System.out.println("COURSES");
        System.out.println("COURSES");
        System.out.println("COURSES");
        System.out.println("COURSES");
        schedules();
        System.out.println("SCHEDULES");
        System.out.println("SCHEDULES");
        System.out.println("SCHEDULES");
        System.out.println("SCHEDULES");
        System.out.println("SCHEDULES");
    }

    private List<_Subject> _subjects() {
        List<_Subject> subjects = new LinkedList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] args = line.split(",");
                _Subject subject = new _Subject()
                        .setDay(args[0].replace(" ", ""))
                        .setTime(args[1].replace(" ", ""))
                        .setFrequency(args[2].replace(" ", ""))
                        .setRoom(args[3].replace(" ", ""))
                        .setFormat(args[4].replace(" ", ""))
                        .setType(args[5].replace(" ", ""))
                        .setName(args[6])
                        .setTeacher(args[7]);
                subjects.add(subject);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    private void users() {
        List<_Subject> subjects = _subjects();
        List<User> subjectTeachers = subjects.stream()
                .filter(subject -> subject.getType().equalsIgnoreCase("Curs"))
                .map(_Subject::getTeacher)
                .distinct()
                .map(teacher -> createUser(teacher, Role.ROLE_SUBJECT_TEACHER))
                .collect(Collectors.toList());

        userRepository.save(new User()
                .setFirstName("UNI10")
                .setLastName("TEAM")
                .setUsername("uni10")
                .setEmail("uni10@scs.ubbcluj.ro")
                .setRole(Role.ROLE_ADMIN)
                .setPassword(bCryptPasswordEncoder.encode("pass")));
        userRepository.saveAll(subjectTeachers);
        List<User> courseTeachers = subjects.stream()
                .filter(subject -> !subject.getType().equals("Curs"))
                .filter(subject -> (userRepository.findByUsername(subject.getTeacher()) == null))
                .map(_Subject::getTeacher)
                .distinct()
                .map(teacher -> createUser(teacher, Role.ROLE_COURSE_TEACHER))
                .collect(Collectors.toList());
        userRepository.saveAll(courseTeachers);
    }

    private void subjects() {
        List<Subject> subjects = _subjects().stream()
                .filter(subject -> subject.getType().equalsIgnoreCase("Curs"))
                .map(_subject -> new Subject().setName(_subject.getName()).setTeacherId(userRepository.findByUsername(_subject.getTeacher()).getId()))
                .collect(Collectors.toList());
        subjectRepository.saveAll(subjects);

    }

    private void courses() {
        List<Course> courses = _subjects().stream()
                .map(_subject -> new Course().setType(_subject.getType()).setSubjectId(subject(_subject.getName()).getId()))
                .distinct()
                .collect(Collectors.toList());
        courseRepository.saveAll(courses);

    }

    private void schedules() {
        List<Schedule> schedules = _subjects().stream()
                .map(this::schedule)
                .collect(Collectors.toList());
        scheduleRepository.saveAll(schedules);

    }

    private Schedule schedule(_Subject subject){
        Time from = Time.valueOf(subject.getTime().substring(0, 2) + ":00:00");
        Time to = Time.valueOf(subject.getTime().substring(3) + ":00:00");
        return new Schedule()
                .setDay(subject.getDay())
                .setRoom(subject.getRoom())
                .setTeacherId(userRepository.findByUsername(subject.getTeacher()).getId())
                .setCourseId(course(subject.getType(), subject.getName()).getId())
                .setStartAt(from)
                .setEndAt(to);
    }

    private Subject subject(String name) {
        return subjectRepository.findAll()
                .stream()
                .filter(subject -> subject.getName().equals(name))
                .findFirst().get();
    }

    private Course course(String type, String subjectName) {
        return courseRepository.findAll()
                .stream()
                .filter(course -> course.getType().equals(type) && course.getSubject().getName().equals(subjectName))
                .findFirst().get();
    }


    private void writeToFiles() throws Exception {
        List<_Subject> subjects = get_Subjects(I3_URL);
        subjects.addAll(get_Subjects(IE3_URL));

        subjects = subjects.stream()
                .filter(subject -> !subject.getName().equals("Practica pedagogica observativa"))
                .filter(subject -> !subject.getName().equals("Proiect colectiv"))
                .collect(Collectors.toList());
        create(FILE);
        System.out.println(subjects);
        System.out.println(subjects.size());
        for (_Subject subject : subjects) {
            writeInCSV(FILE, subject.getDay(),
                    subject.getTime(),
                    subject.getFrequency(),
                    subject.getRoom(),
                    subject.getFormat(),
                    subject.getType(),
                    subject.getName(),
                    subject.getTeacher());
        }

    }


    public static void writeInCSV(String filename, Object... cells) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
        writer.write(cells[0].toString());
        for (int i = 1; i < cells.length; i++) {
            writer.write("," + cells[i].toString());
        }
        writer.newLine();
        writer.close();
    }

    public static void create(String filename) throws Exception {
        new BufferedWriter(new FileWriter(filename)).close();
    }

    private List<_Subject> get_Subjects(String url) throws IOException {
        LinkedList<_Subject> subjects = new LinkedList<>();
        Document doc = Jsoup.connect(url).get();
        Elements tables = doc.getElementsByTag("table");
        for (Element table : tables) {
            Elements rows = table.getElementsByTag("tr");
            for (Element row : rows) {
                Elements data = row.getElementsByTag("td");
                if (data.size() != 0) {
                    _Subject subject = new _Subject()
                            .setDay(data.get(0).text())
                            .setTime(data.get(1).text())
                            .setFrequency(data.get(2).text())
                            .setRoom(data.get(3).text())
                            .setFormat(data.get(4).text())
                            .setType(data.get(5).text())
                            .setName(data.get(6).text())
                            .setTeacher(data.get(7).text());
                    if (subject.getTime().length() == 4) {
                        subject.setTime("0" + subject.getTime());
                    }

                    subject.setTeacher(subject.getTeacher().substring(subject.getTeacher().lastIndexOf('.') + 2));

                    if (!subjects.contains(subject)) {
                        subjects.add(subject);
                    }
                }
            }
        }
        return subjects;
    }

    private User createUser(final String fullName, final Role role) {
        String[] names = fullName.split(" ");
        String lastName = names[0];
        String firstName = names[1];
        String email = firstName + "." + lastName + "@scs.ubbcluj.ro";
        return new User()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUsername(fullName)
                .setPassword(bCryptPasswordEncoder.encode("pass"))
                .setEmail(email)
                .setRole(role);
    }
}


 */
