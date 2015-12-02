/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.controller;

import game.bean.Player;
import game.bean.Team;
import game.interfaces.PlayerInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Lucas
 */
public class AuthenticationController extends Thread {

    private Map<String, PlayerInterface> playersInterface;
    private Map<String, Player> players;
    private Map<String, Team> teams;

    public AuthenticationController() {
        playersInterface = new HashMap<>();
        players = new HashMap<>();
        teams = new HashMap<>();
    }

    public void run() {

    }

    public synchronized void addPlayer(Player p, PlayerInterface pi) {
        players.put(p.getID(), p);
        playersInterface.put(p.getID(), pi);
    }

    public synchronized Player getPlayer(String id) {
        return players.get(id);
    }

    public synchronized PlayerInterface getPlayerInterface(String id) {
        return playersInterface.get(id);
    }

    public synchronized boolean existPlayer(String name) {
        for (Player p : players.values()) {
            if (p.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void addTeam(Team t) {
        teams.put(t.getID(), t);
    }

    public synchronized void addMember(String teamID, Player p) {
        Team t = teams.get(teamID);
        t.addMember(p);
        teams.put(t.getID(), t);
    }
    
    public synchronized void changeLeader(String teamID, Player p){
        Team t = teams.get(teamID);
        t.setLeader(p);
        teams.put(t.getID(), t);
    }

    public synchronized void removeMember(String teamID, Player p) {
        Team t = teams.get(teamID);
        t.removeMember(p);
        if (t.getMembers().size() > 0) {
            teams.put(t.getID(), t);
        } else {
            teams.remove(t.getID());
        }
    }

    public synchronized void removePlayer(String playerID) {
        players.remove(playerID);
        playersInterface.remove(playerID);
    }

    public synchronized Team getTeam(String id) {
        return teams.get(id);
    }

    public synchronized boolean existTeam(String name) {
        for (Team t : teams.values()) {
            if (t.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Player> getAllPlayers() {
        return new ArrayList(players.values());
    }

    public ArrayList<Team> getAllTeam() {
        return new ArrayList(teams.values());
    }
    
    public synchronized void addPoint(String teamID, int points){
        Team t = teams.get(teamID);
        t.addPoint(points);
        teams.put(teamID, t);
    }
    
    public synchronized void removePoint(String teamID, int points){
        Team t = teams.get(teamID);
        t.removePoint(points);
        teams.put(teamID, t);
    }

}
