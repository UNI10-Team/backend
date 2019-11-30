package com.uni10.backend.demo;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "FatherController")
@RestController
@RequestMapping("/persons/{personId}/father")
@AllArgsConstructor
public class FatherController {

    private PersonService personService;

    @GetMapping
    public ResponseEntity<PersonDTO> findFather(final FatherRequest fatherRequest) {
        val optional = personService.findById(fatherRequest.getPersonId());
        if (optional.isPresent()) {
            val father = personService.findById(optional.get().getFatherId());
            if (father.isPresent()) {
                return ResponseEntity.ok(father.get());
            } else {
                return ResponseEntity.noContent().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
