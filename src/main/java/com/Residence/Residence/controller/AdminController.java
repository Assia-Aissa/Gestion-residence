package com.Residence.Residence.controller;

import com.Residence.Residence.Entities.Administrateur;
import com.Residence.Residence.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Create
    @PostMapping
    public ResponseEntity<Administrateur> save(@Valid @RequestBody Administrateur administrateur) {
        Administrateur savedAdmin = adminService.save(administrateur);
        return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
    }

    // Read All
    @GetMapping
    public ResponseEntity<List<Administrateur>> findAll() {
        List<Administrateur> admins = adminService.findAll();
        return ResponseEntity.ok(admins);
    }

    // Read by ID
    @GetMapping("/{id}")
    public ResponseEntity<Administrateur> findById(@PathVariable Long id) {
        Administrateur admin = adminService.findById(id);
        return ResponseEntity.ok(admin);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Administrateur> update(
            @Valid @RequestBody Administrateur administrateur,
            @PathVariable Long id) {
        Administrateur updatedAdmin = adminService.update(administrateur, id);
        return ResponseEntity.ok(updatedAdmin);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        adminService.delete(id);
        return ResponseEntity.noContent().build();
    }
}