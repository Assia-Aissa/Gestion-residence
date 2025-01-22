package com.Residence.Residence.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ResidentRequestDto {
    private String nom;
    private String prenom;

    @NotNull(message = "Email cannot be null")
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    private String telephone;
    private String adresse;

    private String chambreNumber; // Use chambre number instead of ID

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getChambreNumber() {
        return chambreNumber;
    }

    public void setChambreNumber(String chambreNumber) {
        this.chambreNumber = chambreNumber;
    }

    @Override
    public String toString() {
        return "ResidentRequestDto{" +
                "adresse='" + adresse + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", chambreNumber='" + chambreNumber + '\'' +
                '}';
    }
}