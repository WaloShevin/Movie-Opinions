package de.htwberlin.webtech.webtech;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import de.htwberlin.webtech.webtech.persistence.CharacterEntity;
import de.htwberlin.webtech.webtech.persistence.Gender;
import de.htwberlin.webtech.webtech.service.CharacterService;
import de.htwberlin.webtech.webtech.web.CharacterController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CharacterController.class)
public class CharacterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CharacterService characterService;

    @Test
    public void fetchCharacters() throws Exception {
        List<CharacterEntity> characters = Arrays.asList(
                new CharacterEntity(1L, "Character1", 25, Gender.MALE, "Human", "Skill1", "Weakness1", "Story1"),
                new CharacterEntity(2L, "Character2", 30, Gender.FEMALE, "Elf", "Skill2", "Weakness2", "Story2")
        );

        when(characterService.getAllCharacters()).thenReturn(characters);

        mockMvc.perform(get("/characters"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Character1"))
                .andExpect(jsonPath("$[1].name").value("Character2"));
    }

    @Test
    public void createCharacter() throws Exception {
        CharacterEntity character = new CharacterEntity(1L, "Character1", 25, Gender.MALE, "Human", "Skill1", "Weakness1", "Story1");

        when(characterService.saveCharacter(any(CharacterEntity.class))).thenReturn(character);

        mockMvc.perform(post("/characters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(character)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Character1"));
    }

    @Test
    public void deleteCharacter() throws Exception {
        mockMvc.perform(delete("/characters/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void updateCharacter() throws Exception {
        CharacterEntity existingCharacter = new CharacterEntity(1L, "Character1", 25, Gender.MALE, "Human", "Skill1", "Weakness1", "Story1");
        CharacterEntity updatedCharacter = new CharacterEntity(1L, "UpdatedCharacter", 26, Gender.MALE, "Human", "Skill1", "Weakness1", "Story1");

        when(characterService.getCharacterById(1L)).thenReturn(existingCharacter);
        when(characterService.saveCharacter(any(CharacterEntity.class))).thenReturn(updatedCharacter);

        mockMvc.perform(put("/characters/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedCharacter)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("UpdatedCharacter"))
                .andExpect(jsonPath("$.age").value(26));
    }

    @Test
    public void searchCharactersByName() throws Exception {
        List<CharacterEntity> characters = Arrays.asList(
                new CharacterEntity(1L, "Character1", 25, Gender.MALE, "Human", "Skill1", "Weakness1", "Story1")
        );

        when(characterService.searchByName("Character1")).thenReturn(characters);

        mockMvc.perform(get("/characters/searchByName").param("name", "Character1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Character1"));
    }

    @Test
    public void searchCharactersByWeakness() throws Exception {
        List<CharacterEntity> characters = Arrays.asList(
                new CharacterEntity(1L, "Character1", 25, Gender.MALE, "Human", "Skill1", "Weakness1", "Story1")
        );

        when(characterService.searchByWeakness("Weakness1")).thenReturn(characters);

        mockMvc.perform(get("/characters/searchByWeakness").param("weakness", "Weakness1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].weakness").value("Weakness1"));
    }

    @Test
    public void fetchCharactersSortedByAge() throws Exception {
        List<CharacterEntity> characters = Arrays.asList(
                new CharacterEntity(1L, "Character1", 25, Gender.MALE, "Human", "Skill1", "Weakness1", "Story1"),
                new CharacterEntity(2L, "Character2", 30, Gender.FEMALE, "Elf", "Skill2", "Weakness2", "Story2")
        );

        when(characterService.getAllCharactersSortedByAgeAsc()).thenReturn(characters);

        mockMvc.perform(get("/characters/sort").param("sortOrder", "asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].age").value(25))
                .andExpect(jsonPath("$[1].age").value(30));
    }
}
