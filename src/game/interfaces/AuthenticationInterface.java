/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.interfaces;

import game.bean.Player;
import game.bean.Team;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public interface AuthenticationInterface  extends Remote{
    
    void newPlayer(Player p, PlayerInterface pi) throws RemoteException;
    boolean existPlayer(String name) throws RemoteException;
    void newTeam(Team t) throws RemoteException;
    void newMember(String teamID, Player p) throws RemoteException;
    void changeLeader(String teamID, Player p) throws RemoteException;
    void removeMember(String teamID, Player p) throws RemoteException;
    void removePlayer(String playerID) throws RemoteException;
    void addPoint(String teamID, int points) throws RemoteException;
    void removePoint(String teamID, int points) throws RemoteException;
    boolean existTeam(String name) throws RemoteException;
    Player getPlayer(String id) throws RemoteException;
    PlayerInterface getPlayerInterface(String id) throws RemoteException;
    Team getTeam(String id) throws RemoteException;
    ArrayList<Player> getAllPlayers() throws RemoteException;
    ArrayList<Team> getAllTeams() throws RemoteException;
    
    
    
}
