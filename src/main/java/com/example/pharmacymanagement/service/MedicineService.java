package com.example.pharmacymanagement.service;



import com.example.pharmacymanagement.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class MedicineService {
    @Autowired
    private MedicineRepository medicineRepository;
    

}
