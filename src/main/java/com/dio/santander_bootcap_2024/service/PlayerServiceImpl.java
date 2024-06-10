package com.dio.santander_bootcap_2024.service;

import com.dio.santander_bootcap_2024.model.Player;
import com.dio.santander_bootcap_2024.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    // If the player id is present in the repo, it will return it, and if he has a club
    // it will return the player along with his club
    @Override
    public Player findById(Long id) {
        return playerRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    // If the player already exists in the Player Repo, throws exception
    // if not, it will save it in the repo
    @Override
    public Player create(Player player) {
        if(player.getId() != null && playerRepository.existsById(player.getId())) {
            throw(new IllegalArgumentException("Player already exists"));
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
        else
            throw(new NoSuchElementException("Player not found"));
    }

}
