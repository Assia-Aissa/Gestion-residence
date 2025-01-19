package com.Residence.Residence.Repository;

import com.Residence.Residence.Entities.Chambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository

public interface ChambreRepository extends JpaRepository<Chambre, Long> {

}

