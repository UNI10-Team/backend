package com.uni10.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/")
public class BaseController {

    @GetMapping("/")
    public String redirectToSwagger(){
        return "redirect:swagger-ui.html";
    }
}
