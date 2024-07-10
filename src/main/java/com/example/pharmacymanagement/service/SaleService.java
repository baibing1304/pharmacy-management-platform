package com.example.pharmacymanagement.service;

import com.example.pharmacymanagement.model.Inventory;
import com.example.pharmacymanagement.model.Medicine;
import com.example.pharmacymanagement.model.Sale;
import com.example.pharmacymanagement.repository.InventoryRepository;
import com.example.pharmacymanagement.repository.MedicineRepository;
import com.example.pharmacymanagement.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional
    public Sale recordSale(Long medicineId, Integer quantity, BigDecimal totalPrice, LocalDate saleDate) {
        Optional<Inventory> inventory = inventoryRepository.findByMedicineId(medicineId);
        if (inventory.get().getQuantity() < quantity) {
            throw new ValidationException("库存数量不足");
        }
        inventory.get().setQuantity(inventory.get().getQuantity() - quantity);
        Sale sale = new Sale();
        sale.setMedicineId(medicineId);
        sale.setQuantity(quantity);
        sale.setTotalPrice(totalPrice);
        sale.setSaleDate(saleDate);
        saleRepository.save(sale);
        return sale;
    }

    public List<Sale> getSalesByDate(LocalDate saleDate) {
        return saleRepository.findBySaleDate(saleDate);
    }

    public List<Sale> getSalesByDateBetween(LocalDate startDate, LocalDate endDate) {
        return saleRepository.findBySaleDateBetween(startDate, endDate);
    }

    public BigDecimal getTotalPriceByDate(LocalDate saleDate) {
        return saleRepository.sumTotalPriceBySaleDate(saleDate);
    }

    public BigDecimal getTotalPriceByDateBetween(LocalDate startDate, LocalDate endDate) {
        return saleRepository.sumTotalPriceBySaleDateBetween(startDate, endDate);
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Optional<Sale> getSaleById(Long id) {
        return saleRepository.findById(id);
    }

    public void deleteSale(Long id) {
        saleRepository.deleteById(id);
    }

    public void deleteAllSales() {
        saleRepository.deleteAll();
    }

}