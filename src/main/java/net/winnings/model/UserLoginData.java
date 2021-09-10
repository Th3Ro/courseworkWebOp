package net.winnings.model;

import javax.persistence.*;

/**
 * UserLoginData entity class from database
 * @author Nosolenko
 * @version 1.0
 */
@Entity
public class UserLoginData {

    /** User login data id field,
     * which is the primary key with automatic generation of values */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /** User login field */
    @Column(nullable = false, unique = true)
    private String userLogin;
    /** User password field */
    @Column(nullable = false)
    private String userPassword;
    /** The ID field of the user who owns this data */
    @Column(nullable = false)
    private Long userId;

    /**
     * Constructor - creating a new user
     */
    public UserLoginData() {
    }

    /**
     * Constructor - creating a new object with specific values
     * @param userLogin - login
     * @param userPassword - password
     * @param userId - the identifier of the user who owns this data
     */
    public UserLoginData(String userLogin, String userPassword, Long userId) {
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.userId = userId;
    }

    /**
     * Field value retrieval function {@link UserLoginData#id}
     * @return returns the login data id
     */
    public Long getId() {
        return id;
    }

    /**
     * Procedure for determining the login credentials {@link UserLoginData#id}
     * @param id - login data id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Field value retrieval function {@link UserLoginData#userLogin}
     * @return returns login
     */
    public String getUserLogin() {
        return userLogin;
    }

    /**
     * Login determination procedure {@link UserLoginData#userLogin}
     * @param userLogin - login
     */
    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    /**
     * Field value retrieval function {@link UserLoginData#userPassword}
     * @return returns password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Password determination procedure {@link UserLoginData#userLogin}
     * @param userPassword - password
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Field value retrieval function {@link UserLoginData#userId}
     * @return returns the id of the user who owns this data
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * The procedure for determining the identifier of the user who owns this data {@link UserLoginData#userId}
     * @param userId - the identifier of the user who owns this data
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
