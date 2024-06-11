package de.htwberlin.webtech.webtech.persistence;

import jakarta.persistence.*;

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
    private String species;
    private String significantSkill;
    private String weakness;


    public CharacterEntity( Long id, String name, int age, Gender gender, String species,String significantSkill, String weakness ) {
        this.id= id;
        this.name = name;
        this.age=age;
        this.gender =gender;
        this.species = species;
        this.significantSkill = significantSkill;
        this.weakness = weakness;



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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getSignificantSkill() {
        return significantSkill;
    }

    public void setSignificantSkill(String significantSkill) {
        this.significantSkill = significantSkill;
    }

    public String getWeakness() {
        return weakness;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }
}
