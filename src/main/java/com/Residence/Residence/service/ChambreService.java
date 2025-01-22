package com.Residence.Residence.service;

import com.Residence.Residence.DTO.AdminResponseDto;
import com.Residence.Residence.DTO.ChambreRequestDto;
import com.Residence.Residence.DTO.ChambreResponseDto;
import com.Residence.Residence.Entities.Chambre;
import com.Residence.Residence.Entities.StatutChambre;
import com.Residence.Residence.Repository.ChambreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChambreService  {


        private final ChambreRepository chambreRepository;
        private final ModelMapper modelMapper;

        @Autowired
        public ChambreService(ChambreRepository chambreRepository, ModelMapper modelMapper) {
            this.chambreRepository = chambreRepository;
            this.modelMapper = modelMapper;
        }

    public List<ChambreResponseDto> getAvailableChambres() {
        List<Chambre> availableChambres = chambreRepository.findByStatut(StatutChambre.DISPONIBLE);
        return availableChambres.stream()
                .map(chambre -> modelMapper.map(chambre, ChambreResponseDto.class))
                .collect(Collectors.toList());
    }
        // Create
        public ChambreResponseDto save(ChambreRequestDto chambreRequestDto) {
            Chambre chambre = modelMapper.map(chambreRequestDto, Chambre.class);
            Chambre saved = chambreRepository.save(chambre);
            return modelMapper.map(saved, ChambreResponseDto.class);
        }

        // Read All


     public List<ChambreResponseDto> getAllChambres() {
        List<Chambre> chambres = chambreRepository.findAll();
        return chambres.stream()
                .map(chambre -> modelMapper.map(chambre, ChambreResponseDto.class))
                .collect(Collectors.toList());
    }

    // Read by ID
        public ChambreResponseDto findById(Long id) {
            Chambre chambre = chambreRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Chambre not found"));
            return modelMapper.map(chambre, ChambreResponseDto.class);
        }

        // Update
        public ChambreResponseDto update(ChambreRequestDto chambreRequestDto, Long id) {
            Optional<Chambre> chambreOptional = chambreRepository.findById(id);

            if (chambreOptional.isPresent()) {
                Chambre chambre = chambreOptional.get();
                modelMapper.map(chambreRequestDto, chambre);
                chambre.setId(id);
                System.out.println("------------>"+chambre);
                Chambre updated = chambreRepository.save(chambre);
                System.out.println("------------>"+updated);
                return modelMapper.map(updated, ChambreResponseDto.class);
            } else {
                throw new RuntimeException("Chambre not found");
            }
        }

        // Delete
        public void delete(Long id) {
            chambreRepository.deleteById(id);
        }
    }



