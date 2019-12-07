package com.uni10.backend.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@ApiModel
@Data
@Accessors(chain = true)
public class NotificationDTO {

    private long id;

    private String text;

    private long userId;

    private boolean opened;

    private LocalDateTime created;

}
