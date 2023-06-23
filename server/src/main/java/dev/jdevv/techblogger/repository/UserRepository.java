package dev.jdevv.techblogger.repository;

import dev.jdevv.techblogger.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /*
        * Responsible for communicating with User table in Database
     */

    Optional<User> findByUserName(String userName);
}
