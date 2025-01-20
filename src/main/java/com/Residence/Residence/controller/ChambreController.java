package com.Residence.Residence.controller;


import com.Residence.Residence.DTO.ChambreResponseDto;
import com.Residence.Residence.Entities.Chambre;
import com.Residence.Residence.service.ChambreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/chambres")
public class ChambreController {

    @Autowired
    private ChambreService chambreService;

    // Create
    @PostMapping
    public ResponseEntity<ChambreResponseDto> save(@Valid @RequestBody Chambre chambre) {
        ChambreResponseDto savedChambre = chambreService.save(chambre);
        return new ResponseEntity<>(savedChambre, HttpStatus.CREATED);
    }

    // Read All
    @GetMapping
    public ResponseEntity<List<ChambreResponseDto>> findAll() {
        List<ChambreResponseDto> chambres = chambreService.findAll();
        return ResponseEntity.ok(chambres);
    }

    // Read by ID
    @GetMapping("/{id}")
    public ResponseEntity<ChambreResponseDto> findById(@PathVariable Long id) {
        ChambreResponseDto chambre = chambreService.findById(id);
        return ResponseEntity.ok(chambre);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<ChambreResponseDto> update(
            @Valid @RequestBody Chambre chambre,
            @PathVariable Long id) {
        ChambreResponseDto updatedChambre = chambreService.update(chambre, id);
        return ResponseEntity.ok(updatedChambre);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        chambreService.delete(id);
        return ResponseEntity.noContent().build();
    }
}