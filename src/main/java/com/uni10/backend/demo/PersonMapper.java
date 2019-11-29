package com.uni10.backend.demo;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PersonMapper {

    public static PersonDTO personDTO(final Person person) {
        return PersonDTO
                .builder()
                .id(person.getId())
                .age(person.getAge())
                .name(person.getName())
                .motherId(person.getMotherId())
                .fatherId(person.getFatherId())
                .build();
    }

    public static Person person(final PersonDTO personDTO) {
        final Person person = new Person();
        person.setId(personDTO.getId());
        person.setAge(personDTO.getAge());
        person.setName(personDTO.getName());
        person.setMotherId(personDTO.getMotherId());
        person.setMotherId(personDTO.getFatherId());
        return person;
    }
}
