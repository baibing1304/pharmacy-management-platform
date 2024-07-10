package com.example.pharmacymanagement.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "inventory")
@Getter
@Setter
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //药品ID
    @ApiModelProperty(value = "药品ID", required = true, example = "1")
    @Column(name = "medicine_id", nullable = false, unique = true)
    private Long medicineId;

    //库存数量; 非空，正整数
    @ApiModelProperty(value = "库存数量", required = true, example = "100")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    //批次号; 非空，长度1-50字符
    @ApiModelProperty(value = "批次号", required = true, example = "20220101001")
    @NotBlank(message = "批次号不能为空")
    @Size(min = 1, max = 50, message = "批次号长度必须在1到50字符之间")
    @Column(name = "batch_number", nullable = false, length = 50)
    private String batchNumber;

    //有效期; 非空，格式为YYYY-MM-DD
    @ApiModelProperty(value = "有效期", required = true, example = "2022-12-31")
    @NotBlank(message = "有效期不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "expiration_date", nullable = false)
    private String expirationDate;

    //创建时间
    @NotBlank(message = "创建时间不能为空")
    @Column(name = "created_at")
    private String createdAt;

    //更新时间
    @NotBlank(message = "更新时间不能为空")
    @Column(name = "updated_at")
    private String updatedAt;


    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", medicineId=" + medicineId +
                ", quantity=" + quantity +
                ", batchNumber='" + batchNumber + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
