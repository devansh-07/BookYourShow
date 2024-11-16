package com.BookYourShow.UserManagement.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.BookYourShow.UserManagement.Models.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Long>{
    Optional<Users> findUserByEmail(String email);
    void deleteByEmail(String email);
}
