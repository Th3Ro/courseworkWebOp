package net.winnings.repository;

import net.winnings.model.UserLoginData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for interacting with the UserLoginData table of the database
 * @author Nosolenko
 * @version 1.0
 */
@Repository
public interface UserLoginDataRepository extends CrudRepository<UserLoginData, Long> {
}
