/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.rmi;

import game.bean.Challenge;
import game.bean.Invitation;
import game.bean.Message;
import game.bean.Notification;
import game.bean.Player;
import game.bean.Question;
import game.bean.Team;
import game.controller.PlayerController;
import game.controller.TeamController;
import game.interfaces.PlayerInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Lucas
 */
public class PlayerRMI extends UnicastRemoteObject implements PlayerInterface {

    private PlayerController controller;
    private TeamController teamController;
    
    public PlayerRMI()throws RemoteException{
        
    }

    public PlayerRMI(PlayerController pi,TeamController tc) throws RemoteException {
        this.controller = pi;
        this.teamController = tc;
    }

    @Override
    public void addMessageAll(Message msg) throws RemoteException {
        System.out.println(this.getClass() + ">>MSG-ALL:" + msg);
        controller.addMessageAll(msg.getDescription());
    }

    @Override
    public void addMessageTeam(Message msg) throws RemoteException {
        System.out.println(this.getClass() + ">>MSG-TEAM:" + msg);
        controller.addMessageTeam(msg.getDescription());
    }

    @Override
    public void addInvitationTeam(Invitation inv) throws RemoteException {
         System.out.println(this.getClass() + ">>ADD-INVITATION-TEAM:" + inv);
        teamController.inviteJoinTeam(inv);
    }

    @Override
    public void addInternalInvitation(Invitation inv) throws RemoteException {
        System.out.println(this.getClass() + ">>INT-INV:" + inv);
        teamController.addInvitation(inv);
    }

    @Override
    public void addExternalInvitation(Invitation inv) throws RemoteException {
        System.out.println(this.getClass() + ">>EXT-INV:" + inv);
        teamController.addInvitation(inv);
    }

    @Override
    public void addNotification(Notification ntf) throws RemoteException {
        controller.addNotification(ntf);
    }

    @Override
    public void startChallenge(Challenge cha) throws RemoteException {
        teamController.startChallenge(cha);
    }

    @Override
    public void selectAnswer(Question qt, Player p) throws RemoteException {
        teamController.selectAnswer(qt, p);
    }

    @Override
    public void selectQuestion(Player p) throws RemoteException {
        teamController.selectQuestion(p);
    }

    @Override
    public void waitQuestion() throws RemoteException {
        teamController.waitQuestion();
    }

    @Override
    public void waitAnswer() throws RemoteException {
        teamController.waitAnswer();
    }

    @Override
    public void finish(Team t) throws RemoteException {
        teamController.finish(t);
    }

    
    @Override
    public void joinTeam(Team t) throws RemoteException {
        teamController.joinTeam(t);
    }
    
    
    
}
