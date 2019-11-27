package com.uni10.backend.demo;

import com.uni10.backend.controller.Filter;
import lombok.AllArgsConstructor;
import lombok.val;
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
        return personRepository.findById(id).map(PersonMapper::personDTO);
    }

    public PersonDTO save(final PersonDTO dto) {
        Person person = PersonMapper.person(dto);
        personRepository.save(person);
        return PersonMapper.personDTO(person);
    }

    public void deleteById(final Long id) {
        personRepository.deleteById(id);
    }

    public Page<PersonDTO> findAll(final Filter filter) {
        final Pageable pageable = filter.extractPage();
        final Specification<Person> specification = filter.extractFilter();
        return personRepository.findAll(specification, pageable).map(PersonMapper::personDTO);
    }

}
