package com.radixlogos.hospitalapi.controllers;

import com.radixlogos.hospitalapi.dtos.login.LoginRequestDTO;
import com.radixlogos.hospitalapi.dtos.responses.JwtResponseDTO;
import com.radixlogos.hospitalapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody LoginRequestDTO login){
        var response = userService.loginByEmail(login);
        return ResponseEntity.ok().body(response);
    }
}
