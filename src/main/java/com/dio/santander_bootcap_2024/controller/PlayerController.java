package com.dio.santander_bootcap_2024.controller;

import com.dio.santander_bootcap_2024.model.Player;
import com.dio.santander_bootcap_2024.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Player Controler", description = "Player controller to perform CRUD operations on players")
@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Operation(summary = "Get player by specified ID")
    @GetMapping("/{id}")
    public ResponseEntity<Player> findById(@PathVariable Long id) {
        var player = playerService.findById(id);
        return ResponseEntity.ok(player); // Status: 200 OK
    }

    @Operation(summary = "Get all players")
    @GetMapping
    public ResponseEntity<List<Player>> findAll() {
        List<Player> playersList = playerService.findAllPlayers();
        return ResponseEntity.ok(playersList); // Status: 200 OK
    }

    @Operation(summary = "Create a player")
    @PostMapping
    public ResponseEntity<Player> create(@RequestBody Player playerToCreate){
        Player playerCreated = playerService.create(playerToCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(playerCreated); // Status: 201 CREATED
    }

    @Operation(summary = "Delete a player by specified ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        playerService.deleteById(id);
        return ResponseEntity.noContent().build(); // Status: 204 No Content
    }

    @Operation(summary = "Assign a club to a player")
    @PutMapping("/{playerId}/assign-club/{clubId}")
    public ResponseEntity<Void> assignClubToPlayer(@PathVariable Long playerId, @PathVariable Long clubId) {
        playerService.assignClubToPlayer(playerId, clubId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
