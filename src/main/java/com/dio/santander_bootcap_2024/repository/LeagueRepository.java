package com.dio.santander_bootcap_2024.repository;

import com.dio.santander_bootcap_2024.model.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {

}
