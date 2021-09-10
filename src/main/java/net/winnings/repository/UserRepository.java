package net.winnings.repository;

import net.winnings.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface for interacting with the User table of the database
 * @author Nosolenko
 * @version 1.0
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    /**
     * Function for getting a list of users of the type {@link User}
     * @return returns a list of users
     */
    List<User> findAll();
}
