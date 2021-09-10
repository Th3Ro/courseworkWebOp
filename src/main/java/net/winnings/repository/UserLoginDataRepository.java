package net.winnings.repository;

import net.winnings.model.UserLoginData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface for interacting with the UserLoginData table of the database
 * @author Nosolenko
 * @version 1.0
 */
@Repository
public interface UserLoginDataRepository extends CrudRepository<UserLoginData, Long> {
    /**
     * The function of obtaining all authorization data of users of the type {@link UserLoginData}
     * @return returns credentials of all users
     */
    List<UserLoginData> findAll();

    /**
     * Function for obtaining authorization data of a user type {@link UserLoginData}
     * @param userLogin - user name
     * @return returns user credentials
     */
    UserLoginData findByUserLogin(String userLogin);

    /**
     * Function for obtaining authorization data of a user type {@link UserLoginData}
     * @param userId - user id
     * @return returns user credentials
     */
    UserLoginData findByUserId(Long userId);
}
