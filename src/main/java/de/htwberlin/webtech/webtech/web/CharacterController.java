package de.htwberlin.webtech.webtech.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@RestController
public class CharacterController {

  //  private final CharacterService characterService;

   // public CharacterController(CharacterService characterService) {
      //  this.characterService = characterService;
 //   }
    private List<Character> characters;

    public CharacterController() {
        characters = new ArrayList<>();
        characters.add(new Character(1L, "lili", 300, "female", "vampire", "dark magic","sun light"));
        characters.add(new Character(2L, "adam", 30, "male", "elf", "healing ", "ice magic" ));
    }

    @GetMapping(path = "/characters")
    public ResponseEntity<List<Character>> fetchCharacters() {

        return ResponseEntity.ok(characters);
    }
}
