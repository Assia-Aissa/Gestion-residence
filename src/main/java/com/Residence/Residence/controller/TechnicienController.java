package com.Residence.Residence.controller;


import com.Residence.Residence.Entities.Technicien;
import com.Residence.Residence.service.TechnicienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/techniciens")
public class TechnicienController {

    @Autowired
    private TechnicienService technicienService;

    // Create
    @PostMapping
    public ResponseEntity<Technicien> save(@RequestBody Technicien technicien) {
        Technicien savedTechnicien = technicienService.save(technicien);
        return new ResponseEntity<>(savedTechnicien, HttpStatus.CREATED);
    }

    // Read All
    @GetMapping
    public ResponseEntity<List<Technicien>> findAll() {
        List<Technicien> techniciens = technicienService.findAll();
        return ResponseEntity.ok(techniciens);
    }

    // Read by ID
    @GetMapping("/{id}")
    public ResponseEntity<Technicien> findById(@PathVariable Long id) {
        Optional<Technicien> technicien = technicienService.findById(id);
        return technicien.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Technicien> update(@PathVariable Long id, @RequestBody Technicien technicienDetails) {
        Technicien updatedTechnicien = technicienService.update(id, technicienDetails);
        return ResponseEntity.ok(updatedTechnicien);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        technicienService.delete(id);
        return ResponseEntity.noContent().build();
    }
}