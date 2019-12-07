package com.uni10.backend.demo;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonService {

    private PersonRepository personRepository;

    public Optional<PersonDTO> findById(final Long id) {
        return personRepository.findById(id).map(PersonService::personDTO);
    }

    public Page<PersonDTO> findAll(final PersonRequest personRequest) {
        final Pageable pageable = personRequest.toPageable();
        final Specification<Person> specification = personRequest.toSpecification();
        return personRepository.findAll(specification, pageable).map(PersonService::personDTO);
    }

    public PersonDTO save(final PersonDTO dto) {
        Person person = person(dto, 0);
        person = personRepository.save(person);
        return personDTO(person);
    }

    public Optional<PersonDTO> update(final PersonDTO dto, final long id) {
        if (personRepository.existsById(id)) {
            Person person = person(dto, id);
            person = personRepository.save(person);
            return Optional.of(personDTO(person));
        } else {
            return Optional.empty();
        }
    }

    public void deleteById(final Long id) {
        personRepository.deleteById(id);
    }

    private static PersonDTO personDTO(final Person person) {
        return new PersonDTO()
                .setId(person.getId())
                .setAge(person.getAge())
                .setName(person.getName())
                .setMotherId(person.getMotherId())
                .setFatherId(person.getFatherId());
    }

    private static Person person(final PersonDTO personDTO, final long id) {
        return new Person()
                .setId(id)
                .setAge(personDTO.getAge())
                .setName(personDTO.getName())
                .setMotherId(personDTO.getMotherId())
                .setFatherId(personDTO.getFatherId());
    }
}
