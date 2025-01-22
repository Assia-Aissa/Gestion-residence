package com.Residence.Residence.DTO;

import java.util.List;

public class TechnicienResponseDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String specialite;
    private String telephone;
    private List<Long> requeteIds; // Assuming you only need IDs of assigned requests

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

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

    public List<Long> getRequeteIds() {
        return requeteIds;
    }

    public void setRequeteIds(List<Long> requeteIds) {
        this.requeteIds = requeteIds;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}
