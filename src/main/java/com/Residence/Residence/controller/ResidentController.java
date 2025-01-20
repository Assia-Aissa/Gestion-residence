package com.Residence.Residence.controller;

import com.Residence.Residence.Entities.Resident;
import com.Residence.Residence.service.ResidentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/residents")
public class ResidentController {

    @Autowired
    private ResidentService residentService;

    // Create Resident
    @PostMapping
    public ResponseEntity<Resident> save(@Valid @RequestBody Resident resident) {
        Resident savedResident = residentService.save(resident);
        return new ResponseEntity<>(savedResident, HttpStatus.CREATED);
    }

    // Read All
    @GetMapping
    public ResponseEntity<List<Resident>> findAll() {
        List<Resident> residents = residentService.findAll();
        return ResponseEntity.ok(residents);
    }

    // Read by ID
    @GetMapping("/{id}")
    public ResponseEntity<Resident> findById(@PathVariable Long id) {
        Resident resident = residentService.findById(id);
        return ResponseEntity.ok(resident);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Resident> update(
            @Valid @RequestBody Resident resident,
            @PathVariable Long id) {
        Resident updatedResident = residentService.update(resident, id);
        return ResponseEntity.ok(updatedResident);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        residentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}