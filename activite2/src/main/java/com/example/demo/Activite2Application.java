package com.example.demo;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entities.Patient;
import com.example.demo.repositories.PatientRepository;

@SpringBootApplication
public class Activite2Application implements CommandLineRunner {

    private final PatientRepository patientRepository;

    public Activite2Application(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Activite2Application.class, args);
    }

    @Override
    public void run(String... args) {
        // Ajouter des patients
    	patientRepository.save(new Patient("Ahmed", new Date(), true, 100));
    	patientRepository.save(new Patient("Amina", new Date(), true, 200));

   

        // Consulter tous les patients
        System.out.println("Tous les patients :");
        patientRepository.findAll().forEach(System.out::println);

        // Consulter un patient
        Patient p = patientRepository.findById(1L).orElse(null);
        System.out.println("Patient ID 1 : " + p);

        // Chercher des patients
        System.out.println("Patients malades :");
        patientRepository.findByMalade(true).forEach(System.out::println);

        // Mise à jour
        if (p != null) {
            p.setScore(300);
            patientRepository.save(p);
        }

        // Suppression
        patientRepository.deleteById(2L);
    }
}
