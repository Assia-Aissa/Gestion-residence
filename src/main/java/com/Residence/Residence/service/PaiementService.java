package com.Residence.Residence.service;

import com.Residence.Residence.DTO.PaiementRequestDto;
import com.Residence.Residence.DTO.PaiementResponseDto;
import com.Residence.Residence.Entities.Paiement;
import com.Residence.Residence.Entities.Resident;
import com.Residence.Residence.Repository.PaiementRepository;
import com.Residence.Residence.Repository.ResidentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class PaiementService {
    private final PaiementRepository paiementRepository;
    private final ResidentRepository residentRepository;
    private final ModelMapper modelMapper;


    public PaiementService(PaiementRepository paiementRepository, ResidentRepository residentRepository, ModelMapper modelMapper) {
        this.paiementRepository = paiementRepository;
        this.residentRepository = residentRepository;
        this.modelMapper = modelMapper;
    }

    // Create


    public PaiementResponseDto save(PaiementRequestDto paiementRequestDto) {
        Paiement paiement = modelMapper.map(paiementRequestDto, Paiement.class);

        // Set the Resident
        Resident resident = residentRepository.findById(paiementRequestDto.getResidentId())
                .orElseThrow(() -> new RuntimeException("Resident not found"));
        paiement.setResident(resident);

        Paiement saved = paiementRepository.save(paiement);

        // Map the saved Paiement to PaiementResponseDto
        PaiementResponseDto responseDto = modelMapper.map(saved, PaiementResponseDto.class);
        responseDto.setResidentNom(resident.getNom()); // Include resident's name
        responseDto.setResidentPrenom(resident.getPrenom()); // Include resident's first name

        return responseDto;
    }
    public List<PaiementResponseDto> getPaiementsByResidentId(Long residentId) {
        return paiementRepository.findByResidentId(residentId).stream()
                .map(paiement -> modelMapper.map(paiement, PaiementResponseDto.class))
                .collect(Collectors.toList());
    }
    // Read All
    public List<PaiementResponseDto> findAll() {
        return paiementRepository.findAll().stream()
                .map(paiement -> modelMapper.map(paiement, PaiementResponseDto.class))
                .collect(Collectors.toList());
    }

    // Read by ID
    public PaiementResponseDto findById(Long id) {
        Paiement paiement = paiementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paiement not found"));
        return modelMapper.map(paiement, PaiementResponseDto.class);
    }

    // Update
    public PaiementResponseDto update(PaiementRequestDto paiementRequestDto, Long id) {
        Optional<Paiement> paiementOptional = paiementRepository.findById(id);
        if (paiementOptional.isPresent()) {
            Paiement paiement = paiementOptional.get();
            modelMapper.map(paiementRequestDto, paiement);

            // Update the Resident if the ID changes
            if (paiementRequestDto.getResidentId() != null) {
                Resident resident = residentRepository.findById(paiementRequestDto.getResidentId())
                        .orElseThrow(() -> new RuntimeException("Resident not found"));
                paiement.setResident(resident);
            }

            Paiement updated = paiementRepository.save(paiement);
            return modelMapper.map(updated, PaiementResponseDto.class);
        } else {
            throw new RuntimeException("Paiement not found");
        }
    }

    // Delete
    public void delete(Long id) {
        paiementRepository.deleteById(id);
    }
}
