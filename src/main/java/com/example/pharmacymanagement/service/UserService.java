package com.example.pharmacymanagement.service;

import com.example.pharmacymanagement.model.User;
import com.example.pharmacymanagement.repository.UserRepository;
import com.example.pharmacymanagement.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public User registerUser(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new ValidationException("用户名已存在");
        }

        if (!isValidPassword(password)) {
            throw new ValidationException("密码必须包含至少一个字母和一个数字，长度在8-50字符之间");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        return userRepository.save(user);
    }

    public String loginUser(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ValidationException("用户名不存在"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ValidationException("密码不正确");
        }

        return jwtUtil.generateToken(username);
    }

    private boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,50}$");
    }
}