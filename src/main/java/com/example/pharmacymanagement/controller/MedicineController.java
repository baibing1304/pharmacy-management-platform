package com.example.pharmacymanagement.controller;

import com.example.pharmacymanagement.model.Medicine;
import com.example.pharmacymanagement.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/medicines")
@Validated
public class MedicineController {

    @Autowired
    private MedicineService medicineService;


}