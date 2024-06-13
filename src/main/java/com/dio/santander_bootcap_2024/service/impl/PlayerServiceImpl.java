package com.dio.santander_bootcap_2024.service.impl;

import com.dio.santander_bootcap_2024.controller.exception.customexceptions.clubexceptions.ClubNotFoundException;
import com.dio.santander_bootcap_2024.controller.exception.customexceptions.playerexceptions.PlayerAlreadyExistsException;
import com.dio.santander_bootcap_2024.controller.exception.customexceptions.playerexceptions.PlayerNotFoundException;
import com.dio.santander_bootcap_2024.model.Club;
import com.dio.santander_bootcap_2024.model.Player;
import com.dio.santander_bootcap_2024.repository.ClubRepository;
import com.dio.santander_bootcap_2024.repository.PlayerRepository;
import com.dio.santander_bootcap_2024.service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository playerRepository;
    private ClubRepository clubRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository, ClubRepository clubRepository) {
        this.playerRepository = playerRepository;
        this.clubRepository = clubRepository;
    }

    // If the player id is present in the repo, it will return it, and if he has a club
    // it will return the player along with his club
    @Override
    public Player findById(Long id) {
        return playerRepository.findById(id).orElseThrow( () -> new PlayerNotFoundException(id));
    }

    // If the player already exists in the Player Repo, throws exception
    // if not, it will save it in the repo
    @Override
    public Player create(Player player) {
        if(playerRepository.existsById(player.getId())) {
            throw new PlayerAlreadyExistsException();
        }
        return playerRepository.save(player);
    }

    @Override
    public List<Player> findAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        if(playerRepository.existsById(id)) {
            playerRepository.deleteById(id);
        }
        else {
            throw new PlayerNotFoundException(id);
        }
    }

    @Override
    public void assignClubToPlayer(Long player_id, Long club_id) {
        Player player = playerRepository.findById(player_id).orElseThrow( () -> new PlayerNotFoundException(player_id) );
        Club club = clubRepository.findById(club_id).orElseThrow( () -> new ClubNotFoundException(club_id) );

        player.setClub(club);

        playerRepository.save(player);

    }


}
