package com.dio.santander_bootcap_2024.controller.factory;

import com.dio.santander_bootcap_2024.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerResponseFactory {

    public static Player createPlayer() {
        var player = new Player();
        player.setId(1L);
        player.setName("Neymar");
        player.setAge(20);
        player.setPosition("LW");
        player.setShirtNumber(10);
        player.setHeight("180");
        player.setCountry("Brazil");

        return player;
    }

    public static List<Player> createPlayers() {
        var players = new ArrayList<Player>();
        players.add(createPlayer());
        var player2 = new Player();
        player2.setId(2L);
        player2.setName("Vinicius");
        player2.setAge(22);
        player2.setPosition("ST");
        player2.setShirtNumber(7);
        player2.setHeight("175");
        player2.setCountry("Brazil");
        players.add(player2);
        return players;
    }

}
