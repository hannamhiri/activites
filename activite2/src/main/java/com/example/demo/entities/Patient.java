package com.example.demo.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Patient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private boolean malade;
    private int score;

    // Constructeur par défaut requis par JPA
    public Patient() {
    }

    // Constructeur avec arguments pour l'initialisation
    public Patient(String nom, Date dateNaissance, boolean malade, int score) {
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.malade = malade;
        this.score = score;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public boolean isMalade() {
        return malade;
    }

    public void setMalade(boolean malade) {
        this.malade = malade;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // Redéfinir la méthode toString pour un affichage personnalisé
    @Override
    public String toString() {
        return "Patient [id=" + id + ", nom=" + nom + ", dateNaissance=" + dateNaissance + ", malade=" + malade + ", score=" + score + "]";
    }
}
