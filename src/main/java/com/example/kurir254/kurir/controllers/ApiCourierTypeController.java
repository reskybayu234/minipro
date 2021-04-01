package com.example.kurir254.kurir.controllers;

import com.example.kurir254.kurir.models.Courier;
import com.example.kurir254.kurir.models.CourierType;
import com.example.kurir254.kurir.repositories.CourierTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ApiCourierTypeController {

    @Autowired
    private CourierTypeRepo courierTypeRepo;

    @GetMapping("/couriertype")
    public ResponseEntity<List<CourierType>> GetAllCourierType()
    {
        try
        {
            List<CourierType> courierType = this.courierTypeRepo.findAll();
            return new ResponseEntity<>(courierType, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/couriertype")
    public ResponseEntity<Object> SaveCourierType(@RequestBody CourierType courierType, Error error)
    {
        CourierType courierTypeData = this.courierTypeRepo.save(courierType);
        try
        {
            return new ResponseEntity<>("success", HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/couriertype/{id}")
    public ResponseEntity<Object> UpdateCourierType(@RequestBody CourierType courierType, @PathVariable("id") Long id)
    {
        Optional<CourierType> courierTypeData = this.courierTypeRepo.findById(id);
        if (courierTypeData.isPresent())
        {
            courierType.setId(id);
            this.courierTypeRepo.save(courierType);
            ResponseEntity rest = new ResponseEntity<>("success", HttpStatus.OK);
            return rest;
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/couriertype/{id}")
    public ResponseEntity<List<CourierType>> GetCourierTypeById(@PathVariable("id") Long id)
    {
        try
        {
            Optional<CourierType> courierType = this.courierTypeRepo.findById(id);
            if (courierType.isPresent())
            {
                ResponseEntity rest = new ResponseEntity<>(courierType, HttpStatus.OK);
                return rest;
            }
            else
                {
                return ResponseEntity.notFound().build();
            }
        }
        catch (Exception exception)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/couriertypemapped")
    public ResponseEntity<Map<String, Object>> GetAllPage(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="5") int size)
    {
        try
        {
            List<CourierType> courierType = new ArrayList<>();
            Pageable pagingSort = PageRequest.of(page,size);

            Page<CourierType> pageTuts;

            pageTuts = courierTypeRepo.findAll(pagingSort);

            courierType = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("courierType", courierType);
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
}
