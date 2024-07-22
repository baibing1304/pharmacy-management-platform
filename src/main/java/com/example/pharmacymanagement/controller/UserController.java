package com.example.pharmacymanagement.controller;

import com.example.pharmacymanagement.model.User;
import com.example.pharmacymanagement.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/api/users")
@Validated
@Tag(name = "User", description = "User management APIs")
public class UserController {

    @GetMapping("/{id}")
    @Operation(
            summary = "Get a user by ID",
            description = "Returns a user based on the ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = User.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "User not found")
            }
    )
    public User getUserById(
            @Parameter(description = "ID of the user to be obtained") @PathVariable Long id
    ) {
        return null;
        // Your implementation here
    }

    @PostMapping
    @Operation(
            summary = "Create a new user",
            description = "Creates a new user and returns the created user"
    )
    public User createUser(
            @Parameter(description = "User to be created") @RequestBody User user
    ){
        return null;
    }
}