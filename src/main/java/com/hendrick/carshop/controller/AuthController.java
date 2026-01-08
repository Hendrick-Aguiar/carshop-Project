package com.hendrick.carshop.controller;

import com.hendrick.carshop.dto.ClientDTO;
import com.hendrick.carshop.dto.AuthDTO;
import com.hendrick.carshop.dto.LoginResponseDTO;
import com.hendrick.carshop.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {

        this.authService = authService;

    }

    //Create: user and client
    @PostMapping
    public ResponseEntity<ClientDTO> register(@RequestBody ClientDTO dto) throws URISyntaxException {

        return ResponseEntity.ok(authService.register(dto));


    }

    //Read: @GetMapping changed to @Post, login has logs
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthDTO dto, HttpSession session) {

        //return ResponseEntity.ok((authService.login(dto)));
        LoginResponseDTO responseDTO = authService.login(dto);

            session.setAttribute("loggedUserId", responseDTO.getUserId());
            session.setAttribute("loggedUserLogin", responseDTO.getLogin());
            return ResponseEntity.ok(responseDTO);


    }

    //Update: update User and Client
    @PutMapping("/{cpf}")
    public ResponseEntity<ClientDTO> update(@PathVariable String cpf, @RequestBody ClientDTO dto) {

        dto = authService.update(cpf, dto);
        return ResponseEntity.ok(dto);
    }

}
