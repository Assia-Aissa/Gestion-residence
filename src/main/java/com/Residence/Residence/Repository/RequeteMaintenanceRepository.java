package com.Residence.Residence.Repository;

import com.Residence.Residence.Entities.RequeteMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequeteMaintenanceRepository extends JpaRepository<RequeteMaintenance, Long> {
}
