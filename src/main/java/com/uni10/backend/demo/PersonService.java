package com.uni10.backend.demo;

import com.uni10.backend.api.PagedRequest;
import com.uni10.backend.specifications.Specifications;
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
        return personRepository.findById(id).map(PersonMapper::personDTO);
    }

    public Page<PersonDTO> findAll(final PersonRequest personRequest) {
        final Pageable pageable = personRequest.toPageable();
        final Specification<Person> specification = personRequest.toSpecification();
        return personRepository.findAll(specification, pageable).map(PersonMapper::personDTO);
    }

    public PersonDTO save(final PersonDTO dto) {
        Person person = PersonMapper.person(dto);
        person = personRepository.save(person);
        return PersonMapper.personDTO(person);
    }

    public PersonDTO update(final PersonDTO dto, final long id) {
        Person person = PersonMapper.person(dto);
        person.setId(id);
        personRepository.save(person);
        return PersonMapper.personDTO(person);
    }

    public void deleteById(final Long id) {
        personRepository.deleteById(id);
    }

}
