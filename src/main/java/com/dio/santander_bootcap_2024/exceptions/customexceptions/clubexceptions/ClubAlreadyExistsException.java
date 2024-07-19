package com.dio.santander_bootcap_2024.exceptions.customexceptions.clubexceptions;

public class ClubAlreadyExistsException extends RuntimeException {

    public ClubAlreadyExistsException() {
        super("This club already exists");
    }
}
