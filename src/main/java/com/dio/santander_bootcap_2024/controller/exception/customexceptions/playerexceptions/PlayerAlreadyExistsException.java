package com.dio.santander_bootcap_2024.controller.exception.customexceptions.playerexceptions;

public class PlayerAlreadyExistsException extends RuntimeException {

    public PlayerAlreadyExistsException() {
        super("This player already exists");
    }
}
