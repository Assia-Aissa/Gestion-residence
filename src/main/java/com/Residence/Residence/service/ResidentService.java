package com.Residence.Residence.service;


import com.Residence.Residence.DTO.ResidentRequestDto;
import com.Residence.Residence.DTO.ResidentResponseDto;
import com.Residence.Residence.Entities.Chambre;
import com.Residence.Residence.Entities.Resident;
import com.Residence.Residence.Repository.ChambreRepository;
import com.Residence.Residence.Repository.ResidentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    // Create
    public ResidentResponseDto save(ResidentRequestDto residentRequestDto) {
        Resident resident = modelMapper.map(residentRequestDto, Resident.class);

        // Set the Chambre
        if (residentRequestDto.getChambreId() != null) {
            Chambre chambre = chambreRepository.findById(residentRequestDto.getChambreId())
                    .orElseThrow(() -> new RuntimeException("Chambre not found"));
            resident.setChambre(chambre);
        }

        Resident saved = residentRepository.save(resident);
        return modelMapper.map(saved, ResidentResponseDto.class);
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
    public ResidentResponseDto update(ResidentRequestDto residentRequestDto, Long id) {
        Optional<Resident> residentOptional = residentRepository.findById(id);
        if (residentOptional.isPresent()) {
            Resident resident = residentOptional.get();
            modelMapper.map(residentRequestDto, resident);

            // Update the Chambre if the ID changes
           if (residentRequestDto.getChambreId() != null) {
                Chambre chambre = chambreRepository.findById(residentRequestDto.getChambreId())
                        .orElseThrow(() -> new RuntimeException("Chambre not found"));
                resident.setChambre(chambre);
            }

            Resident updated = residentRepository.save(resident);
            return modelMapper.map(updated, ResidentResponseDto.class);
        } else {
            throw new RuntimeException("Resident not found");
        }
    }

    // Delete
    public void delete(Long id) {
        residentRepository.deleteById(id);
    }
}