package com.dio.santander_bootcap_2024.controller.exception;

import com.dio.santander_bootcap_2024.controller.exception.customexceptions.clubexceptions.ClubAlreadyExistsException;
import com.dio.santander_bootcap_2024.controller.exception.customexceptions.clubexceptions.ClubNotFoundException;
import com.dio.santander_bootcap_2024.controller.exception.customexceptions.leagueexceptions.LeagueAlreadyExistsException;
import com.dio.santander_bootcap_2024.controller.exception.customexceptions.leagueexceptions.LeagueNotFoundException;
import com.dio.santander_bootcap_2024.controller.exception.customexceptions.playerexceptions.PlayerAlreadyExistsException;
import com.dio.santander_bootcap_2024.controller.exception.customexceptions.playerexceptions.PlayerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Player Custom Exceptions
    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<String> handlePlayerNotFoundException(PlayerNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PlayerAlreadyExistsException.class)
    public ResponseEntity<String> handlePlayerAlreadyExistsException(PlayerAlreadyExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    // Club Custom Exceptions
    @ExceptionHandler(ClubNotFoundException.class)
    public ResponseEntity<String> handleClubNotFoundException(ClubNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClubAlreadyExistsException.class)
    public ResponseEntity<String> handleClubAlreadyExistsException(ClubAlreadyExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    // League Custom Exceptions
    @ExceptionHandler(LeagueNotFoundException.class)
    public ResponseEntity<String> handleLeagueNotFoundException(LeagueNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LeagueAlreadyExistsException.class)
    public ResponseEntity<String> handleLeagueAlreadyExistsException(LeagueAlreadyExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    // Unexpected Server Exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnexpectedException(Throwable e) {
        return new ResponseEntity<>("Unexpected Server Error.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
