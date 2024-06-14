package com.dio.santander_bootcap_2024.service.impl;

import com.dio.santander_bootcap_2024.controller.exception.customexceptions.clubexceptions.ClubAlreadyExistsException;
import com.dio.santander_bootcap_2024.controller.exception.customexceptions.clubexceptions.ClubNotFoundException;
import com.dio.santander_bootcap_2024.controller.exception.customexceptions.leagueexceptions.LeagueNotFoundException;
import com.dio.santander_bootcap_2024.model.Club;
import com.dio.santander_bootcap_2024.model.League;
import com.dio.santander_bootcap_2024.repository.ClubRepository;
import com.dio.santander_bootcap_2024.repository.LeagueRepository;
import com.dio.santander_bootcap_2024.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClubServiceImpl implements ClubService {

    ClubRepository clubRepository;
    LeagueRepository leagueRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository, LeagueRepository leagueRepository) {
        this.clubRepository = clubRepository;
        this.leagueRepository = leagueRepository;
    }

    @Override
    public Club findClubById(Long id) throws ClubNotFoundException {
        return clubRepository.findById(id).orElseThrow(() -> new ClubNotFoundException(id));
    }

    @Override
    public Club createClub(Club clubToCreate) throws ClubAlreadyExistsException {
        if (clubToCreate.getId() != null && clubRepository.existsById(clubToCreate.getId())) {
            throw new ClubAlreadyExistsException();
        }
         return clubRepository.save(clubToCreate);
    }

    @Override
    public List<Club> findAllClubs() {
        return clubRepository.findAll();
    }

    @Override
    public void deleteClubById(Long id) throws ClubNotFoundException{
        if(clubRepository.existsById(id)) {
            clubRepository.deleteById(id);
        }
        else
            throw new ClubNotFoundException(id);
    }

    @Override
    public void assignLeagueToClub(Long club_id, Long league_id) throws ClubNotFoundException, LeagueNotFoundException {

        Club club = clubRepository.findById(club_id).orElseThrow( () -> new ClubNotFoundException(club_id) );
        League league = leagueRepository.findById(league_id).orElseThrow( () -> new LeagueNotFoundException(league_id) );

        club.setLeague(league);

        clubRepository.save(club);
    }
}
