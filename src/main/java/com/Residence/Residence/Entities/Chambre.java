package com.Residence.Residence.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Chambre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String numero;

    private Double taille;
    private String equipements;

    @Enumerated(EnumType.STRING)
    private StatutChambre statut;

    @OneToOne(mappedBy = "chambre", cascade = CascadeType.ALL, orphanRemoval = true)
    private Resident resident;

    @OneToMany(mappedBy = "chambre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RequeteMaintenance> maintenances;

    @ManyToOne
    @JoinColumn(name = "administrateur_id")
    private Administrateur administrateur;

        public Administrateur getAdministrateur() {
                return administrateur;
        }

        public void setAdministrateur(Administrateur administrateur) {
                this.administrateur = administrateur;
        }

        public List<RequeteMaintenance> getMaintenances() {
                return maintenances;
        }

        public void setMaintenances(List<RequeteMaintenance> maintenances) {
                this.maintenances = maintenances;
        }

        @Override
    public String toString() {
        return "Chambre{" +
                "id=" + id +
                ", equipements='" + equipements + '\'' +
                ", numero='" + numero + '\'' +
                ", statut='" + statut + '\'' +
                ", taille=" + taille +
                '}';
    }

    public StatutChambre getStatut() {
        return statut;
    }

    public void setStatut(StatutChambre statut) {
        this.statut = statut;
    }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }

    public Double getTaille() {
        return taille;
    }

    public void setTaille(Double taille) {
        this.taille = taille;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEquipements() {
        return equipements;
    }

    public void setEquipements(String equipements) {
        this.equipements = equipements;
    }
}