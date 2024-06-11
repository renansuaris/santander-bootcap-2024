package com.dio.santander_bootcap_2024.controller;

import com.dio.santander_bootcap_2024.model.Player;
import com.dio.santander_bootcap_2024.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> findById(@PathVariable Long id) {
        var player = playerService.findById(id);
        return ResponseEntity.ok(player);
    }

    @GetMapping
    public ResponseEntity<List<Player>> findAll() {
        List<Player> playersList = playerService.findAllPlayers();
        return ResponseEntity.ok(playersList);
    }

    @PostMapping
    public ResponseEntity<Player> create(@RequestBody Player playerToCreate){
        Player playerCreated = playerService.create(playerToCreate);
        return ResponseEntity.ok(playerCreated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        playerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{playerId}/assign-club/{clubId}")
    public ResponseEntity<Void> assignClubToPlayer(@PathVariable Long playerId, @PathVariable Long clubId) {
        playerService.assignClubToPlayer(playerId, clubId);
        return ResponseEntity.noContent().build();
    }

}
