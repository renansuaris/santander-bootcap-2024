package com.dio.santander_bootcap_2024.service.impl;

import com.dio.santander_bootcap_2024.controller.exception.customexceptions.leagueexceptions.LeagueAlreadyExistsException;
import com.dio.santander_bootcap_2024.controller.exception.customexceptions.leagueexceptions.LeagueNotFoundException;
import com.dio.santander_bootcap_2024.model.League;
import com.dio.santander_bootcap_2024.repository.LeagueRepository;
import com.dio.santander_bootcap_2024.service.LeagueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeagueServiceImpl implements LeagueService {

    private final LeagueRepository leagueRepository;

    public LeagueServiceImpl(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    @Override
    public League findLeagueById(long id) {
        return leagueRepository.findById(id).orElseThrow(() -> new LeagueNotFoundException(id));
    }

    @Override
    public List<League> findAllLeagues() {
        return leagueRepository.findAll();
    }

    @Override
    public League createLeague(League league) {
        if(league.getId() != null && leagueRepository.existsById(league.getId())){
            throw new LeagueAlreadyExistsException();
        }
        return leagueRepository.save(league);
    }

    @Override
    public void deleteLeague(Long id) {
        if(leagueRepository.existsById(id)){
            leagueRepository.deleteById(id);
        }
        else
            throw new LeagueNotFoundException(id);
    }
}
