package com.mailorderpharmacy.drugservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mailorderpharmacy.drugservice.entity.DrugDetails;
import com.mailorderpharmacy.drugservice.entity.DrugLocationDetails;
import com.mailorderpharmacy.drugservice.entity.Stock;
import com.mailorderpharmacy.drugservice.entity.SuccessResponse;
import com.mailorderpharmacy.drugservice.entity.TokenValid;
import com.mailorderpharmacy.drugservice.exception.DrugNotFoundException;
import com.mailorderpharmacy.drugservice.exception.InvalidTokenException;
import com.mailorderpharmacy.drugservice.exception.StockNotFoundException;
import com.mailorderpharmacy.drugservice.repository.DrugDetailsRepository;
import com.mailorderpharmacy.drugservice.repository.DrugLocationRepository;
import com.mailorderpharmacy.drugservice.restclients.AuthFeign;

@Service
public class DrugDetailsServiceImpl implements DrugDetailsService
{
    @Autowired
    private DrugDetailsRepository drugRepo;
    @Autowired
    private DrugLocationRepository locationRepo;
    @Autowired
    private AuthFeign authFeign;
    
    @Override
    public DrugDetails getDrugById(final String id, final String token) throws InvalidTokenException, DrugNotFoundException {
        DrugDetails drugDetails = null;
        if (((TokenValid)this.authFeign.getValidity(token).getBody()).isValid()) {
            drugDetails = this.drugRepo.findById(id).orElseThrow(() -> new DrugNotFoundException("Drug Not Found"));
            return drugDetails;
        }
        throw new InvalidTokenException("Invalid Credentials");
    }
    
    @Override
    public DrugDetails getDrugByName(final String name, final String token) throws InvalidTokenException, DrugNotFoundException {
        if (((TokenValid)this.authFeign.getValidity(token).getBody()).isValid()) {
            try {
                return this.drugRepo.findBydrugName(name).get();
            }
            catch (Exception e) {
                throw new DrugNotFoundException("Drug Not Found");
            }
        }
        throw new InvalidTokenException("Invalid Credentials");
    }
    
    @Override
    public Stock getDispatchableDrugStock(final String id, final String location, final String token) throws InvalidTokenException, StockNotFoundException, DrugNotFoundException {
        if (!((TokenValid)this.authFeign.getValidity(token).getBody()).isValid()) {
            throw new InvalidTokenException("Invalid Credentials");
        }
        DrugDetails details = null;
        try {
            details = this.drugRepo.findById(id).get();
        }
        catch (Exception e) {
            throw new DrugNotFoundException("Drug Not Found");
        }
        Stock stock = null;
        for (DrugLocationDetails dld : details.getDruglocationQuantities()) {
            if (dld.getLocation().equals(location)) {
                stock = new Stock(id, details.getDrugName(), details.getExpiryDate(), dld.getQuantity());
            }
        }
        if (stock == null) {
            throw new StockNotFoundException("Stock Unavailable at your location");
        }
        return stock;
    }
    

    @Override
    public ResponseEntity<SuccessResponse> updateQuantity(final String drugName, final String location, final int quantity, final String token) throws InvalidTokenException, DrugNotFoundException, StockNotFoundException {
        if (!((TokenValid)this.authFeign.getValidity(token).getBody()).isValid()) {
            throw new InvalidTokenException("Invalid Credentials");
        }
        DrugDetails details = new DrugDetails();
        try {
            details = this.drugRepo.findBydrugName(drugName).get();
        }
        catch (Exception e) {
            throw new DrugNotFoundException("Drug Not Found");
        }
        final List<DrugLocationDetails> dummy = details.getDruglocationQuantities().stream().filter(x -> x.getLocation().equalsIgnoreCase(location)).collect(Collectors.toList());
        if (dummy.size() == 0) {
            throw new StockNotFoundException("Stock Unavailable at your location");
        }
        if (dummy.get(0).getQuantity() >= quantity) {
            final DrugLocationDetails tempDetails = this.locationRepo.findByserialId(dummy.get(0).getSerialId()).get(0);
            final int val = tempDetails.getQuantity() - quantity;
            tempDetails.setQuantity(val);
            this.locationRepo.save(tempDetails);
            return (ResponseEntity<SuccessResponse>)new ResponseEntity((Object)new SuccessResponse("Refill Done Successfully"), HttpStatus.OK);
        }
        throw new StockNotFoundException("Stock Unavailable at your location");
    }
    
    @Override
    public List<DrugDetails> getAllDrugs() {
        final List<DrugDetails> drugList = (List<DrugDetails>)this.drugRepo.findAll();
        return drugList;
    }
}