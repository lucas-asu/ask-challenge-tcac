/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.interfaces;

import game.bean.Challenge;
import game.bean.Invitation;
import game.bean.Message;
import game.bean.Notification;
import game.bean.Player;
import game.bean.Question;
import game.bean.Team;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Lucas
 */
public interface PlayerInterface extends Remote {

    void addMessageAll(Message msg) throws RemoteException;
    void addMessageTeam(Message msg) throws RemoteException;    
    void addInvitationTeam(Invitation inv) throws RemoteException;    
    void addInternalInvitation(Invitation inv) throws RemoteException;    
    void addExternalInvitation(Invitation inv) throws RemoteException;  
    void addNotification(Notification ntf) throws RemoteException;   
    void startChallenge(Challenge cha) throws RemoteException; 
    void selectQuestion(Player p) throws RemoteException; 
    void selectAnswer(Question qt, Player p) throws RemoteException; 
    void waitQuestion() throws RemoteException; 
    void waitAnswer() throws RemoteException; 
    void finish(Team t) throws RemoteException; 
    void joinTeam(Team t) throws RemoteException; 
    
}
