package com.mailorderpharmacy.subscription.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mailorderpharmacy.subscription.entity.PrescriptionDetails;
import com.mailorderpharmacy.subscription.entity.SubscriptionDetails;
import com.mailorderpharmacy.subscription.exceptions.InvalidTokenException;

public interface SubscriptionService {
    ResponseEntity<String> subscribe(PrescriptionDetails prescriptionDetails,String token) throws InvalidTokenException;
    ResponseEntity<String> unsubscribe(String mId,Long sId,String token) throws InvalidTokenException;
    
    List<SubscriptionDetails> getAllSubscriptions(String mId,String token) throws InvalidTokenException;
    
    ResponseEntity<String> getDrugNameBySubscriptionId(Long sId, String token) throws InvalidTokenException;
    
}
