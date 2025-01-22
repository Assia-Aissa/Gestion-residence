package com.Residence.Residence.controller;


import com.Residence.Residence.DTO.PaiementRequestDto;
import com.Residence.Residence.DTO.PaiementResponseDto;
import com.Residence.Residence.service.PaiementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/paiements")
public class PaiementController {

    @Autowired
    private  PaiementService paiementService;

    public PaiementController(PaiementService paiementService) {
        this.paiementService = paiementService;
    }

    // Create
    @PostMapping
    public ResponseEntity<PaiementResponseDto> createPaiement(@RequestBody PaiementRequestDto requestDTO) {
        System.out.println("Received request: " + requestDTO); // Log the request payload
        PaiementResponseDto responseDTO = paiementService.save(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    // Read All
    @GetMapping
    public ResponseEntity<List<PaiementResponseDto>> findAll() {
        List<PaiementResponseDto> paiements = paiementService.findAll();
        return ResponseEntity.ok(paiements);
    }
    @GetMapping("/resident/{residentId}")
    public ResponseEntity<List<PaiementResponseDto>> getPaiementsByResidentId(@PathVariable Long residentId) {
        List<PaiementResponseDto> paiements = paiementService.getPaiementsByResidentId(residentId);
        return new ResponseEntity<>(paiements, HttpStatus.OK);
    }
    // Read by ID
    @GetMapping("/{id}")
    public ResponseEntity<PaiementResponseDto> findById(@PathVariable Long id) {
        PaiementResponseDto paiementResponseDto = paiementService.findById(id);
        return ResponseEntity.ok(paiementResponseDto);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<PaiementResponseDto> update(
            @Valid @RequestBody PaiementRequestDto paiementRequestDto,
            @PathVariable Long id) {
        PaiementResponseDto paiementResponseDto = paiementService.update(paiementRequestDto, id);
        return ResponseEntity.ok(paiementResponseDto);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        paiementService.delete(id);
        return ResponseEntity.noContent().build();
    }
}