package com.dio.santander_bootcap_2024.controller;

import com.dio.santander_bootcap_2024.model.Club;
import com.dio.santander_bootcap_2024.service.ClubService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Club Controler", description = "Club controller to perform CRUD operations on clubs")
@RestController
@RequestMapping("/club")
public class ClubController {

    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @Operation(summary = "Get club by specified ID")
    @GetMapping("/{id}")
    public ResponseEntity<Club> getClubById(@PathVariable Long id){
        var club = clubService.findClubById(id);
        return ResponseEntity.ok(club); // Status: 200 OK
    }

    @Operation(summary = "Get all clubs")
    @GetMapping
    public ResponseEntity<List<Club>> getAllClubs(){
        return ResponseEntity.ok(clubService.findAllClubs()); // Status: 200 OK
    }

    @Operation(summary = "Create a club")
    @PostMapping
    public ResponseEntity<Club> createClub(@RequestBody Club club){
        var clubCreated = clubService.createClub(club);
        return ResponseEntity.status(HttpStatus.CREATED).body(clubCreated); // Status: 201 Created
    }

    @Operation(summary = "Delete club by specified ID")
    @DeleteMapping
    public ResponseEntity<Void> deleteClubById(Long id){
        clubService.deleteClubById(id);
        return ResponseEntity.noContent().build(); // Status: 204 No Content
    }

    @Operation(summary = "Assign a league to a club")
    @PutMapping("/{club_id}/assign-league/{league_id}")
    public ResponseEntity<Void> assignLeagueToClub(@PathVariable Long club_id, @PathVariable Long league_id){
        clubService.assignLeagueToClub(club_id, league_id);
        return ResponseEntity.noContent().build(); // Status: 204 No Content
    }

}
