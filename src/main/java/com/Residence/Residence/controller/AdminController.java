package com.Residence.Residence.controller;


import com.Residence.Residence.DTO.AdminRequestDto;
import com.Residence.Residence.DTO.AdminResponseDto;
import com.Residence.Residence.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Create
    @PostMapping
    public ResponseEntity<AdminResponseDto> save(@Valid @RequestBody AdminRequestDto adminRequestDto) {
        AdminResponseDto adminResponseDto = adminService.save(adminRequestDto);
        return new ResponseEntity<>(adminResponseDto, HttpStatus.CREATED);
    }

    // Read All
    @GetMapping
    public ResponseEntity<List<AdminResponseDto>> findAll() {
        List<AdminResponseDto> admins = adminService.findAll();
        return ResponseEntity.ok(admins);
    }

    // Read by ID
    @GetMapping("/{id}")
    public ResponseEntity<AdminResponseDto> findById(@PathVariable Long id) {
        AdminResponseDto adminResponseDto = adminService.findById(id);
        return ResponseEntity.ok(adminResponseDto);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<AdminResponseDto> update(
            @Valid @RequestBody AdminRequestDto adminRequestDto,
            @PathVariable Long id) {
        AdminResponseDto adminResponseDto = adminService.update(adminRequestDto, id);
        return ResponseEntity.ok(adminResponseDto);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        adminService.delete(id);
        return ResponseEntity.noContent().build();
    }
}