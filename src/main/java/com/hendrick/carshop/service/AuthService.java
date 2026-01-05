package com.hendrick.carshop.service;

import com.hendrick.carshop.dto.ClientDTO;
import com.hendrick.carshop.dto.AuthDTO;
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

        //Login: User and password
        Optional<User> userOptional = userRepository.findByLogin(dto.getLogin());
        if (userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Login already registered.");
        }

        //After login: Check clientes with the same cpf
        Optional<Client> clientOptional = clientRepository.findByCpf(dto.getCpf());
        if (clientOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Client already registered with this CPF.");
        }


        //Creating the user(Login Credenctials)
        User user = new User();
        user.setLogin(dto.getLogin());
        user.setPasswordHash(dto.getPassword());
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
        dto.setPassword(user.getPasswordHash());

        return dto;

    }

    //Read
    public LoginResponseDTO login(AuthDTO dto) {

        //finding user by login
        Optional<User> userOptional = userRepository.findByLogin(dto.getLogin());

        if (!userOptional.isPresent()) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");

        }

        //Optional<Color> colorOptional = colorRepository.findById(id); Optional not return;
        //best practice using this Optional(orElseThrow) and not using Optional and if(!userOptional.IsPresent)
        //Fetching the entities from database
        User user = userRepository.findByLogin(dto.getLogin()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));
        //building the responsedto
        LoginResponseDTO logResDTO = new LoginResponseDTO();
        logResDTO.setUserId(user.getId());
        logResDTO.setLogin(user.getLogin());
        logResDTO.setRole(user.getRole());
        logResDTO.setActive(user.getActive());

        //finding the client associated with the user
        Optional<Client> client = clientRepository.findByUserId(logResDTO.getUserId());

        //if client exists, add cliente data to the dto
        if (client.isPresent()) {
            logResDTO.setId(client.get().getId());
            logResDTO.setName(client.get().getName());
            logResDTO.setEmail(client.get().getEmail());
            logResDTO.setCpf(client.get().getCpf());
            logResDTO.setPhone(client.get().getPhone());


        }
        return logResDTO;
    }

    //Update
    public ClientDTO update(String cpf, ClientDTO dto) {

        //finding cliente with cpf
        Optional<Client> clientOptional = clientRepository.findByCpf(cpf);
        if (clientOptional.isEmpty()) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");

        }


        //updating associated user data
        User user = new User();
        user.setId(dto.getId());
        user.setLogin(dto.getLogin());
        user.setPasswordHash(dto.getPassword());

        //updating client data
        Client client = clientOptional.get();
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setPhone(dto.getPhone());
        client.setEmail(dto.getEmail());
        client = clientRepository.save(client);//saving

        //user receve client
        user = client.getUser();
        user = userRepository.save(user);

        //update to dto return
        dto.setId(user.getId());
        dto.setName(client.getName());
        dto.setPhone(client.getPhone());
        dto.setEmail(client.getEmail());
        dto.setLogin(user.getLogin());
        dto.setPassword(user.getPasswordHash());


        return dto;


    }


}
