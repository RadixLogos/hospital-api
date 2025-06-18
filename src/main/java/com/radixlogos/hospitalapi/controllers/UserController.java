package com.radixlogos.hospitalapi.controllers;


import com.radixlogos.hospitalapi.dtos.insertions.UserInsertDTO;
import com.radixlogos.hospitalapi.dtos.responses.UserResponseDTO;
import com.radixlogos.hospitalapi.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> findAllUsers(Pageable pageable){
        var response = userService.findAllUsers(pageable);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findUser(@PathVariable Long id){
        var response = userService.findUserById(id);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> insertUser(@Valid @RequestBody UserInsertDTO insertDTO){
        var response = userService.insertUser(insertDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long id,
           @Valid @RequestBody UserInsertDTO insertDTO){
        var response = userService.updateUser(id,insertDTO);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
