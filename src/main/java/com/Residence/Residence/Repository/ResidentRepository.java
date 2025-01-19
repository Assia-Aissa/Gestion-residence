package com.Residence.Residence.Repository;

import com.Residence.Residence.Entities.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, Long> {
}
