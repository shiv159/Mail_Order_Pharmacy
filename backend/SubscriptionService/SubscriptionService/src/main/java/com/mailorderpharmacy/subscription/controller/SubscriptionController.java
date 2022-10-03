package com.mailorderpharmacy.subscription.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


import com.mailorderpharmacy.subscription.entity.PrescriptionDetails;
import com.mailorderpharmacy.subscription.entity.SubscriptionDetails;
import com.mailorderpharmacy.subscription.exceptions.InvalidTokenException;
import com.mailorderpharmacy.subscription.exceptions.SubscriptionListEmptyException;
import com.mailorderpharmacy.subscription.services.SubscriptionService;

@CrossOrigin
@RestController
public class SubscriptionController {
	
	@Autowired
	private SubscriptionService subscriptionService;
	String msg = "Not subscribed";
	
	@PostMapping("/subscribe")
	public ResponseEntity<String> subscribe(@RequestHeader("Authorization") String token,
			@RequestBody PrescriptionDetails prescriptionDetails) throws InvalidTokenException {
		try {
			return subscriptionService.subscribe(prescriptionDetails, token);
		} catch (InvalidTokenException e){
			throw new SubscriptionListEmptyException(msg);
		}
	}
	
	@PostMapping("/unsubscribe/{mId}/{sId}")
	public ResponseEntity<String> unsubscribe(@RequestHeader("Authorization") String token,
			@PathVariable("mId") String memberId, @PathVariable("sId") Long subscriptionId)
			 throws InvalidTokenException {
		try {
			return subscriptionService.unsubscribe(memberId, subscriptionId, token);
		} catch (InvalidTokenException e){
			throw new InvalidTokenException(msg);
		}
	}
	
	@GetMapping("/getAllSubscriptions/{mId}")
	public List<SubscriptionDetails> getAllSubscriptionsforMember(@RequestHeader("Authorization") String token,
			@PathVariable("mId") String mId) throws InvalidTokenException{
		try {
			return subscriptionService.getAllSubscriptions(mId, token);
		}catch (InvalidTokenException e){
			throw new InvalidTokenException(msg);
		}
	}
	
	@GetMapping("/getDrugName/{sId}")
	public ResponseEntity<String> getDrugNameBySubscriptionId(@RequestHeader("Authorization") String token,
			@PathVariable("sId") Long sId) throws InvalidTokenException{
		try {
			return subscriptionService.getDrugNameBySubscriptionId(sId, token);
		}catch (Exception e){
			throw new InvalidTokenException(msg);
		}
	}
	
}
