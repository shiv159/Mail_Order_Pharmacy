package com.cts.refill.controller;
import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.refill.exception.DrugQuantityNotAvailable;
import com.cts.refill.exception.InvalidTokenException;
import com.cts.refill.exception.SubscriptionIDNotFoundException;
import com.cts.refill.model.RefillOrder;
import com.cts.refill.model.RefillOrderLine;
import com.cts.refill.service.IRefillOrder;
import com.cts.refill.service.IRefillOrderSubscription;
import com.fasterxml.jackson.databind.node.ObjectNode;

import feign.FeignException;


@RestController
@CrossOrigin
public class RefillController {

	@Autowired
	private IRefillOrder oservice;
	
	@Autowired
	private IRefillOrderSubscription subservice;

	@GetMapping(value = "/viewRefillStatus/{subID}")
	public ResponseEntity<List<RefillOrder>> viewRefillStatus(@RequestHeader("Authorization") String token,@PathVariable long subID)
			throws SubscriptionIDNotFoundException, InvalidTokenException {
		List<RefillOrder> list = oservice.getStatus(subID,token);
		return new ResponseEntity<List<RefillOrder>>(list, HttpStatus.OK);
	}

	@GetMapping(value = "/getRefillDuesAsOfDate/{memberID}/{date}")
	public ResponseEntity<List<RefillOrderLine>> getRefillDuesAsOfDate(@RequestHeader("Authorization") String token,@PathVariable("date") int date,
			@PathVariable("memberID") String memberID) throws InvalidTokenException {
		return new ResponseEntity<List<RefillOrderLine>>(oservice.getRefillDuesAsOfDate(memberID, date,token), HttpStatus.OK);
	}

	@PostMapping(value = "/requestAdhocRefill")
	public ResponseEntity<RefillOrder> requestAdhocRefill(@RequestHeader("Authorization") String token,@RequestBody ObjectNode objectNode)
			throws ParseException, DrugQuantityNotAvailable, InvalidTokenException,FeignException {
		long policyId = objectNode.get("policyID").asLong();
		long subID = objectNode.get("subID").asLong();
		String memberID = objectNode.get("memberID").asText();
		String location = objectNode.get("location").asText();
		int quantity=objectNode.get("quantity").asInt();
		return new ResponseEntity<RefillOrder>(oservice.requestAdhocRefill(policyId, subID, memberID, location,quantity,token),
				HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/getRefillPaymentDues/{subID}")
	public ResponseEntity<Boolean> getRefillPaymentDues(@RequestHeader("Authorization") String token,@PathVariable("subID") long subID) throws InvalidTokenException {
		return new ResponseEntity<Boolean>(oservice.getRefillPaymentDues(subID,token), HttpStatus.OK);
	}
	
	@PostMapping(path = "/requestRefillSubscription/{subId}/{memberId}/{quantity}/{time}")
	public ResponseEntity<RefillOrderLine> requestRefillSubscription(
			@RequestHeader("Authorization") String token, @PathVariable("subId") long subId,
			@PathVariable("memberId") String memberId, @PathVariable("quantity") int quantity,
			@PathVariable("time") int time) throws ParseException, InvalidTokenException {
		return ResponseEntity.accepted().body(
				subservice.updateRefillOrderSubscription(subId, memberId, quantity, time ,token));
	}
	
	@GetMapping(value = "/viewRefillOrderSubscriptionStatus")
	public ResponseEntity<List<RefillOrderLine>> viewRefillOrderSubscriptionStatus(@RequestHeader("Authorization") String token) throws InvalidTokenException{
		return new ResponseEntity<List<RefillOrderLine>>(subservice.getAllSubscription(token),HttpStatus.OK);
	}

	@DeleteMapping(value = "/deleteBySubscriptionId/{subID}")
	public void deleteBySubscriptionId(@RequestHeader("Authorization") String token,@PathVariable("subID") long subID) throws InvalidTokenException {
		subservice.deleteBySubscriptionId(subID,token);
	}
	
}
