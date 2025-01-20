package com.Residence.Residence.service;


import com.Residence.Residence.Entities.Paiement;
import com.Residence.Residence.Repository.PaiementRepository;
import com.Residence.Residence.Repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaiementService {

    private final PaiementRepository paiementRepository;
    private final ResidentRepository residentRepository;

    @Autowired
    public PaiementService(PaiementRepository paiementRepository, ResidentRepository residentRepository) {
        this.paiementRepository = paiementRepository;
        this.residentRepository = residentRepository;
    }

    // Create
    public Paiement save(Paiement paiement) {
        // Set the Resident
        if (paiement.getResident() != null && paiement.getResident().getId() != null) {
            Resident resident = residentRepository.findById(paiement.getResident().getId())
                    .orElseThrow(() -> new RuntimeException("Resident not found"));
            paiement.setResident(resident);
        }
        return paiementRepository.save(paiement); // Return the saved entity
    }

    // Read All
    public List<Paiement> findAll() {
        return paiementRepository.findAll();
    }

    // Read by ID
    public Paiement findById(Long id) {
        return paiementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paiement not found"));
    }

    // Update
    public Paiement update(Paiement paiement, Long id) {
        Optional<Paiement> paiementOptional = paiementRepository.findById(id);
        if (paiementOptional.isPresent()) {
            Paiement existingPaiement = paiementOptional.get();
            existingPaiement.setMontantDu(paiement.getMontantDu());
            existingPaiement.setMontantPaye(paiement.getMontantPaye());
            existingPaiement.setStatut(paiement.getStatut());
            existingPaiement.setDatePaiement(paiement.getDatePaiement());
            existingPaiement.setRecu(paiement.getRecu());

            // Update the Resident if the ID changes
            if (paiement.getResident() != null && paiement.getResident().getId() != null) {
                Resident resident = residentRepository.findById(paiement.getResident().getId())
                        .orElseThrow(() -> new RuntimeException("Resident not found"));
                existingPaiement.setResident(resident);
            }

            return paiementRepository.save(existingPaiement);
        } else {
            throw new RuntimeException("Paiement not found");
        }
    }

    // Delete
    public void delete(Long id) {
        paiementRepository.deleteById(id);
    }
}