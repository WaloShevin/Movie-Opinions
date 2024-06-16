package de.htwberlin.webtech.webtech.service;

import de.htwberlin.webtech.webtech.persistence.CharacterEntity;
import de.htwberlin.webtech.webtech.persistence.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    public List<CharacterEntity> getAllCharacters() {
        return characterRepository.findAll();
    }

    public CharacterEntity saveCharacter(CharacterEntity character) {
        return characterRepository.save(character);
    }

    public void deleteCharacter(Long id) {
        characterRepository.deleteById(id);
    }

    public CharacterEntity getCharacterById(Long id) {
        return characterRepository.findById(id).orElse(null);
    }
}
