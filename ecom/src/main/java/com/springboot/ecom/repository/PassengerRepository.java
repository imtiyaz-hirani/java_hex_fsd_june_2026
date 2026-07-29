package com.springboot.ecom.repository;

import com.springboot.ecom.model.Passenger;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Optional<Passenger> findByContact(String contact);
}
