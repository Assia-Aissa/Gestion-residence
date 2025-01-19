package com.Residence.Residence.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Resident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;

    @Column(unique = true, nullable = false)
    private String email;

    private String telephone;
    private String adresse;
    private Date dateNaissance;



    @OneToMany(mappedBy = "resident")
    private List<Paiement> historiquePaiements;

    @OneToMany(mappedBy = "resident")
    private List<RequeteMaintenance> historiqueRequetes;

    @OneToOne
    private Chambre chambre;

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Chambre getChambre() {
        return chambre;
    }

    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Paiement> getHistoriquePaiements() {
        return historiquePaiements;
    }

    public void setHistoriquePaiements(List<Paiement> historiquePaiements) {
        this.historiquePaiements = historiquePaiements;
    }

    public List<RequeteMaintenance> getHistoriqueRequetes() {
        return historiqueRequetes;
    }

    public void setHistoriqueRequetes(List<RequeteMaintenance> historiqueRequetes) {
        this.historiqueRequetes = historiqueRequetes;
    }

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
