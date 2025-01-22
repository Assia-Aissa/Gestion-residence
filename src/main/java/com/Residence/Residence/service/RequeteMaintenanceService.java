package com.Residence.Residence.service;
import com.Residence.Residence.DTO.RequeteMaintenanceRequestDTO;
import com.Residence.Residence.Entities.RequeteMaintenance;
import com.Residence.Residence.Repository.RequeteMaintenanceRepository;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.Residence.Residence.DTO.RequeteMaintenanceResponseDTO;
import com.Residence.Residence.Entities.Resident;
import com.Residence.Residence.Entities.Technicien;
import com.Residence.Residence.Entities.Chambre;
import com.Residence.Residence.Entities.StatutRequete;
import com.Residence.Residence.Repository.ResidentRepository;
import com.Residence.Residence.Repository.TechnicienRepository;
import com.Residence.Residence.Repository.ChambreRepository;
import org.modelmapper.ModelMapper;

import java.util.Date;

import java.util.stream.Collectors;

@Service
public class RequeteMaintenanceService {

    @Autowired
    private RequeteMaintenanceRepository requeteMaintenanceRepository;

    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private TechnicienRepository technicienRepository;

    @Autowired
    private ChambreRepository chambreRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Create a new maintenance request
    public RequeteMaintenanceResponseDTO createRequeteMaintenance(RequeteMaintenanceRequestDTO requestDTO) {
        // Explicit mapping setup
        ModelMapper modelMapper = new ModelMapper();

        RequeteMaintenance requeteMaintenance = modelMapper.map(requestDTO, RequeteMaintenance.class);

        requeteMaintenance.setDateSignalement(new Date());
        requeteMaintenance.setStatut(StatutRequete.EN_ATTENTE);

        // Save the entity and return the response DTO
        RequeteMaintenance savedRequete = requeteMaintenanceRepository.save(requeteMaintenance);
        return modelMapper.map(savedRequete, RequeteMaintenanceResponseDTO.class);
    }


    // Get all maintenance requests
    public List<RequeteMaintenanceResponseDTO> getAllRequeteMaintenances() {
        List<RequeteMaintenance> requetes = requeteMaintenanceRepository.findAll();
        return requetes.stream()
                .map(requete -> modelMapper.map(requete, RequeteMaintenanceResponseDTO.class))
                .collect(Collectors.toList());
    }

    // Get a maintenance request by ID
    public RequeteMaintenanceResponseDTO getRequeteMaintenanceById(Long id) {
        Optional<RequeteMaintenance> requete = requeteMaintenanceRepository.findById(id);
        return requete.map(value -> modelMapper.map(value, RequeteMaintenanceResponseDTO.class))
                .orElseThrow(() -> new RuntimeException("RequeteMaintenance not found"));
    }

    // Update a maintenance request
    public RequeteMaintenanceResponseDTO updateRequeteMaintenance(Long id, RequeteMaintenanceRequestDTO requestDTO) {
        RequeteMaintenance existingRequete = requeteMaintenanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RequeteMaintenance not found"));

        modelMapper.map(requestDTO, existingRequete);
        existingRequete.setId(id); // Ensure the ID is not changed



        RequeteMaintenance updatedRequete = requeteMaintenanceRepository.save(existingRequete);
        return modelMapper.map(updatedRequete, RequeteMaintenanceResponseDTO.class);
    }

    // Delete a maintenance request
    public void deleteRequeteMaintenance(Long id) {
        requeteMaintenanceRepository.deleteById(id);
    }
}