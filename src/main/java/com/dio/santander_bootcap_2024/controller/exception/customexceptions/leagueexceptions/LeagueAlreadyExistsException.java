package com.dio.santander_bootcap_2024.controller.exception.customexceptions.leagueexceptions;

public class LeagueAlreadyExistsException extends RuntimeException {

    public LeagueAlreadyExistsException() {
        super("This league already exists");
    }

}
