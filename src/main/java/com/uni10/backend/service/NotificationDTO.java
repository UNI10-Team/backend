package com.uni10.backend.service;

import io.swagger.annotations.ApiModel;

@Getter
@Builder
@ApiModel
public class NotificationDTO {
    private String text;
    private long userId;
}
