package com.Residence.Residence.DTO;

import com.Residence.Residence.Entities.StatutChambre;
import lombok.*;


public class ChambreRequestDto {

        private String numero;
        private Double taille;
        private String equipements;
        private StatutChambre statut;

        // Getters and Setters
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
    }

