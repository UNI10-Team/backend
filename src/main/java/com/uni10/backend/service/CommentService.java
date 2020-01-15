package com.uni10.backend.service;

import com.uni10.backend.api.dto.CommentDTO;
import com.uni10.backend.api.exceptions.NotFoundException;
import com.uni10.backend.api.requests.CommentRequest;
import com.uni10.backend.entity.Comment;
import com.uni10.backend.repository.CommentRepository;
import com.uni10.backend.security.SecurityService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommentService {

    private CommentRepository commentRepository;

    public Page<CommentDTO> findAll(final CommentRequest commentRequest) {
        final Pageable pageable = commentRequest.toPageable();
        Specification<Comment> specification = commentRequest.toSpecification();
        return commentRepository.findAll(specification, pageable).map(CommentService::commentDTO);
    }

    public CommentDTO save(CommentDTO commentDTO) {
        Comment comment = comment(commentDTO);
        comment.setAccepted(false);
        comment = commentRepository.save(comment);
        return commentDTO.setId(comment.getId()).setUsername(SecurityService.getCurrentUser().getUsername());
    }

    public CommentDTO update(final CommentDTO commentDTO, final long id) {
        val optional = commentRepository.findById(id);
        Comment comment = comment(optional.orElseThrow(NotFoundException::new), commentDTO);
        comment = commentRepository.save(comment);
        return commentDTO(comment);
    }

    public void deleteById(final long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
        } else {
            throw new NotFoundException();
        }
    }


    private static CommentDTO commentDTO(final Comment comment) {
        return new CommentDTO()
                .setId(comment.getId())
                .setText(comment.getText())
                .setUserId(comment.getUserId())
                .setUsername(comment.getUser().getUsername())
                .setAttachmentId(comment.getAttachmentId())
                .setSubjectId(comment.getSubjectId())
                .setAccepted(comment.isAccepted());
    }

    private static Comment comment(final CommentDTO commentDTO) {
        return new Comment()
                .setId(0)
                .setText(commentDTO.getText())
                .setUserId(commentDTO.getUserId())
                .setAttachmentId(commentDTO.getAttachmentId())
                .setSubjectId(commentDTO.getSubjectId())
                .setAccepted(commentDTO.isAccepted());
    }

    private static Comment comment(final Comment comment, final CommentDTO commentDTO) {
        return comment
                .setAccepted(commentDTO.isAccepted())
                .setText(commentDTO.getText());
    }
}
