package net.winnings.repository;

import net.winnings.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for interacting with the User table of the database
 * @author Nosolenko
 * @version 1.0
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
