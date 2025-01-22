package com.Residence.Residence.controller;


import com.Residence.Residence.DTO.TechnicienRequestDTO;
import com.Residence.Residence.DTO.TechnicienResponseDTO;

import com.Residence.Residence.service.TechnicienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/techniciens")
public class TechnicienController {

    @Autowired
    private TechnicienService technicienService;

    // Create a new Technicien
    @PostMapping
    public ResponseEntity<TechnicienResponseDTO> createTechnicien(@RequestBody TechnicienRequestDTO requestDTO) {
        TechnicienResponseDTO responseDTO = technicienService.createTechnicien(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    // Get all Techniciens
    @GetMapping
    public ResponseEntity<List<TechnicienResponseDTO>> getAllTechniciens() {
        List<TechnicienResponseDTO> techniciens = technicienService.getAllTechniciens();
        return new ResponseEntity<>(techniciens, HttpStatus.OK);
    }

    // Get a Technicien by ID
    @GetMapping("/{id}")
    public ResponseEntity<TechnicienResponseDTO> getTechnicienById(@PathVariable Long id) {
        TechnicienResponseDTO responseDTO = technicienService.getTechnicienById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // Update a Technicien
    @PutMapping("/{id}")
    public ResponseEntity<TechnicienResponseDTO> updateTechnicien(
            @PathVariable Long id, @RequestBody TechnicienRequestDTO requestDTO) {
        TechnicienResponseDTO responseDTO = technicienService.updateTechnicien(id, requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // Delete a Technicien
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnicien(@PathVariable Long id) {
        technicienService.deleteTechnicien(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}