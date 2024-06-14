package com.dio.santander_bootcap_2024.controller;

import com.dio.santander_bootcap_2024.model.League;
import com.dio.santander_bootcap_2024.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/league")
public class LeagueController {

    private final LeagueService leagueService;

    @Autowired
    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<League> findById(@PathVariable int id) {
        return ResponseEntity.ok(leagueService.findLeagueById(id)); // Status: 200 OK
    }

    @GetMapping
    public ResponseEntity<List<League>> findAll(){
        return ResponseEntity.ok(leagueService.findAllLeagues()); // Status: 200 OK
    }

    @PostMapping
    public ResponseEntity<League> createLeague(@RequestBody League league) {
        var leagueCreated = leagueService.createLeague(league);
        return ResponseEntity.status(HttpStatus.CREATED).body(leagueCreated); // Status: 201 Created
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeague(@PathVariable long id) {
        leagueService.deleteLeague(id);
        return ResponseEntity.noContent().build(); // Status: 204 No Content
    }

}
