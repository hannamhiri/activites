package com.example.demo.repositories;

import com.example.demo.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    // Custom queries can be added here
}
