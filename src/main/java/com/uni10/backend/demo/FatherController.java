package com.uni10.backend.demo;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Api(value = "FatherController")
@RestController
@RequestMapping("/persons/{personId}/father")
@AllArgsConstructor
public class FatherController {

    private PersonService personService;

    @GetMapping
    public ResponseEntity<PersonDTO> findFather(@PathVariable(name = "personId") final long personId) {
        val optional = personService.findById(personId);
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
