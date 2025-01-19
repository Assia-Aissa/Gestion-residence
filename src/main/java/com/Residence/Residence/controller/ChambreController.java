package com.Residence.Residence.controller;


import com.Residence.Residence.DTO.ChambreRequestDto;
import com.Residence.Residence.DTO.ChambreResponseDto;
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
        public ResponseEntity<ChambreResponseDto> save(@Valid @RequestBody ChambreRequestDto chambreRequestDto) {
            ChambreResponseDto chambreResponseDto = chambreService.save(chambreRequestDto);
            return new ResponseEntity<>(chambreResponseDto, HttpStatus.CREATED);
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
            ChambreResponseDto chambreResponseDto = chambreService.findById(id);
            return ResponseEntity.ok(chambreResponseDto);
        }

        // Update
        @PutMapping("/{id}")
        public ResponseEntity<ChambreResponseDto> update(
                @Valid @RequestBody ChambreRequestDto chambreRequestDto,
                @PathVariable Long id) {
            ChambreResponseDto chambreResponseDto = chambreService.update(chambreRequestDto, id);
            return ResponseEntity.ok(chambreResponseDto);
        }

        // Delete
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id) {
            chambreService.delete(id);
            return ResponseEntity.noContent().build();
        }
    }


