package com.hendrick.carshop.repository;

import com.hendrick.carshop.dto.CartDTO;
import com.hendrick.carshop.model.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

    Optional<Client> findByCpf(String cpf);

    Optional<Client> findByUserId(Long userId);





}
