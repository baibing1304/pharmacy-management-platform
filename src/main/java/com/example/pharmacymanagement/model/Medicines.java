package com.example.pharmacymanagement.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "medicines")
@Getter
@Setter
public class Medicines {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //药品名称;非空，长度1-100字符;，长度1-100字符
    @ApiModelProperty(value = "药品名称", required = true, example = "阿司匹林")
    @NotBlank(message = "药品名称不能为空")
    @Size(min = 1, max = 100, message = "药品名称长度必须在1到100字符之间")
    @Column(name = "name", nullable = false, length = 100)
    private String name;


    



}