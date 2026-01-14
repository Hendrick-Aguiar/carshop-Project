package com.hendrick.carshop.repository;

import com.hendrick.carshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(User userId);



    Optional<User> findByLogin(String login);
}
