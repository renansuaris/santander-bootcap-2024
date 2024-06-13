package com.dio.santander_bootcap_2024.service;

import com.dio.santander_bootcap_2024.model.Club;

import java.util.List;

public interface ClubService {

    Club findClubById(Long id);

    Club createClub(Club club);

    List<Club> findAllClubs();

    void deleteClubById(Long id);

    void assignLeagueToClub(Long club_id, Long league_id);

}
