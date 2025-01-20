package com.Residence.Residence.service;

import com.Residence.Residence.Entities.Administrateur;
import com.Residence.Residence.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    // Create
    public Administrateur save(Administrateur administrateur) {
        return adminRepository.save(administrateur);
    }

    // Read All
    public List<Administrateur> findAll() {
        return adminRepository.findAll();
    }

    // Read by ID
    public Administrateur findById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrateur not found"));
    }

    // Update
    public Administrateur update(Administrateur administrateur, Long id) {
        Optional<Administrateur> adminOptional = adminRepository.findById(id);
        if (adminOptional.isPresent()) {
            Administrateur existingAdmin = adminOptional.get();
            existingAdmin.setNom(administrateur.getNom());
            existingAdmin.setPrenom(administrateur.getPrenom());
            existingAdmin.setEmail(administrateur.getEmail());
            existingAdmin.setMotDePasse(administrateur.getMotDePasse());
            existingAdmin.setRole(administrateur.getRole());
            return adminRepository.save(existingAdmin);
        } else {
            throw new RuntimeException("Administrateur not found");
        }
    }

    // Delete
    public void delete(Long id) {
        adminRepository.deleteById(id);
    }
}