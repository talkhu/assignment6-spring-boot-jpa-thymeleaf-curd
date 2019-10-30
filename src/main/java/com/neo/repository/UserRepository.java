package com.neo.repository;

import com.neo.model.CustomUser;
import com.neo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);
    User findByUserName(String userName);
}