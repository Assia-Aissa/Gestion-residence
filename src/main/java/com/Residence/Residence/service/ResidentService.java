package com.Residence.Residence.service;

import com.Residence.Residence.DTO.ResidentRequestDto;
import com.Residence.Residence.DTO.ResidentResponseDto;
import com.Residence.Residence.Entities.Chambre;
import com.Residence.Residence.Entities.Resident;
import com.Residence.Residence.Entities.StatutChambre;
import com.Residence.Residence.Repository.ChambreRepository;
import com.Residence.Residence.Repository.ResidentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class ResidentService {

    private final ResidentRepository residentRepository;
    private final ChambreRepository chambreRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ResidentService(ResidentRepository residentRepository, ChambreRepository chambreRepository, ModelMapper modelMapper) {
        this.residentRepository = residentRepository;
        this.chambreRepository = chambreRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public ResidentResponseDto save(ResidentRequestDto residentRequestDto) {
        // Validate email
        if (residentRequestDto.getEmail() == null || residentRequestDto.getEmail().trim().isEmpty()) {
            throw new RuntimeException("Email cannot be empty or null.");
        }

        // Check if a resident with the same email already exists
        if (residentRepository.existsByEmail(residentRequestDto.getEmail())) {
            throw new RuntimeException("A resident with the same email already exists.");
        }

        // Find the chambre by its number
        Chambre chambre = chambreRepository.findByNumero(residentRequestDto.getChambreNumber())
                .orElseThrow(() -> new RuntimeException("Chambre not found"));

        // Check if the chambre is available
        if (chambre.getStatut() != StatutChambre.DISPONIBLE) {
            throw new RuntimeException("Chambre is not available.");
        }

        // Update chambre status to OCCUPEE
        chambre.setStatut(StatutChambre.OCCUPEE);
        chambreRepository.save(chambre);

        // Map ResidentRequestDto to Resident entity
        Resident resident = modelMapper.map(residentRequestDto, Resident.class);
        resident.setChambre(chambre); // Assign the chambre to the resident

        // Save the resident
        Resident savedResident = residentRepository.save(resident);

        // Map the saved resident to ResidentResponseDto
        ResidentResponseDto responseDto = modelMapper.map(savedResident, ResidentResponseDto.class);
        responseDto.setChambreNumber(savedResident.getChambre().getNumero());

        return responseDto;
    }

    // Read All
    public List<ResidentResponseDto> findAll() {
        return residentRepository.findAll().stream()
                .map(resident -> modelMapper.map(resident, ResidentResponseDto.class))
                .collect(Collectors.toList());
    }

    // Read by ID
    public ResidentResponseDto findById(Long id) {
        Resident resident = residentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resident not found"));
        return modelMapper.map(resident, ResidentResponseDto.class);
    }

    // Update
    @Transactional
    public ResidentResponseDto update(ResidentRequestDto residentRequestDto, Long id) {
        Optional<Resident> residentOptional = residentRepository.findById(id);
        if (residentOptional.isPresent()) {
            Resident resident = residentOptional.get();

            // Check if the email is being updated and if it already exists
            if (!resident.getEmail().equals(residentRequestDto.getEmail()) &&
                    residentRepository.existsByEmail(residentRequestDto.getEmail())) {
                throw new RuntimeException("A resident with the same email already exists.");
            }

            // Update resident details
            modelMapper.map(residentRequestDto, resident);

            // Save the updated resident
            Resident updated = residentRepository.save(resident);
            return modelMapper.map(updated, ResidentResponseDto.class);
        } else {
            throw new RuntimeException("Resident not found");
        }
    }

    // Delete
    @Transactional
    public void delete(Long id) {
        Optional<Resident> residentOptional = residentRepository.findById(id);
        if (residentOptional.isPresent()) {
            Resident resident = residentOptional.get();

            // Free the chambre if it's assigned
            if (resident.getChambre() != null) {
                Chambre chambre = resident.getChambre();
                chambre.setStatut(StatutChambre.DISPONIBLE);
                chambreRepository.save(chambre);
            }

            // Delete the resident
            residentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Resident not found");
        }
    }
}