package com.example.kurir254.kurir.controllers;

import com.example.kurir254.kurir.models.Bank;
import com.example.kurir254.kurir.repositories.BankRepo;
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
public class ApiBankController {

    @Autowired
    private BankRepo bankRepo;

    @GetMapping("/bank")
    public ResponseEntity<List<Bank>> GetAllBank()
    {
        try
        {
            List<Bank> bank = this.bankRepo.GetAvailable();
            return new ResponseEntity<>(bank, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/bank")
    public ResponseEntity<Object> SaveBank(@RequestBody Bank bank, Error error)
    {
        Bank bankData = this.bankRepo.save(bank);
        try
        {
            bank.setCreatedBy(1L);
            bank.setCreatedOn(new Date());
            this.bankRepo.save(bank);
            return new ResponseEntity<>("success", HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/bank/{id}")
    public ResponseEntity<Object> UpdateBank(@RequestBody Bank bank, @PathVariable("id") Long id)
    {
        Optional<Bank> bankData = this.bankRepo.findById(id);
        if (bankData.isPresent())
        {
            bank.setId(id);
            bank.setModifiedBy(1L);
            bank.setModifiedOn(new Date());
            this.bankRepo.save(bank);
            ResponseEntity rest = new ResponseEntity<>("success", HttpStatus.OK);
            return rest;
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/bank/{id}")
    public ResponseEntity<List<Bank>> GetBankById(@PathVariable("id") Long id)
    {
        try
        {
            Optional<Bank> bank = this.bankRepo.findById(id);
            if (bank.isPresent())
            {
                ResponseEntity rest = new ResponseEntity<>(bank, HttpStatus.OK);
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

    @GetMapping("/bankmapped")
    public ResponseEntity<Map<String, Object>> GetAllPage(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="5") int size)
    {
        try
        {
            List<Bank> bank = new ArrayList<>();
            Pageable pagingSort = PageRequest.of(page,size);

            Page<Bank> pageTuts;

            pageTuts = bankRepo.GetAllBankDelete(pagingSort);

            bank = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("bank", bank);
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

    @GetMapping("/searchbank/{keyword}")
    public ResponseEntity<List<Bank>> GetBankByName(@PathVariable("keyword") String keyword)
    {
        if(keyword != null)
        {
            List<Bank> bank = this.bankRepo.SearchBank(keyword);
            return new ResponseEntity<>(bank, HttpStatus.OK);
        }
        else
        {
            List<Bank> bank = this.bankRepo.findAll();
            return new ResponseEntity<>(bank,HttpStatus.OK);
        }
    }

    @DeleteMapping("/bank/{id}")
    public ResponseEntity<Object> DeleteBank(@PathVariable("id") Long id)
    {
        this.bankRepo.deleteById(id);
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }

    @GetMapping("/deletebank/{id}")
    public ResponseEntity<List<Bank>> GetDeleteBankById(@PathVariable("id") Long id)
    {
        try
        {
            Optional<Bank> bank = this.bankRepo.findById(id);
            if (bank.isPresent())
            {

                ResponseEntity responseEntity = new ResponseEntity<>(bank, HttpStatus.OK);
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

    @PutMapping("/deletebank/{id}")
    public ResponseEntity<Object> DeleteBank(@RequestBody Bank bank, @PathVariable("id") Long id)
    {
        Optional<Bank> bankData = this.bankRepo.findById(id);
        if(bankData.isPresent())
        {
            bank.setId(id);
            bank.setDeletedBy(1L);
            bank.setDeletedOn(new Date());
            bank.setDelete(true);
            this.bankRepo.save(bank);
            ResponseEntity responseEntity = new ResponseEntity<>("success",HttpStatus.OK);
            return responseEntity;
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

}
