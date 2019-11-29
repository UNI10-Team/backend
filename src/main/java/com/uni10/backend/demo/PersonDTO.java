package com.uni10.backend.demo;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ApiModel
@Builder
@Getter
@ToString
@EqualsAndHashCode
@ValidPerson
public class PersonDTO {

    private long id;

    private String name;

    private int age;

    private long motherId;
    private long fatherId;
}
