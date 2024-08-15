package com.dio.santander_bootcap_2024.controller.factory;

import com.dio.santander_bootcap_2024.model.League;

import java.util.ArrayList;
import java.util.List;

public class LeagueResponseFactory {

    public static League buildLeague() {
        League league = new League();
        league.setLeagueName("Serie A");
        league.setId(1L);
        league.setLeagueCountry("Italy");
        return league;
    }

    public static List<League> buildLeagueList() {
        List<League> leagueList = new ArrayList<>();
        leagueList.add(buildLeague());
        return leagueList;
    }

}
