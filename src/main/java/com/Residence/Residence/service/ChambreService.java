package com.Residence.Residence.service;


import com.Residence.Residence.DTO.ChambreResponseDto;
import com.Residence.Residence.Entities.Chambre;
import com.Residence.Residence.Repository.ChambreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChambreService {

    private final ChambreRepository chambreRepository;

    @Autowired
    public ChambreService(ChambreRepository chambreRepository) {
        this.chambreRepository = chambreRepository;
    }

    // Create
    public ChambreResponseDto save(Chambre chambre) {
        Chambre saved = chambreRepository.save(chambre);
        return mapToDto(saved);
    }

    // Read All
    public List<ChambreResponseDto> findAll() {
        return chambreRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Read by ID
    public ChambreResponseDto findById(Long id) {
        Chambre chambre = chambreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chambre not found"));
        return mapToDto(chambre);
    }

    // Update
    public ChambreResponseDto update(Chambre chambre, Long id) {
        Optional<Chambre> chambreOptional = chambreRepository.findById(id);
        if (chambreOptional.isPresent()) {
            Chambre existingChambre = chambreOptional.get();
            existingChambre.setNumero(chambre.getNumero());
            existingChambre.setTaille(chambre.getTaille());
            existingChambre.setEquipements(chambre.getEquipements());
            existingChambre.setStatut(chambre.getStatut());
            Chambre updated = chambreRepository.save(existingChambre);
            return mapToDto(updated);
        } else {
            throw new RuntimeException("Chambre not found");
        }
    }

    // Delete
    public void delete(Long id) {
        chambreRepository.deleteById(id);
    }

    // Helper method to map Chambre to ChambreResponseDto
    private ChambreResponseDto mapToDto(Chambre chambre) {
        ChambreResponseDto dto = new ChambreResponseDto();
        dto.setId(chambre.getId());
        dto.setNumero(chambre.getNumero());
        dto.setTaille(chambre.getTaille());
        dto.setEquipements(chambre.getEquipements());
        dto.setStatut(chambre.getStatut());

        if (chambre.getResident() != null) {
            ChambreResponseDto.ResidentInfo residentInfo = new ChambreResponseDto.ResidentInfo();
            residentInfo.setNom(chambre.getResident().getNom());
            residentInfo.setPrenom(chambre.getResident().getPrenom());
            dto.setResident(residentInfo);
        }

        return dto;
    }
}
