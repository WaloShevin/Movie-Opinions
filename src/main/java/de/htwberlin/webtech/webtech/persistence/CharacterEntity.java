package de.htwberlin.webtech.webtech.persistence;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import javax.annotation.processing.Generated;

@Entity(name = "characters")
public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    @NonNull
    private String species;
    @Column(nullable = false)
    @NonNull
    private String significantSkill;
    @Column(nullable = false)
    @NonNull
    private String weakness;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NonNull
    private String story;



    public CharacterEntity( Long id, String name, int age, Gender gender, String species,String significantSkill, String weakness, String story ) {
        this.id= id;
        this.name = name;
        this.age=age;
        this.gender =gender;
        this.species = species;
        this.significantSkill = significantSkill;
        this.weakness = weakness;
        this.story = story;


    }

    public CharacterEntity() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @NonNull
    public String getSpecies() {
        return species;
    }

    public void setSpecies(@NonNull String species) {
        this.species = species;
    }

    @NonNull
    public String getSignificantSkill() {
        return significantSkill;
    }

    public void setSignificantSkill(@NonNull String significantSkill) {
        this.significantSkill = significantSkill;
    }

    @NonNull
    public String getWeakness() {
        return weakness;
    }

    public void setWeakness(@NonNull String weakness) {
        this.weakness = weakness;
    }

    @NonNull
    public String getStory() {
        return story;
    }

    public void setStory(@NonNull String story) {
        this.story = story;
    }
}
