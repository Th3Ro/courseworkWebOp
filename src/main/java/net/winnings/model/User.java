package net.winnings.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.util.Date;


/**
 * User entity class from database
 * @author Nosolenko
 * @version 1.0
 */
@Entity
public class User {

    /** User id field, which is the primary key with automatic generation of values */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /** User surname field */
    @Column(nullable = false)
    private String surname;
    /** User name field */
    @Column(nullable = false)
    private String name;
    /** User's date of birth field */
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    /** User cash account field */
    @Column(nullable = false)
    @Digits(integer = 10, fraction = 2)
    private double moneyScore;
    /** User image path field */
    private String image;

    /**
     * Constructor - creating a new user
     */
    public User() {
    }

    /**
     * Constructor - creating a new object with specific values
     * @param surname - surname
     * @param name - name
     * @param dateOfBirth - date of birth
     */
    public User(String surname, String name, Date dateOfBirth) {
        this.surname = surname;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.moneyScore = 0;
        this.image = "adv.jpg";
    }

    /**
     * Field value retrieval function {@link User#id}
     * @return returns user id
     */
    public Long getId() {
        return id;
    }

    /**
     * User ID determination procedure {@link User#id}
     * @param id - user id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Field value retrieval function {@link User#surname}
     * @return returns the surname of the user
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Procedure for determining the user's surname {@link User#surname}
     * @param surname - user surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Field value retrieval function {@link User#name}
     * @return returns user name
     */
    public String getName() {
        return name;
    }

    /**
     * Username determination procedure {@link User#name}
     * @param name - user name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Field value retrieval function {@link User#dateOfBirth}
     * @return returns the user's date of birth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Procedure for determining the user's date of birth {@link User#dateOfBirth}
     * @param dateOfBirth - user's date of birth
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Field value retrieval function {@link User#moneyScore}
     * @return returns the user's money account
     */
    public double getMoneyScore() {
        return moneyScore;
    }

    /**
     * The procedure for determining the user's cash account {@link User#moneyScore}
     * @param moneyScore - user money account
     */
    public void setMoneyScore(double moneyScore) {
        this.moneyScore = moneyScore;
    }

    /**
     * Field value retrieval function {@link User#image}
     * @return returns the user's photo
     */
    public String getImage() {
        return image;
    }

    /**
     * The procedure for determining the user's cash account {@link User#image}
     * @param image - user photo
     */
    public void setImage(String image) {
        this.image = image;
    }
}
