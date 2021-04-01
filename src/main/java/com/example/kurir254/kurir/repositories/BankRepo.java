package com.example.kurir254.kurir.repositories;

import com.example.kurir254.kurir.models.Bank;
import com.example.kurir254.kurir.models.Courier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BankRepo extends JpaRepository<Bank, Long>{
    @Query("FROM Bank WHERE lower(Name) LIKE lower(concat('%',?1,'%'))")
    public List<Bank> SearchBank(String keyword);

    @Query("SElect f FROM Bank f WHERE isDelete = false")
    Page<Bank> GetAllBankDelete(Pageable pageable);
}
