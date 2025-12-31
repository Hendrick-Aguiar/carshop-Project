package com.hendrick.carshop.controller;

import com.hendrick.carshop.dto.ClientDTO;
import com.hendrick.carshop.dto.LoginDTO;
import com.hendrick.carshop.dto.LoginResponseDTO;
import com.hendrick.carshop.dto.UserDTO;
import com.hendrick.carshop.model.User;
import com.hendrick.carshop.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/clients")
@CrossOrigin("*")
public class ClientController {

    private final AuthService authService;

    public ClientController(AuthService clientService) {

        this.authService = clientService;

    }

    @PostMapping
    public ResponseEntity<ClientDTO> register(@RequestBody ClientDTO dto) throws URISyntaxException {

        return ResponseEntity.created(new URI("Client")).body(authService.register(dto));


    }

    @GetMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO dto) {

        return ResponseEntity.ok((authService.login(dto)));

    }

    @PutMapping("/{cpf}")
    public ResponseEntity<ClientDTO> update(@PathVariable String cpf, @RequestBody ClientDTO dto) {

        dto = authService.update(cpf, dto);
        return ResponseEntity.ok(dto);
    }

}
