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

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        List<Medicine> medicines = medicineService.getAllMedicines();
        return ResponseEntity.ok(medicines);
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Medicine> createMedicine(@Valid @RequestBody Medicine medicine) {
        Medicine createdMedicine = medicineService.createMedicine(medicine);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMedicine);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Medicine> getMedicine(@PathVariable @NotNull Long id) {
        Medicine medicine = medicineService.getMedicineById(id);
        return ResponseEntity.ok(medicine);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Medicine> updateMedicine(@PathVariable @NotNull Long id,
                                                   @Valid @RequestBody Medicine medicine) {
        Medicine updatedMedicine = medicineService.updateMedicine(id, medicine);
        return ResponseEntity.ok(updatedMedicine);
    }

    /**
     *
     @DeleteMapping("/{id}")
     @PreAuthorize("isAuthenticated()")
     public ResponseEntity<String> deleteMedicine(@PathVariable @NotNull Long id) {
     medicineService.deleteMedicine(id);
     return ResponseEntity.ok("药品已成功删除");
     */

    }
}