package com.mailorderpharmacy.subscription.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mailorderpharmacy.subscription.entity.PrescriptionDetails;

public interface PrescriptionRepository extends JpaRepository<PrescriptionDetails,Long> {

}
