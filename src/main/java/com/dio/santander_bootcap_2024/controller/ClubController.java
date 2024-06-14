package com.dio.santander_bootcap_2024.controller;

import com.dio.santander_bootcap_2024.model.Club;
import com.dio.santander_bootcap_2024.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/club")
public class ClubController {

    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Club> getClubById(@PathVariable Long id){
        var club = clubService.findClubById(id);
        return ResponseEntity.ok(club); // Status: 200 OK
    }

    @GetMapping
    public ResponseEntity<List<Club>> getAllClubs(){
        return ResponseEntity.ok(clubService.findAllClubs()); // Status: 200 OK
    }

    @PostMapping
    public ResponseEntity<Club> createClub(@RequestBody Club club){
        var clubCreated = clubService.createClub(club);
        return ResponseEntity.status(HttpStatus.CREATED).body(clubCreated); // Status: 201 Created
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteClubById(Long id){
        clubService.deleteClubById(id);
        return ResponseEntity.noContent().build(); // Status: 204 No Content
    }

    @PutMapping("/{club_id}/assign-league/{league_id}")
    public ResponseEntity<Void> assignLeagueToClub(@PathVariable Long club_id, @PathVariable Long league_id){
        clubService.assignLeagueToClub(club_id, league_id);
        return ResponseEntity.noContent().build(); // Status: 204 No Content
    }

}
