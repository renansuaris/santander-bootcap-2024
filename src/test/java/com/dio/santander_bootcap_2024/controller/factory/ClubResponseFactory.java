package com.dio.santander_bootcap_2024.controller.factory;

import com.dio.santander_bootcap_2024.model.Club;

import java.util.ArrayList;
import java.util.List;

public class ClubResponseFactory {

    public static Club buildClub() {
        Club club = new Club();
        club.setClubStadium("Santiago Bernabeu");
        club.setClubName("Real Madrid");
        club.setId(1L);

        return club;
    }

    public static List<Club> buildListClubs() {
        List<Club> clubs = new ArrayList<>();

        Club club1 = new Club();
        club1.setClubStadium("Santiago Bernabeu");
        club1.setClubName("Real Madrid");
        club1.setId(1L);

        clubs.add(club1);

        Club club2 = new Club();
        club2.setClubStadium("Sao Januario");
        club2.setClubName("Vasco da Gama");
        club2.setId(2L);

        clubs.add(club2);

        return clubs;
    }

}
