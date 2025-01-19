package com.Residence.Residence.DTO;

import com.Residence.Residence.Entities.StatutPaiement;

import java.util.Date;

public class PaiementResponseDto {
    private Long id;
    private double montantDu;
    private double montantPaye;
    private StatutPaiement statut;
    private Date datePaiement;
    private String recu;
    private Long residentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(Date datePaiement) {
        this.datePaiement = datePaiement;
    }

    public double getMontantDu() {
        return montantDu;
    }

    public void setMontantDu(double montantDu) {
        this.montantDu = montantDu;
    }

    public double getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(double montantPaye) {
        this.montantPaye = montantPaye;
    }

    public String getRecu() {
        return recu;
    }

    public void setRecu(String recu) {
        this.recu = recu;
    }

    public Long getResidentId() {
        return residentId;
    }

    public void setResidentId(Long residentId) {
        this.residentId = residentId;
    }

    public StatutPaiement getStatut() {
        return statut;
    }

    public void setStatut(StatutPaiement statut) {
        this.statut = statut;
    }
}
