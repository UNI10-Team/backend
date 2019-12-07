package com.uni10.backend.api.requests;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class AttachmentRequest extends PagedRequest {

    @DateTimeFormat(pattern = "MMddyyyy")
    private Date date;

    @DateTimeFormat(pattern = "hh:mm:ss")
    private LocalDateTime time;
}
