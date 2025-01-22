package com.Residence.Residence.service;

import com.Residence.Residence.DTO.AssignTechDto;
import com.Residence.Residence.DTO.TechnicienRequestDTO;
import com.Residence.Residence.DTO.TechnicienResponseDTO;
import com.Residence.Residence.Entities.RequeteMaintenance;
import com.Residence.Residence.Entities.Technicien;

import com.Residence.Residence.Repository.RequeteMaintenanceRepository;
import com.Residence.Residence.Repository.TechnicienRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TechnicienService {

    @Autowired
    private TechnicienRepository technicienRepository;


    @Autowired
    private ModelMapper modelMapper;

    // Create a new Technicien
    public TechnicienResponseDTO createTechnicien(TechnicienRequestDTO requestDTO) {
        // Map RequestDTO to Entity
        Technicien technicien = modelMapper.map(requestDTO, Technicien.class);
          System.out.println("--------------------"+technicien);
        // Save the entity
        Technicien savedTechnicien = technicienRepository.save(technicien);
            System.out.println("----------------------->"+savedTechnicien);
        // Map Entity to ResponseDTO
        return modelMapper.map(savedTechnicien, TechnicienResponseDTO.class);
    }

    // Get all Techniciens
    public List<TechnicienResponseDTO> getAllTechniciens() {
        return technicienRepository.findAll().stream()
                .map(technicien -> modelMapper.map(technicien, TechnicienResponseDTO.class))
                .collect(Collectors.toList());
    }

    // Get a Technicien by ID
    public TechnicienResponseDTO getTechnicienById(Long id) {
        Technicien technicien = technicienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Technicien not found"));
        return modelMapper.map(technicien, TechnicienResponseDTO.class);
    }

    // Update a Technicien
    public TechnicienResponseDTO updateTechnicien(Long id, TechnicienRequestDTO requestDTO) {
        Technicien technicien = technicienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Technicien not found"));

        // Map RequestDTO to existing Entity
        modelMapper.map(requestDTO, technicien);

        // Save the updated entity
        Technicien updatedTechnicien = technicienRepository.save(technicien);

        // Map Entity to ResponseDTO
        return modelMapper.map(updatedTechnicien, TechnicienResponseDTO.class);
    }

    // Delete a Technicien
    public void deleteTechnicien(Long id) {
        technicienRepository.deleteById(id);
    }


}
