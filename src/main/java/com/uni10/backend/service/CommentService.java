package com.uni10.backend.service;

import com.uni10.backend.api.dto.CommentDTO;
import com.uni10.backend.api.requests.CommentRequest;
import com.uni10.backend.entity.Attachment;
import com.uni10.backend.entity.Comment;
import com.uni10.backend.repository.AttachmentRepository;
import com.uni10.backend.repository.CommentRepository;
import com.uni10.backend.specifications.Specifications;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
@AllArgsConstructor
public class CommentService {

    private CommentRepository commentRepository;
    private AttachmentRepository attachmentRepository;

    public Page<CommentDTO> findAll(final CommentRequest commentRequest, final long attachmentId) {
        if (attachmentRepository.existsById(attachmentId)) {
            String[] pathToAttachment = {"attachmentId"};
            final Pageable pageable = commentRequest.toPageable();
            final Specification<Comment> specification = Specifications.equal(pathToAttachment, attachmentId);
            return commentRepository.findAll(specification, pageable).map(CommentService::commentDTO);
        } else {
            throw new RuntimeException();
        }
    }

    public Optional<CommentDTO> save(final CommentDTO commentDTO, final long attachmentId) {
        if (attachmentRepository.existsById(attachmentId)) {
            Comment comment = comment(commentDTO, 0);
            comment = commentRepository.save(comment);
            return Optional.of(commentDTO(comment));
        } else {
            return Optional.empty();
        }
    }

    public Optional<CommentDTO> update(final CommentDTO commentDTO, final long id) {
        if (commentRepository.existsById(id)) {
            Comment comment = comment(commentDTO, id);
            comment = commentRepository.save(comment);
            return Optional.of(commentDTO(comment));
        } else {
            return Optional.empty();
        }
    }

    public void deleteById(final Long id) {
        commentRepository.deleteById(id);
    }

    private static CommentDTO commentDTO(final Comment comment) {
        return new CommentDTO()
                .setId(comment.getId())
                .setText(comment.getText())
                .setUserId(comment.getUserId())
                .setAttachmentId(comment.getAttachmentId());
    }

    private static Comment comment(final CommentDTO commentDTO, final long id) {
        return new Comment()
                .setId(id)
                .setText(commentDTO.getText())
                .setUserId(commentDTO.getUserId())
                .setAttachmentId(commentDTO.getAttachmentId());
    }
}
