package com.dio.santander_bootcap_2024.controller;

import com.dio.santander_bootcap_2024.model.League;
import com.dio.santander_bootcap_2024.service.LeagueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "League Controler", description = "League controller to perform CRUD operations on leagues")
@RestController
@RequestMapping("/league")
public class LeagueController {

    private final LeagueService leagueService;

    @Autowired
    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @Operation(summary = "Find league by specified ID")
    @GetMapping("/{id}")
    public ResponseEntity<League> findById(@PathVariable int id) {
        return ResponseEntity.ok(leagueService.findLeagueById(id)); // Status: 200 OK
    }

    @Operation(summary = "Get all leagues")
    @GetMapping
    public ResponseEntity<List<League>> findAll(){
        return ResponseEntity.ok(leagueService.findAllLeagues()); // Status: 200 OK
    }

    @Operation(summary = "Create a league")
    @PostMapping
    public ResponseEntity<League> createLeague(@RequestBody League league) {
        var leagueCreated = leagueService.createLeague(league);
        return ResponseEntity.status(HttpStatus.CREATED).body(leagueCreated); // Status: 201 Created
    }

    @Operation(summary = "Delete a league by specified ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeague(@PathVariable long id) {
        leagueService.deleteLeague(id);
        return ResponseEntity.noContent().build(); // Status: 204 No Content
    }

}
