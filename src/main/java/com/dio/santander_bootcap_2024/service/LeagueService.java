package com.dio.santander_bootcap_2024.service;


import com.dio.santander_bootcap_2024.model.League;

import java.util.List;

public interface LeagueService {

    League findLeagueById(long id);

    List<League> findAllLeagues();

    League createLeague(League league);

    void deleteLeague(Long id);

}
