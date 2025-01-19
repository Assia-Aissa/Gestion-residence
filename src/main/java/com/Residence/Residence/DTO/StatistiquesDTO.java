package com.Residence.Residence.DTO;

import com.Residence.Residence.Entities.Paiement;
import com.Residence.Residence.Entities.RequeteMaintenance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class StatistiquesDTO {

    private double tauxOccupation;
    private List<Paiement> paiementsEnRetard;
    private List<RequeteMaintenance> incidentsEnCours;
    private List<String> maintenancePreventive;


}
