package com.hendrick.carshop.service;

import com.hendrick.carshop.dto.ClientDTO;
import com.hendrick.carshop.dto.LoginDTO;
import com.hendrick.carshop.dto.LoginResponseDTO;
import com.hendrick.carshop.enums.Role;
import com.hendrick.carshop.model.Client;
import com.hendrick.carshop.model.User;
import com.hendrick.carshop.repository.ClientRepository;
import com.hendrick.carshop.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    public AuthService(ClientRepository clientRepository, UserRepository userRepository) {

        this.clientRepository = clientRepository;
        this.userRepository = userRepository;

    }

    //Create
    public ClientDTO register(ClientDTO dto) {

        Optional<Client> clientOptional = clientRepository.findByCpf(dto.getCpf());
        if (clientOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Client already registered with this CPF.");
        }

        Optional<User> userOptional = userRepository.findByLogin(dto.getLogin());
        if (userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Login already registered.");
        }

        User user = new User();
        user.setLogin(dto.getLogin());
        user.setPasswordHash(dto.getPassword());
        user.setRole(Role.USER);
        user.setActive(Boolean.TRUE);
        user.setCreatedAt(LocalDateTime.now());
        user = userRepository.save(user);

        Client client = new Client();
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setPhone(dto.getPhone());
        client.setEmail(dto.getEmail());
        client.setUser(user);
        client.setCreatedAt(LocalDateTime.now());
        client = clientRepository.save(client);

        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setEmail(client.getEmail());
        dto.setCpf(client.getCpf());
        dto.setPhone(client.getPhone());
        dto.setLogin(user.getLogin());
        dto.setPassword(user.getPasswordHash());

        return dto;

    }

    //Read
    public LoginResponseDTO login(LoginDTO dto) {


        Optional<User> userOptional = userRepository.findByLogin(dto.getLogin());

        if (!userOptional.isPresent()) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");

        }

        if (!userOptional.get().getPasswordHash().equals(dto.getPassword())) {

            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Login or Password.");

        }


        LoginResponseDTO logResDTO = new LoginResponseDTO();
        logResDTO.setId(userOptional.get().getId());
        logResDTO.setLogin(userOptional.get().getLogin());
        logResDTO.setRole(userOptional.get().getRole());
        logResDTO.setActive(userOptional.get().getActive());
        //find the user by the id and POST THE CLIENT
        Optional<Client> client = clientRepository.findByUserId(logResDTO.getId());

        logResDTO.setName(client.get().getName());
        logResDTO.setEmail(client.get().getEmail());
        logResDTO.setCpf(client.get().getCpf());
        logResDTO.setPhone(client.get().getPhone());
        logResDTO.setId(client.get().getId());


        return logResDTO;


    }

    //Update
    public ClientDTO update(String cpf, ClientDTO dto) {

        Optional<Client> clientOptional = clientRepository.findByCpf(cpf);
        if (clientOptional.isEmpty()) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");

        }


        Client client = clientOptional.get();
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setPhone(dto.getPhone());
        client.setEmail(dto.getEmail());
        client = clientRepository.save(client);

        User user = new User();
        user.setId(dto.getId());
        user.setLogin(dto.getLogin());
        user.setPasswordHash(dto.getPassword());
        //user receve client
        user = client.getUser();
        user = userRepository.save(user);

        dto.setId(user.getId());
        dto.setName(client.getName());
        dto.setPhone(client.getPhone());
        dto.setEmail(client.getEmail());
        dto.setLogin(user.getLogin());
        dto.setPassword(user.getPasswordHash());


        return dto;


    }




}
