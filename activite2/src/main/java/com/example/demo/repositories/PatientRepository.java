package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	List<Patient> findByMalade(boolean malade);
}

