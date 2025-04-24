package com.example.demo.entities;


import java.util.Collection;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

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
    
    @OneToMany(mappedBy = "patient", fetch=FetchType.LAZY)
    private Collection<RendezVous> rendezvous;
    
    public Patient() {
    }

    // Constructeur avec arguments pour l'initialisation
    public Patient(String nom, Date dateNaissance, boolean malade, int score) {
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.malade = malade;
        this.score = score;
    }
    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public Date getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(Date dateNaissance) { this.dateNaissance = dateNaissance; }

    public boolean isMalade() { return malade; }
    public void setMalade(boolean malade) { this.malade = malade; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
}