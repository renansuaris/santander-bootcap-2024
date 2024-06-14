package com.dio.santander_bootcap_2024.controller;

import com.dio.santander_bootcap_2024.model.Player;
import com.dio.santander_bootcap_2024.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> findById(@PathVariable Long id) {
        var player = playerService.findById(id);
        return ResponseEntity.ok(player); // Status: 200 OK
    }

    @GetMapping
    public ResponseEntity<List<Player>> findAll() {
        List<Player> playersList = playerService.findAllPlayers();
        return ResponseEntity.ok(playersList); // Status: 200 OK
    }

    @PostMapping
    public ResponseEntity<Player> create(@RequestBody Player playerToCreate){
        Player playerCreated = playerService.create(playerToCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(playerCreated); // Status: 201 CREATED
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        playerService.deleteById(id);
        return ResponseEntity.noContent().build(); // Status: 204 No Content
    }

    @PutMapping("/{playerId}/assign-club/{clubId}")
    public ResponseEntity<Void> assignClubToPlayer(@PathVariable Long playerId, @PathVariable Long clubId) {
        playerService.assignClubToPlayer(playerId, clubId);
        return ResponseEntity.noContent().build(); // Status: 204 No Content
    }

}
