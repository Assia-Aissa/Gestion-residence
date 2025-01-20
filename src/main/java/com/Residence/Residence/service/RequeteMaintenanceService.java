package com.Residence.Residence.service;




import com.Residence.Residence.Entities.RequeteMaintenance;

import com.Residence.Residence.Repository.RequeteMaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequeteMaintenanceService {

    @Autowired
    private RequeteMaintenanceRepository requeteMaintenanceRepository;

    // Get all maintenance requests
    public List<RequeteMaintenance> getAllRequetes() {
        System.out.println("hhhhhhhhhhhhhhhhhhhhhh"+requeteMaintenanceRepository.findAll());
        return requeteMaintenanceRepository.findAll();
    }

    // Get a maintenance request by ID
    public Optional<RequeteMaintenance> getRequeteById(Long id) {
        return requeteMaintenanceRepository.findById(id);
    }

    // Create a new maintenance request
    public RequeteMaintenance createRequete(RequeteMaintenance requeteMaintenance) {
        return requeteMaintenanceRepository.save(requeteMaintenance);
    }

    // Update an existing maintenance request
    public RequeteMaintenance updateRequete(Long id, RequeteMaintenance updatedRequete) {
        if (requeteMaintenanceRepository.existsById(id)) {
            updatedRequete.setId(id);
            return requeteMaintenanceRepository.save(updatedRequete);
        }
        return null; // Or throw an exception
    }

    // Delete a maintenance request
    public void deleteRequete(Long id) {
        requeteMaintenanceRepository.deleteById(id);
    }
}