package com.example.kurir254.kurir.controllers;

import com.example.kurir254.kurir.models.Courier;
import com.example.kurir254.kurir.repositories.CourierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ApiCourierController {

    @Autowired
    private CourierRepo courierRepo;

    @GetMapping("/courier")
    public ResponseEntity<List<Courier>> GetAllCourier()
    {
        try
        {
            List<Courier> courier = this.courierRepo.findAll();
            return new ResponseEntity<>(courier, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    @PostMapping("/courier")
    public ResponseEntity<Object> SaveCourier(@RequestBody Courier courier, Error error)
    {
        Courier courierData = this.courierRepo.save(courier);
        try
        {
            courier.setCreatedBy(1);
            courier.setCreatedOn(new Date());
            this.courierRepo.save(courier);
            return new ResponseEntity<>("success", HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
        }
    }

//    @DeleteMapping("/courier/{id}")
//    public ResponseEntity<Object> DeleteCourier(@PathVariable("id") Long id){
//        this.courierRepo.deleteById(id);
//        return new ResponseEntity<>("Success",HttpStatus.OK);
//    }

    @PutMapping("/courier/{id}")
    public ResponseEntity<Object> UpdateCourier(@RequestBody Courier courier, @PathVariable("id") Long id){
        Optional<Courier> courierData = this.courierRepo.findById(id);
        if(courierData.isPresent()){
            courier.setId(id);
            courier.setModifiedBy(1);
            courier.setModifiedOn(new Date());
            this.courierRepo.save(courier);
            ResponseEntity rest = new ResponseEntity<>("Success", HttpStatus.OK);
            return rest;
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/courier/{id}")
    public ResponseEntity<List<Courier>> GetCourierById(@PathVariable("id") Long id) {
        try {
            Optional<Courier> courier = this.courierRepo.findById(id);
            if (courier.isPresent()) {
                ResponseEntity rest = new ResponseEntity<>(courier, HttpStatus.OK);
                return rest;
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/couriermapped")
    public ResponseEntity<Map<String, Object>> getAllPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size)
    {
        try
        {
            List<Courier> courier = new ArrayList<>();
            Pageable pagingSort = PageRequest.of(page,size);

            Page<Courier> pageTuts;

            pageTuts = courierRepo.GetAllCourierDelete(pagingSort);

            courier = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("courier", courier);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItem", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchcourier/{keyword}")
    public ResponseEntity<List<Courier>> GetCourierByName(@PathVariable("keyword") String keyword)
    {
        if(keyword != null)
        {
            List<Courier> courier = this.courierRepo.SearchCourier(keyword);
            return new ResponseEntity<>(courier, HttpStatus.OK);
        }
        else
        {
            List<Courier> courier = this.courierRepo.findAll();
            return new ResponseEntity<>(courier,HttpStatus.OK);
        }
    }

    @PutMapping("/deletecourier/{id}")
    public ResponseEntity<Object> DeleteCoureir(@RequestBody Courier courier, @PathVariable("id") Long id)
    {
        Optional<Courier> courierData = this.courierRepo.findById(id);
        if(courierData.isPresent())
        {
            courier.setId(id);
            courier.setDeletedBy(1);
            courier.setDeletedOn(new Date());
            courier.setDelete(true);
            this.courierRepo.save(courier);
            ResponseEntity responseEntity = new ResponseEntity<>("success",HttpStatus.OK);
            return responseEntity;
        }
      else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/deletecourier/{id}")
    public ResponseEntity<List<Courier>> GetDeleteCourierById(@PathVariable("id") Long id)
    {
        try
        {
            Optional<Courier> courier = this.courierRepo.findById(id);
            if (courier.isPresent())
            {

                ResponseEntity responseEntity = new ResponseEntity<>(courier, HttpStatus.OK);
                return responseEntity;
            }
            else
            {
                return ResponseEntity.notFound().build();
            }
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


}
