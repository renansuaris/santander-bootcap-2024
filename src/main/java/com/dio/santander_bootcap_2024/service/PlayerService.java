package com.dio.santander_bootcap_2024.service;

import com.dio.santander_bootcap_2024.model.Player;

import java.util.List;

public interface PlayerService {

    Player findById(Long id);

    Player create(Player player);

    List<Player> findAllPlayers();

    void deleteById(Long id);

    void assignClubToPlayer(Long p_id, Long club_id);
}
