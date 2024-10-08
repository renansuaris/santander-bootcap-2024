package com.dio.santander_bootcap_2024.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "tb_player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    private int shirtNumber;
    @Column(nullable = false)
    private String height;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String position;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    private Club club;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(int shirtNumber) {
        this.shirtNumber = shirtNumber;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

}
