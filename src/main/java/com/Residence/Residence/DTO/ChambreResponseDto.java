package com.Residence.Residence.DTO;

import com.Residence.Residence.Entities.StatutChambre;

public class ChambreResponseDto {
    private Long id;
    private String numero;
    private Double taille;
    private String equipements;
    private StatutChambre statut;
    private ResidentInfo resident; // Custom field for resident info

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Double getTaille() {
        return taille;
    }

    public void setTaille(Double taille) {
        this.taille = taille;
    }

    public String getEquipements() {
        return equipements;
    }

    public void setEquipements(String equipements) {
        this.equipements = equipements;
    }

    public StatutChambre getStatut() {
        return statut;
    }

    public void setStatut(StatutChambre statut) {
        this.statut = statut;
    }

    public ResidentInfo getResident() {
        return resident;
    }

    public void setResident(ResidentInfo resident) {
        this.resident = resident;
    }

    // Inner class for resident info
    public static class ResidentInfo {
        private String nom;
        private String prenom;

        // Getters and Setters
        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getPrenom() {
            return prenom;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }
    }
}