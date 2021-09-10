package net.winnings.view;

import net.winnings.model.Bet;
import net.winnings.model.SportMatch;

import java.sql.Date;

/**
 * A class that summarizes the data of a bet of the {@link Bet} type and a match of the {@link SportMatch} type
 * @author Nosolenko
 * @version 1.0
 */
public class BetForUser {

    /** Match id */
    private Long matchId;
    /** Bet id */
    private Long betId;
    /** Match name */
    private String matchName;
    /** Team number */
    private byte team;
    /** Match date */
    private Date date;
    /** Team coefficient */
    private float coef;
    /** Bet amount */
    private int money;
    /** Bet result */
    private int result;

    /**
     * Constructor - creating a new object with specific values
     * @param matchId - match id
     * @param betId - bet identifier
     * @param matchName - match name
     * @param team - team number
     * @param date - match date
     * @param coef - team coefficient
     * @param money - bet amount
     * @param result - bet result
     */
    public BetForUser(Long matchId, Long betId, String matchName, byte team, Date date, float coef, int money, int result) {
        this.matchId = matchId;
        this.betId = betId;
        this.matchName = matchName;
        this.team = team;
        this.date = date;
        this.coef = coef;
        this.money = money;
        this.result = result;
    }

    /**
     * Field value retrieval function {@link BetForUser#matchId}
     * @return returns the match id
     */
    public Long getMatchId() {
        return matchId;
    }

    /**
     * Match id determination procedure {@link BetForUser#matchId}
     * @param matchId - match id
     */
    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    /**
     * Field value retrieval function {@link BetForUser#betId}
     * @return returns bet id
     */
    public Long getBetId() {
        return betId;
    }

    /**
     * Procedure for determining the bet identifier {@link BetForUser#betId}
     * @param betId - bet identifier
     */
    public void setBetId(Long betId) {
        this.betId = betId;
    }

    /**
     * Field value retrieval function {@link BetForUser#matchName}
     * @return returns the name of the match
     */
    public String getMatchName() {
        return matchName;
    }

    /**
     * Match Name Determination Procedure {@link BetForUser#matchName}
     * @param matchName - match name
     */
    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    /**
     * Field value retrieval function {@link BetForUser#team}
     * @return returns the command number
     */
    public byte getTeam() {
        return team;
    }

    /**
     * Procedure for determining the command number {@link BetForUser#team}
     * @param team - team number
     */
    public void setTeam(byte team) {
        this.team = team;
    }

    /**
     * Field value retrieval function {@link BetForUser#date}
     * @return returns the date of the match
     */
    public Date getDate() {
        return date;
    }

    /**
     * Match date determination procedure {@link BetForUser#date}
     * @param date - match date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Field value retrieval function {@link BetForUser#coef}
     * @return returns the team factor
     */
    public float getCoef() {
        return coef;
    }

    /**
     * Team coefficient determination procedure {@link BetForUser#coef}
     * @param coef - team coefficient
     */
    public void setCoef(float coef) {
        this.coef = coef;
    }

    /**
     * Field value retrieval function {@link BetForUser#money}
     * @return return of the bet amount
     */
    public int getMoney() {
        return money;
    }

    /**
     * The procedure for determining the amount of the bet {@link BetForUser#money}
     * @param money - bet amount
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Field value retrieval function {@link BetForUser#result}
     * @return returns the bet result
     */
    public int getResult() {
        return result;
    }

    /**
     * The procedure for determining the result of a bet {@link BetForUser#result}
     * @param result - bet result
     */
    public void setResult(int result) {
        this.result = result;
    }
}
