package com.dio.santander_bootcap_2024.controller;

import com.dio.santander_bootcap_2024.model.League;
import com.dio.santander_bootcap_2024.service.LeagueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/league")
public class LeagueController {

    private final LeagueService leagueService;

    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<League> findById(@PathVariable int id) {
        return ResponseEntity.ok(leagueService.findLeagueById(id));
    }

    @GetMapping
    public ResponseEntity<List<League>> findAll(){
        return ResponseEntity.ok(leagueService.findAllLeagues());
    }

    @PostMapping
    public ResponseEntity<League> createLeague(@RequestBody League league) {
        return ResponseEntity.ok(leagueService.createLeague(league));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeague(@PathVariable long id) {
        leagueService.deleteLeague(id);
        return ResponseEntity.noContent().build();
    }

}
