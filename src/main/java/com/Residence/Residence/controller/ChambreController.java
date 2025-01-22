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
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/chambres")
public class ChambreController {

        @Autowired
        private ChambreService chambreService;


    @GetMapping("/disponibles")
    public ResponseEntity<List<ChambreResponseDto>> getAvailableChambres() {
        List<ChambreResponseDto> availableChambres = chambreService.getAvailableChambres();
        return ResponseEntity.ok(availableChambres);
    }
        // Create
        @PostMapping("/chambre/save")
        public ResponseEntity<ChambreResponseDto> save(@Valid @RequestBody ChambreRequestDto chambreRequestDto) {
            ChambreResponseDto chambreResponseDto = chambreService.save(chambreRequestDto);
            return new ResponseEntity<>(chambreResponseDto, HttpStatus.CREATED);
        }

        // Read All
        @GetMapping("/allChambres")
        public ResponseEntity<List<ChambreResponseDto>> getAllChambres() {
            List<ChambreResponseDto> chambres = chambreService.getAllChambres();
            return ResponseEntity.ok(chambres);
        }

        // Read by ID
        @GetMapping("chambre/{id}")
        public ResponseEntity<ChambreResponseDto> findById(@PathVariable Long id) {
            ChambreResponseDto chambreResponseDto = chambreService.findById(id);
            return ResponseEntity.ok(chambreResponseDto);
        }

        // Update
        @PutMapping("chambre/{id}")
        public ResponseEntity<ChambreResponseDto> update(
                @Valid @RequestBody ChambreRequestDto chambreRequestDto,
                @PathVariable Long id) {
            ChambreResponseDto chambreResponseDto = chambreService.update(chambreRequestDto, id);
            return ResponseEntity.ok(chambreResponseDto);
        }

        // Delete
        @DeleteMapping("chambre/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id) {
            chambreService.delete(id);
            return ResponseEntity.noContent().build();
        }
    }


