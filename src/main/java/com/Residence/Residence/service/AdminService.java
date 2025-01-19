package com.Residence.Residence.service;
import com.Residence.Residence.DTO.AdminRequestDto;
import com.Residence.Residence.DTO.AdminResponseDto;
import com.Residence.Residence.Entities.Administrateur;
import com.Residence.Residence.Repository.AdminRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AdminService(AdminRepository adminRepository, ModelMapper modelMapper) {
        this.adminRepository = adminRepository;
        this.modelMapper = modelMapper;
    }

    // Create
    public AdminResponseDto save(AdminRequestDto adminRequestDto) {
        Administrateur administrateur = modelMapper.map(adminRequestDto, Administrateur.class);
        Administrateur saved = adminRepository.save(administrateur);
        return modelMapper.map(saved, AdminResponseDto.class);
    }

    // Read All
    public List<AdminResponseDto> findAll() {
        return adminRepository.findAll().stream()
                .map(admin -> modelMapper.map(admin, AdminResponseDto.class))
                .collect(Collectors.toList());
    }

    // Read by ID
    public AdminResponseDto findById(Long id) {
        Administrateur administrateur = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrateur not found"));
        return modelMapper.map(administrateur, AdminResponseDto.class);
    }

    // Update
    public AdminResponseDto update(AdminRequestDto adminRequestDto, Long id) {
        Optional<Administrateur> adminOptional = adminRepository.findById(id);
        if (adminOptional.isPresent()) {
            Administrateur administrateur = adminOptional.get();
            modelMapper.map(adminRequestDto, administrateur);
            administrateur.setId(id);
            Administrateur updated = adminRepository.save(administrateur);
            return modelMapper.map(updated, AdminResponseDto.class);
        } else {
            throw new RuntimeException("Administrateur not found");
        }
    }

    // Delete
    public void delete(Long id) {
        adminRepository.deleteById(id);
    }
}