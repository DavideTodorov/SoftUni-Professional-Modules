package com.example.cardealer.repositories;

import com.example.cardealer.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long> {
}
