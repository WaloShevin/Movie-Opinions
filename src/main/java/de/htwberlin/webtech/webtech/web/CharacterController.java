package de.htwberlin.webtech.webtech.web;

import de.htwberlin.webtech.webtech.persistence.CharacterEntity;
import de.htwberlin.webtech.webtech.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
@CrossOrigin(origins = "http://localhost:3000")
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

    @PutMapping("/{id}")
    public ResponseEntity<CharacterEntity> updateCharacter(@PathVariable Long id, @RequestBody CharacterEntity updatedCharacter) {
        CharacterEntity existingCharacter = characterService.getCharacterById(id);
        if (existingCharacter == null) {
            return ResponseEntity.notFound().build();
        }
        existingCharacter.setName(updatedCharacter.getName());
        existingCharacter.setAge(updatedCharacter.getAge());
        existingCharacter.setGender(updatedCharacter.getGender());
        existingCharacter.setSpecies(updatedCharacter.getSpecies());
        existingCharacter.setSignificantSkill(updatedCharacter.getSignificantSkill());
        existingCharacter.setWeakness(updatedCharacter.getWeakness());
        existingCharacter.setStory(updatedCharacter.getStory());

        CharacterEntity updated = characterService.saveCharacter(existingCharacter);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/searchByName")
    public ResponseEntity<List<CharacterEntity>> searchCharactersByName(@RequestParam("name") String name) {
        List<CharacterEntity> characters = characterService.searchByName(name);
        return ResponseEntity.ok(characters);
    }

    @GetMapping("/searchByWeakness")
    public ResponseEntity<List<CharacterEntity>> searchCharactersByWeakness(@RequestParam("weakness") String weakness) {
        List<CharacterEntity> characters = characterService.searchByWeakness(weakness);
        return ResponseEntity.ok(characters);
    }

    @GetMapping("/sort")
    public ResponseEntity<List<CharacterEntity>> fetchCharactersSortedByAge(@RequestParam("sortOrder") String sortOrder) {
        List<CharacterEntity> characters;
        if ("asc".equalsIgnoreCase(sortOrder)) {
            characters = characterService.getAllCharactersSortedByAgeAsc();
        } else {
            characters = characterService.getAllCharactersSortedByAgeDesc();
        }
        return ResponseEntity.ok(characters);
    }
}
