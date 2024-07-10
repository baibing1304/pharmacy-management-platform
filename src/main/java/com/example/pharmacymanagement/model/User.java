package com.example.pharmacymanagement.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //用户名；非空，唯一，长度1-50字符
    @ApiModelProperty(value = "用户名", required = true, example = "admin")
    @NotBlank(message = "用户名不能为空")
    @Size(min = 1, max = 50, message = "用户名长度必须在1到50字符之间")
    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    //密码；非空，长度8-50字符，必须包含字母和数字
    @ApiModelProperty(value = "密码", required = true, example = "123456")
    @NotBlank(message = "密码不能为空")
    @Size(min = 8, max = 50, message = "密码长度必须在8到50字符之间")
    @Column(name = "password", nullable = false, length = 50)
    private String password;

    //角色；非空，长度1-50字符
    @ApiModelProperty(value = "角色", required = true, example = "admin")
    @NotBlank(message = "角色不能为空")
    @Size(min = 1, max = 50, message = "角色长度必须在1到50字符之间")
    @Column(name = "role", nullable = false, length = 50)
    private String role;

    //创建时间
    @NotBlank(message = "创建时间不能为空")
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    //更新时间
    @NotBlank(message = "更新时间不能为空")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}