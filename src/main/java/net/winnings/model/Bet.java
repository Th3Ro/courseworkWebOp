package net.winnings.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Bet entity class from database
 * @author Nosolenko
 * @version 1.0
 */
@Entity
public class Bet {

    /** User id field, which is the primary key with automatic generation of values */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /** Match id field */
    private Long matchId;
    /** User id field */
    private Long userId;
    /** Command number field */
    private byte teamNumber;
    /** Bet amount field */
    private int money;
    /** Bet result field */
    private int winResult;

    /**
     * Constructor - creating a new user
     */
    public Bet() {
    }

    /**
     * Constructor - creating a new object with specific values
     * @param matchId - match id
     * @param userId - user id
     * @param teamNumber - team number
     * @param money - bet amount
     */
    public Bet(Long matchId, Long userId, byte teamNumber, int money) {
        this.matchId = matchId;
        this.userId = userId;
        this.teamNumber = teamNumber;
        this.money = money;
        this.winResult = 0;
    }

    /**
     * Field value retrieval function {@link Bet#id}
     * @return returns bet id
     */
    public Long getId() {
        return id;
    }

    /**
     * Procedure for determining the bet identifier {@link Bet#id}
     * @param id - bet identifier
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Field value retrieval function {@link Bet#matchId}
     * @return returns the match id
     */
    public Long getMatchId() {
        return matchId;
    }

    /**
     * Match id determination procedure {@link Bet#matchId}
     * @param matchId - match id
     */
    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    /**
     * Field value retrieval function {@link Bet#userId}
     * @return returns user id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * User ID determination procedure {@link Bet#userId}
     * @param userId - user id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Field value retrieval function {@link Bet#teamNumber}
     * @return returns the command number
     */
    public byte getTeamNumber() {
        return teamNumber;
    }

    /**
     * Procedure for determining the command number {@link Bet#teamNumber}
     * @param teamNumber - team number
     */
    public void setTeamNumber(byte teamNumber) {
        this.teamNumber = teamNumber;
    }

    /**
     * Field value retrieval function {@link Bet#winResult}
     * @return returns the bet result
     */
    public int getWinResult() {
        return winResult;
    }
    /**
     * The procedure for determining the result of a bet {@link Bet#winResult}
     * @param winResult - bet result
     */
    public void setWinResult(int winResult) {
        this.winResult = winResult;
    }

    /**
     * Field value retrieval function {@link Bet#money}
     * @return returns the bet amount
     */
    public int getMoney() {
        return money;
    }

    /**
     * The procedure for determining the amount of the bet {@link Bet#money}
     * @param money - bet amount
     */
    public void setMoney(int money) {
        this.money = money;
    }
}
