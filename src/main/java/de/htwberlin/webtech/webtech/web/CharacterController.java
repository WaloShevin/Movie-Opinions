package de.htwberlin.webtech.webtech.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
public class CharacterController {

    @GetMapping(path = "/characters")
    public List<Character> showFantasyWorldPage(){
        return List.of(new Character( "lili", 6 ),
        new Character ( "adam" , 5));
} }