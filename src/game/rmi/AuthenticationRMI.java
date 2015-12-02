/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.rmi;

import game.bean.Player;
import game.bean.Team;
import game.interfaces.AuthenticationInterface;
import game.interfaces.PlayerInterface;
import game.controller.AuthenticationController;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class AuthenticationRMI extends UnicastRemoteObject implements AuthenticationInterface {

    private AuthenticationController controller;

    public AuthenticationRMI(AuthenticationController controller) throws RemoteException {
        this.controller = controller;
    }

    @Override
    public void newPlayer(Player p, PlayerInterface pi) throws RemoteException {
        System.out.println(this.getClass()+" - new player... "+p);
        controller.addPlayer(p,pi);
    }

    @Override
    public boolean existPlayer(String name) throws RemoteException {
        System.out.println(this.getClass()+" - exist player... "+name);
        return controller.existPlayer(name);
    }

    @Override
    public void newTeam(Team t) throws RemoteException {
        System.out.println(this.getClass()+" - new team... "+t);
        controller.addTeam(t);
    }
    
    @Override
    public void newMember(String teamID, Player p) throws RemoteException {
        System.out.println(this.getClass()+" - new member... "+teamID+" - "+p);
        controller.addMember(teamID, p);
    }

    @Override
    public void changeLeader(String teamID, Player p) throws RemoteException {
         System.out.println(this.getClass()+" - change leader... "+teamID+" - "+p);
         controller.changeLeader(teamID, p);
    }
    
    
    @Override
    public void removeMember(String teamID, Player p) throws RemoteException {
        System.out.println(this.getClass()+" - remove member... "+teamID+" - "+p);
        controller.removeMember(teamID, p);
    }

    @Override
    public void removePlayer(String playerID) throws RemoteException {
        System.out.println(this.getClass()+" - remove player... "+playerID);
        controller.removePlayer(playerID);
    }   

    @Override
    public boolean existTeam(String name) throws RemoteException {
        System.out.println(this.getClass()+" - exist team... "+name);
        return controller.existTeam(name);
    }

    @Override
    public Player getPlayer(String id) throws RemoteException {
        System.out.println(this.getClass()+" - get player... "+id);
        return controller.getPlayer(id);
    }
    
    @Override
    public PlayerInterface getPlayerInterface(String id) throws RemoteException {
        System.out.println(this.getClass()+" - get player interface... "+id);
        return controller.getPlayerInterface(id);
    }

    @Override
    public Team getTeam(String id) throws RemoteException {
        System.out.println(this.getClass()+" - get team... "+id);
        return controller.getTeam(id);
    }

    @Override
    public ArrayList<Player> getAllPlayers() throws RemoteException {
        System.out.println(this.getClass()+" - get all players... ");
        return controller.getAllPlayers();
    }

    @Override
    public ArrayList<Team> getAllTeams() throws RemoteException {
        System.out.println(this.getClass()+" - get all teams... ");
        return controller.getAllTeam();
    }

    @Override
    public void addPoint(String teamID, int points) throws RemoteException {
        controller.addPoint(teamID, points);
    }

    @Override
    public void removePoint(String teamID, int points) throws RemoteException {
       controller.removePoint(teamID, points);
    }

    
}
