package com.uni10.backend.demo;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@ApiModel
public class PersonDTO {

    private long id;
    private String name;
    private int age;
    private long mother_id;
    private long father_id;
}
