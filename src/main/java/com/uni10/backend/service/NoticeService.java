package com.uni10.backend.service;

import com.uni10.backend.api.dto.NoticeDTO;
import com.uni10.backend.api.exceptions.NotFoundException;
import com.uni10.backend.api.requests.NoticeRequest;
import com.uni10.backend.entity.Notice;
import com.uni10.backend.repository.NoticeRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NoticeService {

    private NoticeRepository noticeRepository;

    public Page<NoticeDTO> findAll(final NoticeRequest noticeRequest) {
        final Pageable pageable = noticeRequest.toPageable();
        Specification<Notice> specification = noticeRequest.toSpecification();
        return noticeRepository.findAll(specification, pageable).map(NoticeService::noticeDTO);
    }

    public NoticeDTO save(NoticeDTO noticeDTO) {
        Notice notice = notice(noticeDTO);
        notice = noticeRepository.save(notice);
        return noticeDTO(notice);
    }

    public void deleteById(final long id) {
        if (noticeRepository.existsById(id)) {
            noticeRepository.deleteById(id);
        } else {
            throw new NotFoundException();
        }
    }


    private static NoticeDTO noticeDTO(final Notice notice) {
        return new NoticeDTO()
                .setId(notice.getId())
                .setText(notice.getText())
                .setSubjectId(notice.getSubjectId());
    }

    private static Notice notice(final NoticeDTO noticeDTO) {
        return new Notice()
                .setId(0)
                .setText(noticeDTO.getText())
                .setSubjectId(noticeDTO.getSubjectId());
    }
}
