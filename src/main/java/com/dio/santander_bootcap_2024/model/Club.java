package com.dio.santander_bootcap_2024.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "tb_club")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String clubName;

    @Column(unique = true, nullable = false)
    private String clubStadium;

    @OneToMany(mappedBy = "club", cascade = CascadeType.REMOVE)
    private List<Player> players;

    @ManyToOne
    private League league;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubStadium() {
        return clubStadium;
    }

    public void setClubStadium(String clubStadium) {
        this.clubStadium = clubStadium;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }
}
