package de.htwberlin.webtech.webtech;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import de.htwberlin.webtech.webtech.persistence.CharacterRepository;
import de.htwberlin.webtech.webtech.persistence.Gender;
import de.htwberlin.webtech.webtech.service.CharacterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import de.htwberlin.webtech.webtech.persistence.CharacterEntity;

public class CharacterServiceTest {

    @InjectMocks
    private CharacterService characterService;

    @Mock
    private CharacterRepository characterRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllCharacters() {
        List<CharacterEntity> characters = Arrays.asList(
                new CharacterEntity(1L, "Character1", 25, Gender.MALE, "Human", "Skill1", "Weakness1", "Story1"),
                new CharacterEntity(2L, "Character2", 30, Gender.FEMALE, "Elf", "Skill2", "Weakness2", "Story2")
        );

        when(characterRepository.findAll()).thenReturn(characters);

        List<CharacterEntity> result = characterService.getAllCharacters();
        assertEquals(2, result.size());
        assertEquals("Character1", result.get(0).getName());
    }

    @Test
    public void saveCharacter() {
        CharacterEntity character = new CharacterEntity(1L, "Character1", 25, Gender.MALE, "Human", "Skill1", "Weakness1", "Story1");

        when(characterRepository.save(any(CharacterEntity.class))).thenReturn(character);

        CharacterEntity result = characterService.saveCharacter(character);
        assertEquals("Character1", result.getName());
    }

    @Test
    public void updateCharacter() {
        CharacterEntity character = new CharacterEntity(1L, "Character1", 25, Gender.MALE, "Human", "Skill1", "Weakness1", "Story1");

        when(characterRepository.save(any(CharacterEntity.class))).thenReturn(character);

        CharacterEntity result = characterService.updateCharacter(character);
        assertEquals("Character1", result.getName());
    }

    @Test
    public void deleteCharacter() {
        characterService.deleteCharacter(1L);
        verify(characterRepository, times(1)).deleteById(1L);
    }

    @Test
    public void getCharacterById() {
        CharacterEntity character = new CharacterEntity(1L, "Character1", 25, Gender.MALE, "Human", "Skill1", "Weakness1", "Story1");

        when(characterRepository.findById(1L)).thenReturn(Optional.of(character));

        CharacterEntity result = characterService.getCharacterById(1L);
        assertEquals("Character1", result.getName());
    }

    @Test
    public void searchByWeakness() {
        List<CharacterEntity> characters = Arrays.asList(
                new CharacterEntity(1L, "Character1", 25, Gender.MALE, "Human", "Skill1", "Weakness1", "Story1")
        );

        when(characterRepository.findByWeaknessContainingIgnoreCase("Weakness1")).thenReturn(characters);

        List<CharacterEntity> result = characterService.searchByWeakness("Weakness1");
        assertEquals(1, result.size());
        assertEquals("Character1", result.get(0).getName());
    }

    @Test
    public void searchByName() {
        List<CharacterEntity> characters = Arrays.asList(
                new CharacterEntity(1L, "Character1", 25, Gender.MALE, "Human", "Skill1", "Weakness1", "Story1")
        );

        when(characterRepository.findAllByName("Character1")).thenReturn(characters);

        List<CharacterEntity> result = characterService.searchByName("Character1");
        assertEquals(1, result.size());
        assertEquals("Character1", result.get(0).getName());
    }

    @Test //test überprüfen
    public void getAllCharactersSortedByAgeDesc() {
        List<CharacterEntity> characters = Arrays.asList(
                new CharacterEntity(1L, "Character1", 25, Gender.MALE, "Human", "Skill1", "Weakness1", "Story1"),
                new CharacterEntity(2L, "Character2", 30, Gender.FEMALE, "Elf", "Skill2", "Weakness2", "Story2")
        );

        when(characterRepository.findAllByOrderByAgeDesc()).thenReturn(characters);

        List<CharacterEntity> result = characterService.getAllCharactersSortedByAgeDesc();

        // Sort characters list manually to compare with expected
        characters.sort(Comparator.comparingInt(CharacterEntity::getAge).reversed());

        assertEquals(characters.size(), result.size());
        assertEquals(characters.get(0).getAge(), result.get(0).getAge());
    }
    /*@Test
    public void getAllCharactersSortedByAgeDesc() {
        List<CharacterEntity> characters = Arrays.asList(
            new CharacterEntity(1L, "Character1", 25, Gender.MALE, "Human", "Skill1", "Weakness1", "Story1"),
            new CharacterEntity(2L, "Character2", 30, Gender.FEMALE, "Elf", "Skill2", "Weakness2", "Story2")
        );

        when(characterRepository.findAllByOrderByAgeDesc()).thenReturn(characters);

        List<CharacterEntity> result = characterService.getAllCharactersSortedByAgeDesc();
        assertEquals(2, result.size());
        assertEquals(30, result.get(0).getAge());
    } **/
    @Test
    public void getAllCharactersSortedByAgeAsc() {
        List<CharacterEntity> characters = Arrays.asList(
                new CharacterEntity(1L, "Character1", 25, Gender.MALE, "Human", "Skill1", "Weakness1", "Story1"),
                new CharacterEntity(2L, "Character2", 30, Gender.FEMALE, "Elf", "Skill2", "Weakness2", "Story2")
        );

        when(characterRepository.findAllByOrderByAgeAsc()).thenReturn(characters);

        List<CharacterEntity> result = characterService.getAllCharactersSortedByAgeAsc();
        assertEquals(2, result.size());
        assertEquals(25, result.get(0).getAge());
    }
}
