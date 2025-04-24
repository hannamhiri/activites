package com.example.demo;

import java.util.Date;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.entities.Consultation;
import com.example.demo.entities.Medecin;
import com.example.demo.entities.Patient;
import com.example.demo.entities.RendezVous;
import com.example.demo.repositories.ConsultationRepository;
import com.example.demo.repositories.MedecinRepository;
import com.example.demo.repositories.PatientRepository;
import com.example.demo.repositories.RendezVousRepository;

@SpringBootApplication
public class Activite2SqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(Activite2SqlApplication.class, args);
    }

    @Bean
    CommandLineRunner start(
            PatientRepository patientRepository,
            MedecinRepository medecinRepository,
            RendezVousRepository rendezVousRepository,
            ConsultationRepository consultationRepository) {
        return args -> {

            // Créer et sauvegarder des patients
            Patient patient1 = new Patient("Ala", new Date(), true, 100);
            Patient patient2 = new Patient("salma", new Date(), true, 200);
            patientRepository.save(patient1);
            patientRepository.save(patient2);

            // Créer et sauvegarder des médecins
            Medecin medecin1 = new Medecin(null, "Dr. Jamel", "Cardiologist", "dr.john@example.com", null);
            Medecin medecin2 = new Medecin(null, "Dr. Sara", "Pediatrician", "dr.sarah@example.com", null);
            medecinRepository.save(medecin1);
            medecinRepository.save(medecin2);

            // Créer et sauvegarder des rendez-vous
            RendezVous rendezVous1 = new RendezVous();
            rendezVous1.setPatient(patient1);
            rendezVous1.setMedecin(medecin1);
            rendezVous1.setDate(new Date().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
            rendezVous1.setHeure(java.time.LocalTime.now());
            rendezVous1.setStatut(com.example.demo.entities.StatutRdv.CONFIRME);
            rendezVousRepository.save(rendezVous1);

            RendezVous rendezVous2 = new RendezVous();
            rendezVous2.setPatient(patient2);
            rendezVous2.setMedecin(medecin2);
            rendezVous2.setDate(new Date().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
            rendezVous2.setHeure(java.time.LocalTime.now());
            rendezVous2.setStatut(com.example.demo.entities.StatutRdv.EN_ATTENTE);
            rendezVousRepository.save(rendezVous2);

            // Créer et sauvegarder des consultations
            Consultation consultation1 = new Consultation();
            consultation1.setRapport("Consultation report for Ahmed with Dr. John");
            consultation1.setRendezVous(rendezVous1);
            consultationRepository.save(consultation1);

            Consultation consultation2 = new Consultation();
            consultation2.setRapport("Consultation report for Amina with Dr. Sarah");
            consultation2.setRendezVous(rendezVous2);
            consultationRepository.save(consultation2);

            // Vérification dans la console
            System.out.println("Patients enregistrés :");
            System.out.println(patient1.getNom() + " avec ID : " + patient1.getId());
            System.out.println(patient2.getNom() + " avec ID : " + patient2.getId());

            System.out.println("Médecins enregistrés :");
            System.out.println(medecin1.getNom() + " avec ID : " + medecin1.getId());
            System.out.println(medecin2.getNom() + " avec ID : " + medecin2.getId());

            System.out.println("Rendez-vous enregistrés :");
            System.out.println("Rendez-vous 1 avec " + rendezVous1.getMedecin().getNom() + " pour " + rendezVous1.getPatient().getNom());
            System.out.println("Rendez-vous 2 avec " + rendezVous2.getMedecin().getNom() + " pour " + rendezVous2.getPatient().getNom());

            // Récupérer et afficher les consultations associées
            Optional<Consultation> fetchedConsultation1 = consultationRepository.findById(consultation1.getId());
            Optional<Consultation> fetchedConsultation2 = consultationRepository.findById(consultation2.getId());

            if (fetchedConsultation1.isPresent()) {
                System.out.println("Consultation trouvée : " + fetchedConsultation1.get().getRapport());
            } else {
                System.out.println("Consultation non trouvée pour Ahmed");
            }

            if (fetchedConsultation2.isPresent()) {
                System.out.println("Consultation trouvée : " + fetchedConsultation2.get().getRapport());
            } else {
                System.out.println("Consultation non trouvée pour Amina");
            }
        };
    }
}
