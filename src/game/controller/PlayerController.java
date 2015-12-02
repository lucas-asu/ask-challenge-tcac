/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.controller;

import game.bean.Invitation;
import game.bean.Message;
import game.bean.Notification;
import game.bean.Player;
import game.bean.Team;
import game.interfaces.PlayerInterface;
import game.rmi.PlayerRMI;
import game.util.Util;
import game.view.MainWindow;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas
 */
public class PlayerController extends Thread {

    private MainWindow window;
    
    public InterfaceFactory factory;
    private PlayerInterface playerInterface;
    public Player player;
        
    public Map<String, Invitation> invitations;
    

    public PlayerController(String IP, MainWindow mw) {
        
        this.invitations = new HashMap<>();
        this.window = mw;
        factory = new InterfaceFactory(IP);  
        
        
       
    }
    
    public void initPlayerInterface(TeamController tc){
        try {
                      
            playerInterface = new PlayerRMI(this,tc);            
        } catch (RemoteException ex) {
            Logger.getLogger(PlayerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        while(true){
            
        }
    }

    public boolean initPlayer(String nick) {
        if (factory.getAuthenticationInterface() != null) {
            try {
                player = new Player(nick);
                if (!factory.getAuthenticationInterface().existPlayer(nick)) {
                    factory.getAuthenticationInterface().newPlayer(player, playerInterface);
                    return true;
                }
            } catch (RemoteException ex) {
                Logger.getLogger(PlayerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public void sendMessageGlobal(String msg){
        Message m = new Message(player.getID(), msg, Util.CHAT_ALL);
        try {
            factory.getChatInterface().newMessageToAll(m);
        } catch (RemoteException ex) {
            Logger.getLogger(PlayerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendMessageTeam(String msg, Team team){
        try {
            Message m = new Message(player.getID(), msg, Util.CHAT_ALL); 
            factory.getChatInterface().newMessageToTeam(team.getID(), m);
        } catch (RemoteException ex) {
            Logger.getLogger(PlayerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addMessageAll(String msg) {
        window.setMessageAll(msg);
        
    }
    
    public void addMessageTeam(String msg) {
        window.setMessageTeam(msg);       
    }
 
    
    public void closing(Team team){
        try {
            if(team != null){
                factory.getAuthenticationInterface().removeMember(team.getID(), player);
            }
            factory.getAuthenticationInterface().removePlayer(player.getID());            
        } catch (RemoteException ex) {
            Logger.getLogger(PlayerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public void addNotification(Notification ntf){
        window.showNotification(ntf.getMessage());
    }

   
}
