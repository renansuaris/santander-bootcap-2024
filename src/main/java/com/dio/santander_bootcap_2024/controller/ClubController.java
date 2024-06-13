package com.dio.santander_bootcap_2024.controller;

import com.dio.santander_bootcap_2024.model.Club;
import com.dio.santander_bootcap_2024.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/club")
public class ClubController {

    private ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Club> getClubById(@PathVariable Long id){
        var club = clubService.findClubById(id);
        return ResponseEntity.ok(club);
    }

    @GetMapping
    public ResponseEntity<List<Club>> getAllClubs(){
        return ResponseEntity.ok(clubService.findAllClubs());
    }

    @PostMapping
    public ResponseEntity<Club> createClub(@RequestBody Club club){
        return ResponseEntity.ok(clubService.createClub(club));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteClubById(Long id){
        clubService.deleteClubById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{club_id}/assign-league/{league_id}")
    public ResponseEntity<Void> assignLeagueToClub(@PathVariable Long club_id, @PathVariable Long league_id){
        clubService.assignLeagueToClub(club_id, league_id);
        return ResponseEntity.noContent().build();
    }

}
