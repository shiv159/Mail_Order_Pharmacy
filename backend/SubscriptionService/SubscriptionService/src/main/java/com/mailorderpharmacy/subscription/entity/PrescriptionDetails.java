package com.mailorderpharmacy.subscription.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;




@Entity
public class PrescriptionDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long prescriptionId;
	private String memberId;
	private String memberLocation;
	private String policyNumber;
	private String insuranceProvider;
	private LocalDate prescriptionDate;
	private String drugName;
	private String dosageDefinition;
	private int quantity;
	private int courseDuration; 
	private String doctorName;
	public Long getPrescriptionId() {
		return prescriptionId;
	}
	public void setPrescriptionId(Long prescriptionId) {
		this.prescriptionId = prescriptionId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberLocation() {
		return memberLocation;
	}
	public void setMemberLocation(String memberLocation) {
		this.memberLocation = memberLocation;
	}
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	public String getInsuranceProvider() {
		return insuranceProvider;
	}
	public void setInsuranceProvider(String insuranceProvider) {
		this.insuranceProvider = insuranceProvider;
	}
	public LocalDate getPrescriptionDate() {
		return prescriptionDate;
	}
	public void setPrescriptionDate(LocalDate prescriptionDate) {
		this.prescriptionDate = prescriptionDate;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public String getDosageDefinition() {
		return dosageDefinition;
	}
	public void setDosageDefinition(String dosageDefinition) {
		this.dosageDefinition = dosageDefinition;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getCourseDuration() {
		return courseDuration;
	}
	public void setCourseDuration(int courseDuration) {
		this.courseDuration = courseDuration;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public PrescriptionDetails(Long prescriptionId, String memberId, String memberLocation, String policyNumber,
			String insuranceProvider, LocalDate prescriptionDate, String drugName, String dosageDefinition,
			int quantity, int courseDuration, String doctorName) {
		super();
		this.prescriptionId = prescriptionId;
		this.memberId = memberId;
		this.memberLocation = memberLocation;
		this.policyNumber = policyNumber;
		this.insuranceProvider = insuranceProvider;
		this.prescriptionDate = prescriptionDate;
		this.drugName = drugName;
		this.dosageDefinition = dosageDefinition;
		this.quantity = quantity;
		this.courseDuration = courseDuration;
		this.doctorName = doctorName;
	}
	public PrescriptionDetails() {
		
	}


}
