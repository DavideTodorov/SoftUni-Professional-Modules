package com.example.exercise.repositories;

import com.example.exercise.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {
    Game findByTitle(String title);
}
