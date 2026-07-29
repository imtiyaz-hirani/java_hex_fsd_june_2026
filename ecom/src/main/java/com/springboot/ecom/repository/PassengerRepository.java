package com.springboot.ecom.repository;

import com.springboot.ecom.model.Passenger;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Optional<Passenger> findByContact(String contact);

    List<Passenger> findByIsActive(boolean isActive, Pageable pageable);
}
