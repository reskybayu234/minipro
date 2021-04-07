package com.example.kurir254.kurir.controllers;

import com.example.kurir254.kurir.models.CustomerRegisteredCard;
import com.example.kurir254.kurir.repositories.CustomerRegisteredCardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ApiCustomerRegisteredCardController {

    @Autowired
    private CustomerRegisteredCardRepo customerRegisteredCardRepo;

    @GetMapping("/customercard")
    public ResponseEntity<List<CustomerRegisteredCard>> GetAllCustomerRegisteredCard() {
        try {
            List<CustomerRegisteredCard> customerRegisteredCard = this.customerRegisteredCardRepo.findAll();
            return new ResponseEntity<>(customerRegisteredCard, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/customercard")
    public ResponseEntity<Object> SaveCustomerCard(@RequestBody CustomerRegisteredCard customerRegisteredCard) {
        CustomerRegisteredCard customerRegisteredCardData = this.customerRegisteredCardRepo.save(customerRegisteredCard);
        try {
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (
                Exception e) {
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }
    }
}
