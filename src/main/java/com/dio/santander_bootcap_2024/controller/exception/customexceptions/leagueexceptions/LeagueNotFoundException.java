package com.dio.santander_bootcap_2024.controller.exception.customexceptions.leagueexceptions;

public class LeagueNotFoundException extends RuntimeException {

    public LeagueNotFoundException(Long id) {
        super("League with id " + id + " was not found");
    }
}
