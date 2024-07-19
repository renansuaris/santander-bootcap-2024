package com.dio.santander_bootcap_2024.exceptions.customexceptions.playerexceptions;

public class PlayerNotFoundException extends RuntimeException {

    public PlayerNotFoundException(Long id) {
        super("Player with id " + id + " was not found");
    }

}
