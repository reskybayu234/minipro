package com.example.kurir254.kurir.repositories;

import com.example.kurir254.kurir.models.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepo extends JpaRepository <PaymentMethod, Long> {
}
