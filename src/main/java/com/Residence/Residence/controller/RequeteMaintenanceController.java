package com.Residence.Residence.controller;


import com.Residence.Residence.DTO.RequeteMaintenanceRequestDTO;
import com.Residence.Residence.DTO.RequeteMaintenanceResponseDTO;

import com.Residence.Residence.service.RequeteMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/requete-maintenance")
public class RequeteMaintenanceController {

    @Autowired
    private RequeteMaintenanceService requeteMaintenanceService;

    @PostMapping
    public ResponseEntity<RequeteMaintenanceResponseDTO> createRequeteMaintenance(@RequestBody RequeteMaintenanceRequestDTO requestDTO) {
        RequeteMaintenanceResponseDTO responseDTO = requeteMaintenanceService.createRequeteMaintenance(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RequeteMaintenanceResponseDTO>> getAllRequeteMaintenances() {
        List<RequeteMaintenanceResponseDTO> responseDTOs = requeteMaintenanceService.getAllRequeteMaintenances();
        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequeteMaintenanceResponseDTO> getRequeteMaintenanceById(@PathVariable Long id) {
        RequeteMaintenanceResponseDTO responseDTO = requeteMaintenanceService.getRequeteMaintenanceById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RequeteMaintenanceResponseDTO> updateRequeteMaintenance(@PathVariable Long id, @RequestBody RequeteMaintenanceRequestDTO requestDTO) {
        RequeteMaintenanceResponseDTO responseDTO = requeteMaintenanceService.updateRequeteMaintenance(id, requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequeteMaintenance(@PathVariable Long id) {
        requeteMaintenanceService.deleteRequeteMaintenance(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}