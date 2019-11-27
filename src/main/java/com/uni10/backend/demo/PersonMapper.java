package com.uni10.backend.demo;

import lombok.val;

public class PersonMapper {

    public static PersonDTO personDTO(final Person person) {
        return PersonDTO
                .builder()
                .id(person.getId())
                .age(person.getAge())
                .name(person.getName())
                .mother_id(person.getMother().getId())
                .father_id(person.getFather().getId())
                .build();
    }

    public static Person person(final PersonDTO personDTO) {
        final Person person = new Person();
        person.setId(personDTO.getId());
        person.setAge(personDTO.getAge());
        person.setName(personDTO.getName());
        return person;
    }
}
