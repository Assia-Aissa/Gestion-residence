package com.Residence.Residence.service;


import com.Residence.Residence.Entities.RequeteMaintenance;
import com.Residence.Residence.Repository.RequeteMaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequeteMaintenanceService {

    private final RequeteMaintenanceRepository requeteMaintenanceRepository;

    @Autowired
    public RequeteMaintenanceService(RequeteMaintenanceRepository requeteMaintenanceRepository) {
        this.requeteMaintenanceRepository = requeteMaintenanceRepository;
    }

    // Create
    public RequeteMaintenance save(RequeteMaintenance requeteMaintenance) {
        return requeteMaintenanceRepository.save(requeteMaintenance);
    }

    // Read All
    public List<RequeteMaintenance> findAll() {
        return requeteMaintenanceRepository.findAll();
    }

    // Read by ID
    public Optional<RequeteMaintenance> findById(Long id) {
        return requeteMaintenanceRepository.findById(id);
    }

    // Update
    public RequeteMaintenance update(Long id, RequeteMaintenance requeteMaintenanceDetails) {
        Optional<RequeteMaintenance> requeteOptional = requeteMaintenanceRepository.findById(id);
        if (requeteOptional.isPresent()) {
            RequeteMaintenance requeteMaintenance = requeteOptional.get();
            requeteMaintenance.setDescription(requeteMaintenanceDetails.getDescription());
            requeteMaintenance.setDateSignalement(requeteMaintenanceDetails.getDateSignalement());
            requeteMaintenance.setStatut(requeteMaintenanceDetails.getStatut());
            requeteMaintenance.setResident(requeteMaintenanceDetails.getResident());
            requeteMaintenance.setTechnicien(requeteMaintenanceDetails.getTechnicien());
            requeteMaintenance.setChambre(requeteMaintenanceDetails.getChambre());
            return requeteMaintenanceRepository.save(requeteMaintenance);
        } else {
            throw new RuntimeException("RequeteMaintenance not found");
        }
    }

    // Delete
    public void delete(Long id) {
        requeteMaintenanceRepository.deleteById(id);
    }

}