package com.hendrick.carshop.repository;

import com.hendrick.carshop.model.Client;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, String> {

    public Optional<Client> findByCpf(String cpf);


    Optional<Client> findByUserId(Long userId);
}
