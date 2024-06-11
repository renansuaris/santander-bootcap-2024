package com.dio.santander_bootcap_2024.service;

import com.dio.santander_bootcap_2024.model.Club;
import com.dio.santander_bootcap_2024.model.Player;
import com.dio.santander_bootcap_2024.repository.ClubRepository;
import com.dio.santander_bootcap_2024.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    @Override
    public void assignClubToPlayer(Long p_id, Long club_id) {
        Player player = playerRepository.findById(p_id).orElseThrow(NoSuchElementException::new);
        Club club = clubRepository.findById(club_id).orElseThrow(NoSuchElementException::new);

        player.setClub(club);

        playerRepository.save(player);

    }


}
