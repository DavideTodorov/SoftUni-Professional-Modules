package com.example.football.repository;


import com.example.football.models.dto.BestPlayerOutputDto;
import com.example.football.models.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    @Query("select new com.example.football.models.dto.BestPlayerOutputDto(p.firstName, p.lastName, p.position, p.team.name, p.team.stadiumName) " +
            "from Player p  where p.birthDate between :startDate and :endDate " +
            "order by p.stat.shooting desc, p.stat.passing desc, p.stat.endurance desc, p.lastName ")
    List<BestPlayerOutputDto> findAllByBirthDateBetweenAndOrderByStatsDesc(@Param("startDate")LocalDate startDate,
                                                                           @Param("endDate")LocalDate endDate);
}
