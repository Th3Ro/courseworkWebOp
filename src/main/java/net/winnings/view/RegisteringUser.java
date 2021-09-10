package net.winnings.view;

import net.winnings.model.User;
import net.winnings.model.UserLoginData;

import java.util.Date;

/**
 * A class that summarizes user data {@link User} and
 * its authorization data {@link UserLoginData}
 * @author Nosolenko
 * @version 1.0
 */
public class RegisteringUser {

    /** User surname field */
    private String surname;
    /** User name field */
    private String name;
    /** User's date of birth field */
    private Date dateOfBirth;
    /** User cash account field */
    private double moneyScore;

    /** User login field */
    private String userLogin;
    /** User password field */
    private String userPassword;

    /**
     * Constructor - creating a new object with specific values
     * @param surname - surname
     * @param name - name
     * @param dateOfBirth - date of birth
     * @param moneyScore - cash account
     * @param userLogin - login
     * @param userPassword - password
     */
    public RegisteringUser(String surname, String name, Date dateOfBirth, double moneyScore, String userLogin, String userPassword) {
        this.surname = surname;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.moneyScore = moneyScore;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
    }

    /**
     * Field value retrieval function {@link RegisteringUser#surname}
     * @return returns the user's surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Match ID determination procedure {@link RegisteringUser#surname}
     * @param surname - user surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Field value retrieval function {@link RegisteringUser#name}
     * @return returns user name
     */
    public String getName() {
        return name;
    }

    /**
     * Match ID determination procedure {@link RegisteringUser#name}
     * @param name - user name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Field value retrieval function {@link RegisteringUser#dateOfBirth}
     * @return returns the user's date of birth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Match id determination procedure {@link RegisteringUser#dateOfBirth}
     * @param dateOfBirth - user's date of birth
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Field value retrieval function {@link RegisteringUser#moneyScore}
     * @return returns the user's money account
     */
    public double getMoneyScore() {
        return moneyScore;
    }

    /**
     * The procedure for determining the user's cash account {@link RegisteringUser#moneyScore}
     * @param moneyScore - user money account
     */
    public void setMoneyScore(double moneyScore) {
        this.moneyScore = moneyScore;
    }

    /**
     * Field value retrieval function {@link RegisteringUser#userLogin}
     * @return returns username
     */
    public String getUserLogin() {
        return userLogin;
    }

    /**
     * User login determination procedure {@link RegisteringUser#userLogin}
     * @param userLogin - user login
     */
    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    /**
     * Field value retrieval function{@link RegisteringUser#userPassword}
     * @return returns the user's password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * User password determination procedure {@link RegisteringUser#userPassword}
     * @param userPassword - user password
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
