package com.Residence.Residence.service;


import com.Residence.Residence.Entities.Technicien;
import com.Residence.Residence.Repository.TechnicienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnicienService {

    private final TechnicienRepository technicienRepository;

    @Autowired
    public TechnicienService(TechnicienRepository technicienRepository) {
        this.technicienRepository = technicienRepository;
    }

    // Create
    public Technicien save(Technicien technicien) {
        return technicienRepository.save(technicien);
    }

    // Read All
    public List<Technicien> findAll() {
        return technicienRepository.findAll();
    }

    // Read by ID
    public Optional<Technicien> findById(Long id) {
        return technicienRepository.findById(id);
    }

    // Update
    public Technicien update(Long id, Technicien technicienDetails) {
        Optional<Technicien> technicienOptional = technicienRepository.findById(id);
        if (technicienOptional.isPresent()) {
            Technicien technicien = technicienOptional.get();
            technicien.setNom(technicienDetails.getNom());
            technicien.setPrenom(technicienDetails.getPrenom());
            technicien.setSpecialite(technicienDetails.getSpecialite());
            technicien.setTelephone(technicienDetails.getTelephone());
            return technicienRepository.save(technicien);
        } else {
            throw new RuntimeException("Technicien not found");
        }
    }

    // Delete
    public void delete(Long id) {
        technicienRepository.deleteById(id);
    }
}
