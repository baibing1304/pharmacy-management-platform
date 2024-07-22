package com.example.pharmacymanagement.repository;

import com.example.pharmacymanagement.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    Medicine findByName(String name);
}
