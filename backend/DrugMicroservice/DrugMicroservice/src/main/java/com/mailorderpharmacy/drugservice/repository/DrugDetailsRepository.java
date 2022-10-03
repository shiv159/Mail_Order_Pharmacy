package com.mailorderpharmacy.drugservice.repository;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.mailorderpharmacy.drugservice.entity.DrugDetails;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DrugDetailsRepository extends JpaRepository<DrugDetails, String>
{
    Optional<DrugDetails> findById(String id);
    
    Optional<DrugDetails> findBydrugName(String name);
}