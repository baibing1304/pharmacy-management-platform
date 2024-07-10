package com.example.pharmacymanagement.repository;

import com.example.pharmacymanagement.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findBySaleDate(LocalDate saleDate);
    List<Sale> findBySaleDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT SUM(s.totalPrice) FROM Sale s WHERE s.saleDate = :saleDate")
    BigDecimal sumTotalPriceBySaleDate(LocalDate saleDate);

    @Query("SELECT SUM(s.totalPrice) FROM Sale s WHERE s.saleDate BETWEEN :startDate AND :endDate")
    BigDecimal sumTotalPriceBySaleDateBetween(LocalDate startDate, LocalDate endDate);
}