package com.dio.santander_bootcap_2024.exceptions.customexceptions.clubexceptions;

public class ClubNotFoundException extends RuntimeException {

    public ClubNotFoundException(Long id) {
        super("Club with id " + id + " was not found");
    }
}
