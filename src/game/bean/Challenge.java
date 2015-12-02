/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class Challenge implements Serializable {
    
    private final String ID;
    private final Team teamA; 
    private final Team teamB;
    private ArrayList<Round> rounds;
    private final int limitOfRounds;
    private final int points;
    private boolean started;

    public Challenge(Team teamA, Team teamB, int limitOfRounds, int points) {
        this.ID = "CHL:"+(teamA.toString()+teamB.toString()+System.currentTimeMillis()).hashCode();
        this.teamA = teamA;
        this.teamB = teamB;
        this.limitOfRounds = limitOfRounds;
        this.points = points;
    }

    public String getID() {
        return ID;
    }

    public Team getTeamA() {
        return teamA;
    }

    public Team getTeamB() {
        return teamB;
    }

    public ArrayList<Round> getRounds() {
        return rounds;
    }

    public int getLimitOfRounds() {
        return limitOfRounds;
    }

    public int getPoints() {
        return points;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }
    
    
}
