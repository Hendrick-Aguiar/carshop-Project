package com.hendrick.carshop.service;

import com.hendrick.carshop.dto.*;
import com.hendrick.carshop.enums.Role;
import com.hendrick.carshop.model.Client;
import com.hendrick.carshop.model.User;
import com.hendrick.carshop.repository.ClientRepository;
import com.hendrick.carshop.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encode;


    public AuthService(ClientRepository clientRepository, UserRepository userRepository, PasswordEncoder encode) {

        this.clientRepository = clientRepository;
        this.userRepository = userRepository;

        this.encode = encode;
    }

    //Create
    public ClientDTO register(ClientDTO dto) {

        //Login: User and password
        Optional<User> userSearch = userRepository.findByLogin(dto.getLogin());

        if (userSearch.isPresent()) {

            throw new ResponseStatusException(HttpStatus.CONFLICT, "The user is already registered.");

        }

        //Check if client is register
        Optional<Client> clientFinder = clientRepository.findClientByName(dto.getName());

        if (clientFinder.isPresent()) {

            throw new ResponseStatusException(HttpStatus.CONFLICT, "The client is already registered.");

        }

        //Creating the user(Login Credenctials)
        User user = new User();
        user.setLogin(dto.getLogin());
        user.setPasswordHash(encode.encode(dto.getPassword()));
        user.setRole(Role.USER);
        user.setActive(Boolean.TRUE);
        user.setCreatedAt(LocalDateTime.now());
        user = userRepository.save(user);//saving in the database

        //Creating the client(personal data)
        Client client = new Client();
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setPhone(dto.getPhone());
        client.setEmail(dto.getEmail());
        client.setUser(user);
        client.setCreatedAt(LocalDateTime.now());
        client = clientRepository.save(client);//save client in the database

        //LoginResponseDTO fill dto with persisted data to return
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setEmail(client.getEmail());
        dto.setCpf(client.getCpf());
        dto.setPhone(client.getPhone());
        dto.setLogin(user.getLogin());


        return dto;

    }

    //Read
    public UpdateResponseDTO login(AuthDTO dto) {

        //finding user by login

        //Optional<Color> colorOptional = colorRepository.findById(id); Optional not return;
        //best practice using this Optional(orElseThrow) and not using Optional and if(!userOptional.IsPresent)
        //Fetching the entities from database
        User user = userRepository.findByLogin(dto.getLogin()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));
        //building the responsedto
        UpdateResponseDTO logResDTO = new UpdateResponseDTO();
        logResDTO.setUserId(user.getId());
        logResDTO.setLogin(user.getLogin());
        logResDTO.setRole(user.getRole());
        logResDTO.setActive(user.getActive());

        //finding the client associated with the user
        Optional<Client> client = clientRepository.findByUserId(logResDTO.getUserId());

        //if client exists, add cliente data to the dto
        if (client.isPresent()) {
            logResDTO.setClientId(client.get().getId());
            logResDTO.setName(client.get().getName());
            logResDTO.setEmail(client.get().getEmail());
            logResDTO.setCpf(client.get().getCpf());
            logResDTO.setPhone(client.get().getPhone());


        }
        return logResDTO;
    }

    //Update
    public UpdateResponseDTO update(Long userId, UpdateClientDTO requestDTO) {

        //finding cliente with cpf

        Client client = clientRepository.findByUserId(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found."));
        //dto data


        //update dto data to client

        client.setName(requestDTO.getName());
        client.setPhone(requestDTO.getPhone());
        client.setEmail(requestDTO.getEmail());

        //save
        client = clientRepository.save(client);


        //update to dto return
        UpdateResponseDTO dto = new UpdateResponseDTO();
        dto.setUserId(client.getUser().getId());
        dto.setLogin(client.getUser().getLogin());
        dto.setRole(client.getUser().getRole());
        dto.setActive(client.getUser().getActive());
        dto.setClientId(client.getId());
        dto.setCpf(client.getCpf());
        dto.setName(client.getName());
        dto.setPhone(client.getPhone());
        dto.setEmail(client.getEmail());


        return dto;


    }


}
