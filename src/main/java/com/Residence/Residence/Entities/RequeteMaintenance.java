package com.Residence.Residence.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor

public class RequeteMaintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Date dateSignalement;

    @Enumerated(EnumType.STRING)
    private StatutRequete statut;

    @ManyToOne
    @JoinColumn(name = "resident_id")
    private Resident resident;

    @ManyToOne
    @JoinColumn(name = "technicien_id")
    private Technicien technicien;

    @ManyToOne
    @JoinColumn(name = "chambre_id")
    private Chambre chambre;

    public Chambre getChambre() {
        return chambre;
    }

    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }

    public Date getDateSignalement() {
        return dateSignalement;
    }

    public void setDateSignalement(Date dateSignalement) {
        this.dateSignalement = dateSignalement;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }

    public StatutRequete getStatut() {
        return statut;
    }

    public void setStatut(StatutRequete statut) {
        this.statut = statut;
    }

    public Technicien getTechnicien() {
        return technicien;
    }

    public void setTechnicien(Technicien technicien) {
        this.technicien = technicien;
    }
}
