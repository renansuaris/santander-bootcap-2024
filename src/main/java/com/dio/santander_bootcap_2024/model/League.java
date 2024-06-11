package com.dio.santander_bootcap_2024.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "tb_league")
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String leagueName;

    @Column(nullable = false)
    private String leagueCountry;

    @OneToMany(mappedBy = "league", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Club> teams;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getLeagueCountry() {
        return leagueCountry;
    }

    public void setLeagueCountry(String leagueCountry) {
        this.leagueCountry = leagueCountry;
    }

    public List<Club> getTeams() {
        return teams;
    }

    public void setTeams(List<Club> teams) {
        this.teams = teams;
    }
}
