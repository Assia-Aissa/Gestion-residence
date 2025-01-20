package com.Residence.Residence.controller;


import com.Residence.Residence.Entities.Paiement;
import com.Residence.Residence.service.PaiementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paiements")
public class PaiementController {

    @Autowired
    private PaiementService paiementService;

    @PostMapping
    public ResponseEntity<Paiement> save(@Valid @RequestBody Paiement paiement) {
        Paiement savedPaiement = paiementService.save(paiement);
        return new ResponseEntity<>(savedPaiement, HttpStatus.CREATED);
    }

    // Read All
    @GetMapping
    public ResponseEntity<List<Paiement>> findAll() {
        List<Paiement> paiements = paiementService.findAll();
        return ResponseEntity.ok(paiements);
    }

    // Read by ID
    @GetMapping("/{id}")
    public ResponseEntity<Paiement> findById(@PathVariable Long id) {
        Paiement paiement = paiementService.findById(id);
        return ResponseEntity.ok(paiement);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Paiement> update(
            @Valid @RequestBody Paiement paiement,
            @PathVariable Long id) {
        Paiement updatedPaiement = paiementService.update(paiement, id);
        return ResponseEntity.ok(updatedPaiement);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        paiementService.delete(id);
        return ResponseEntity.noContent().build();
    }
}