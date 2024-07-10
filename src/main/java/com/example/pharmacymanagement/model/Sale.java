package com.example.pharmacymanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
@Getter
@Setter
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //药品ID
    @Column(name = "medicine_id", nullable = false, unique = true)
    private Long medicineId;

    //销售数量; 非空，正整数
    @NotNull(message = "销售数量不能为空")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    //总价; 非空，精度为两位小数
    @NotNull(message = "总价不能为空")
    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    //销售日期; 非空，格式为YYYY-MM-DD
    @NotNull(message = "销售日期不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate saleDate;

    //创建时间
    @NotNull(message = "创建时间不能为空")
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    //更新时间
    @NotNull(message = "更新时间不能为空")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", medicineId=" + medicineId +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", saleDate=" + saleDate +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}