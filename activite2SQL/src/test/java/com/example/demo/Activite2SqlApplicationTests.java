package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entities.Consultation;
import com.example.demo.entities.Medecin;
import com.example.demo.entities.Patient;
import com.example.demo.entities.RendezVous;
import com.example.demo.repositories.ConsultationRepository;
import com.example.demo.repositories.MedecinRepository;
import com.example.demo.repositories.PatientRepository;
import com.example.demo.repositories.RendezVousRepository;

@SpringBootTest
public class Activite2SqlApplicationTests {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MedecinRepository medecinRepository;

    @Autowired
    private RendezVousRepository rendezVousRepository;

    @Autowired
    private ConsultationRepository consultationRepository;

    private Patient patient;
    private Medecin medecin;
    private RendezVous rendezVous;
    private Consultation consultation;

    @BeforeEach
    public void setUp() {
        // Créer et sauvegarder un patient
        patient = new Patient("Ahmed", new Date(), true, 100);
        patientRepository.save(patient);

        // Créer et sauvegarder un médecin (Medecin)
        medecin = new Medecin(null, "Dr. John", "Cardiologist", "dr.john@example.com", null);
        medecinRepository.save(medecin);

        // Créer et sauvegarder un rendez-vous (RendezVous)
        rendezVous = new RendezVous();
        rendezVous.setPatient(patient);
        rendezVous.setMedecin(medecin);
        rendezVous.setDate(new Date().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
        rendezVous.setHeure(java.time.LocalTime.now());
        rendezVous.setStatut(com.example.demo.entities.StatutRdv.CONFIRME); // En supposant que StatutRdv est un Enum
        rendezVousRepository.save(rendezVous);

        // Créer et sauvegarder une consultation
        consultation = new Consultation();
        consultation.setRapport("Consultation report for Ahmed");
        consultation.setRendezVous(rendezVous);
        consultationRepository.save(consultation);
    }

    @Test
    public void testRelations() {
        // Tester le patient et son rendez-vous
        assertNotNull(patient.getId(), "Le patient devrait avoir un ID après l'enregistrement");
        assertNotNull(rendezVous.getId(), "Le rendez-vous devrait avoir un ID après l'enregistrement");

        // Tester si le rendez-vous est correctement lié au patient et au médecin
        assertEquals(patient.getId(), rendezVous.getPatient().getId(), "Le patient dans le rendez-vous devrait correspondre au patient enregistré");
        assertEquals(medecin.getId(), rendezVous.getMedecin().getId(), "Le médecin dans le rendez-vous devrait correspondre au médecin enregistré");

        // Tester si la consultation est correctement liée au rendez-vous
        assertEquals(rendezVous.getId(), consultation.getRendezVous().getId(), "La consultation devrait être liée au rendez-vous correct");

        // Tester si le rapport de la consultation est correctement sauvegardé et récupéré
        assertEquals("Consultation report for Ahmed", consultation.getRapport(), "Le rapport de consultation devrait correspondre au rapport enregistré");

        // Tester la récupération du patient sauvegardé dans la base de données
        Optional<Patient> fetchedPatient = patientRepository.findById(patient.getId());
        assertTrue(fetchedPatient.isPresent(), "Le patient devrait être présent dans la base de données");
        assertEquals("Ahmed", fetchedPatient.get().getNom(), "Le nom du patient récupéré devrait être Ahmed");

        // Tester la relation du rendez-vous avec le patient et le médecin
        RendezVous fetchedRendezVous = rendezVousRepository.findById(rendezVous.getId()).orElse(null);
        assertNotNull(fetchedRendezVous, "Le rendez-vous devrait être présent dans la base de données");
        assertEquals("Dr. John", fetchedRendezVous.getMedecin().getNom(), "Le nom du médecin dans le rendez-vous devrait être Dr. John");
        assertEquals("Ahmed", fetchedRendezVous.getPatient().getNom(), "Le nom du patient dans le rendez-vous devrait être Ahmed");
    }
}
