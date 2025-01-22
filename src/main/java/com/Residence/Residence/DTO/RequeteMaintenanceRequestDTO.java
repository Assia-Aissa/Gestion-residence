package com.Residence.Residence.DTO;

import com.Residence.Residence.Entities.StatutRequete;

import java.util.Date;

public class RequeteMaintenanceRequestDTO {
    private String description;
    private Date dateSignalement;
    private StatutRequete statut;
    //private Long residentId;
    //private Long technicienId;
    //private Long chambreId;

    // Getters and Setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateSignalement() {
        return dateSignalement;
    }

    public void setDateSignalement(Date dateSignalement) {
        this.dateSignalement = dateSignalement;
    }

    public StatutRequete getStatut() {
        return statut;
    }

    public void setStatut(StatutRequete statut) {
        this.statut = statut;
    }


}