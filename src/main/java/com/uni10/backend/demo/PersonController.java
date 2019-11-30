package com.uni10.backend.demo;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "PersonController")
@RestController
@RequestMapping("/persons")
@AllArgsConstructor
public class PersonController {

    private PersonService personService;

    @GetMapping
    @ApiOperation(value = "PersonController.findAll", notes = "Find all Persons")
    public Page<PersonDTO> findAll(PersonRequest personRequest) {
        return personService.findAll(personRequest);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "PersonController.findById", notes = "Find one Person by id")
    public ResponseEntity<PersonDTO> findById(@PathVariable long id) {
        val optional = personService.findById(id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{personId}/mother")
    public ResponseEntity<PersonDTO> findMother(final MotherRequest motherRequest) {
        val optional = personService.findById(motherRequest.getPersonId());
        if (optional.isPresent()) {
            val mother = personService.findById(optional.get().getMotherId());
            if (mother.isPresent()) {
                return ResponseEntity.ok(mother.get());
            } else {
                return ResponseEntity.noContent().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ApiOperation(value = "PersonController.save", notes = "Save a new person")
    public ResponseEntity<PersonDTO> save(@Valid @RequestBody PersonDTO personDTO) {
        return ResponseEntity.ok(personService.save(personDTO));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "PersonController.update", notes = "Update an existent person")
    public ResponseEntity<PersonDTO> update(@Valid @RequestBody PersonDTO personDTO, @PathVariable long id) {
        val optional = personService.update(personDTO, id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "PersonController.deleteById", notes = "Delete an existent person")
    public ResponseEntity deleteById(@PathVariable long id) {
        personService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
