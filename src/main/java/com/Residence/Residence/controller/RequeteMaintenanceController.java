package com.Residence.Residence.controller;


import com.Residence.Residence.Entities.RequeteMaintenance;

import com.Residence.Residence.service.RequeteMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/requetes-maintenance")
public class RequeteMaintenanceController {

    @Autowired
    private RequeteMaintenanceService requeteMaintenanceService;

    // Get all maintenance requests
    @GetMapping
    public List<RequeteMaintenance> getAllRequetes() {
        System.out.println("---------------------->"+requeteMaintenanceService.getAllRequetes());

        return requeteMaintenanceService.getAllRequetes();
    }

    // Get a maintenance request by ID
    @GetMapping("/{id}")
    public ResponseEntity<RequeteMaintenance> getRequeteById(@PathVariable Long id) {
        Optional<RequeteMaintenance> requete = requeteMaintenanceService.getRequeteById(id);

        return requete.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new maintenance request
    @PostMapping
    public RequeteMaintenance createRequete(@RequestBody RequeteMaintenance requeteMaintenance) {
        return requeteMaintenanceService.createRequete(requeteMaintenance);
    }

    // Update an existing maintenance request
    @PutMapping("/{id}")
    public ResponseEntity<RequeteMaintenance> updateRequete(@PathVariable Long id, @RequestBody RequeteMaintenance updatedRequete) {
        RequeteMaintenance requete = requeteMaintenanceService.updateRequete(id, updatedRequete);
        if (requete != null) {
            return ResponseEntity.ok(requete);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete a maintenance request
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequete(@PathVariable Long id) {
        requeteMaintenanceService.deleteRequete(id);
        return ResponseEntity.noContent().build();
    }
}