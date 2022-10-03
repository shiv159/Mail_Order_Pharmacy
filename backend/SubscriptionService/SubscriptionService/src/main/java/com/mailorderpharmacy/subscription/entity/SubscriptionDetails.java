package com.mailorderpharmacy.subscription.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.ToString;


@Entity
@ToString
public class SubscriptionDetails {
	public SubscriptionDetails()
	{
		
	}
	public SubscriptionDetails(final Long prescriptionId, final int refillCycle, final int quantity,
			final String memberId, final LocalDate subscriptionDate, final String memberLocation,
			final String subscriptionStatus, final String drugName) {
		super();
		this.prescriptionId = prescriptionId;
		this.memberId = memberId;
		this.subscriptionDate = subscriptionDate;
		this.memberLocation = memberLocation;
		this.subscriptionStatus = subscriptionStatus;
		this.refillCycle = refillCycle;
		this.quantity = quantity;
		this.drugName = drugName;
	}
	
	/*public SubscriptionDetails(Long subscriptionId, Long prescriptionId, String drugName, int refillCycle, int quantity,
			String memberId, LocalDate subscriptionDate, String memberLocation, String subscriptionStatus) {
		super();
		this.subscriptionId = subscriptionId;
		this.prescriptionId = prescriptionId;
		this.drugName = drugName;
		this.refillCycle = refillCycle;
		this.quantity = quantity;
		this.memberId = memberId;
		this.subscriptionDate = subscriptionDate;
		this.memberLocation = memberLocation;
		this.subscriptionStatus = subscriptionStatus;
	}*/

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long subscriptionId;
	private Long prescriptionId;
	private String drugName;
	private int refillCycle;
	private int quantity;
	private String memberId;
	private LocalDate subscriptionDate;
	private String memberLocation;
	private String subscriptionStatus;
	public Long getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(Long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	public Long getPrescriptionId() {
		return prescriptionId;
	}
	public void setPrescriptionId(Long prescriptionId) {
		this.prescriptionId = prescriptionId;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public int getRefillCycle() {
		return refillCycle;
	}
	public void setRefillCycle(int refillCycle) {
		this.refillCycle = refillCycle;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public LocalDate getSubscriptionDate() {
		return subscriptionDate;
	}
	public void setSubscriptionDate(LocalDate subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}
	public String getMemberLocation() {
		return memberLocation;
	}
	public void setMemberLocation(String memberLocation) {
		this.memberLocation = memberLocation;
	}
	public String getSubscriptionStatus() {
		return subscriptionStatus;
	}
	public void setSubscriptionStatus(String subscriptionStatus) {
		this.subscriptionStatus = subscriptionStatus;
	}

	
	
}
