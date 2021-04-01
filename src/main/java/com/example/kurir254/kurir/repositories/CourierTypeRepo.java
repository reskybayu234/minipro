package com.example.kurir254.kurir.repositories;

import com.example.kurir254.kurir.models.Courier;
import com.example.kurir254.kurir.models.CourierType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourierTypeRepo extends JpaRepository<CourierType, Long> {

    @Query("FROM Courier WHERE lower(Name) LIKE lower(concat('%',?1,'%'))")
    public List<Courier> SearchCourier(String keyword);

    @Query("SElect f FROM Courier f WHERE isDelete = false")
    Page<Courier> GetAllCourierTypeDelete(Pageable pageable);
}
