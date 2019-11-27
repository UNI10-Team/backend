package com.uni10.backend.demo;


import com.uni10.backend.controller.Filter;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
@AllArgsConstructor
public class PersonController {

    private PersonService personService;

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> findById(@PathVariable long id) {
        val optional = personService.findById(id);
        if(optional.isPresent()){
            return ResponseEntity.ok(optional.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public PersonDTO save(@RequestBody PersonDTO personDTO) {
        return personService.save(personDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable long id) {
        personService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public Page<PersonDTO> findAll(@RequestParam(required = false) MultiValueMap<String, String> parameters) {
        return personService.findAll(new Filter(parameters));
    }
}
