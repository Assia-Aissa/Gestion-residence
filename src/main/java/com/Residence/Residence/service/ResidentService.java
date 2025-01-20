package com.Residence.Residence.service;

import com.Residence.Residence.Entities.Chambre;
import com.Residence.Residence.Entities.Resident;
import com.Residence.Residence.Repository.ChambreRepository;
import com.Residence.Residence.Repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResidentService {

    private final ResidentRepository residentRepository;
    private final ChambreRepository chambreRepository;

    @Autowired
    public ResidentService(ResidentRepository residentRepository, ChambreRepository chambreRepository) {
        this.residentRepository = residentRepository;
        this.chambreRepository = chambreRepository;
    }

    // Create
    public Resident save(Resident resident) {
        // Set the Chambre if the ID is provided
        if (resident.getChambre() != null && resident.getChambre().getId() != null) {
            Chambre chambre = chambreRepository.findById(resident.getChambre().getId())
                    .orElseThrow(() -> new RuntimeException("Chambre not found"));
            resident.setChambre(chambre);
        }
        return residentRepository.save(resident);
    }

    // Read All
    public List<Resident> findAll() {
        return residentRepository.findAll();
    }

    // Read by ID
    public Resident findById(Long id) {
        return residentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resident not found"));
    }

    // Update
    public Resident update(Resident resident, Long id) {
        Optional<Resident> residentOptional = residentRepository.findById(id);
        if (residentOptional.isPresent()) {
            Resident existingResident = residentOptional.get();
            existingResident.setNom(resident.getNom());
            existingResident.setPrenom(resident.getPrenom());
            existingResident.setEmail(resident.getEmail());
            existingResident.setTelephone(resident.getTelephone());
            existingResident.setAdresse(resident.getAdresse());
            existingResident.setDateNaissance(resident.getDateNaissance());

            // Update the Chambre if the ID changes
            if (resident.getChambre() != null && resident.getChambre().getId() != null) {
                Chambre chambre = chambreRepository.findById(resident.getChambre().getId())
                        .orElseThrow(() -> new RuntimeException("Chambre not found"));
                existingResident.setChambre(chambre);
            }

            return residentRepository.save(existingResident);
        } else {
            throw new RuntimeException("Resident not found");
        }
    }

    // Delete
    public void delete(Long id) {
        residentRepository.deleteById(id);
    }
}