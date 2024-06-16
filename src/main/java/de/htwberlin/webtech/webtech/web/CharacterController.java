package de.htwberlin.webtech.webtech.web;

import de.htwberlin.webtech.webtech.persistence.CharacterEntity;
import de.htwberlin.webtech.webtech.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping
    public ResponseEntity<List<CharacterEntity>> fetchCharacters() {
        List<CharacterEntity> characters = characterService.getAllCharacters();
        return ResponseEntity.ok(characters);
    }

    @PostMapping
    public ResponseEntity<CharacterEntity> createCharacter(@RequestBody CharacterEntity character) {
        CharacterEntity savedCharacter = characterService.saveCharacter(character);
        return ResponseEntity.ok(savedCharacter);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {
        characterService.deleteCharacter(id);
        return ResponseEntity.noContent().build();
    }
}
