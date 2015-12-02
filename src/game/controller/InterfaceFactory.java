/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.controller;

import game.interfaces.AuthenticationInterface;
import game.interfaces.ChallengeInterface;
import game.interfaces.ChatInterface;
import game.interfaces.InvitationChallengeInterface;
import game.interfaces.InvitationTeamInterface;
import game.interfaces.QuestionInterface;
import game.util.Util;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas
 */
public class InterfaceFactory {
    
    private AuthenticationInterface authenticationInterface;
    private ChallengeInterface challengeInterface;
    private ChatInterface chatInterface;
    private InvitationChallengeInterface invitationInterface;
    private QuestionInterface questionInterface;
    private Registry registry;
    
    public InterfaceFactory(String ip){
        try {
            registry = LocateRegistry.getRegistry(ip, 1090);
        } catch (RemoteException ex) {
            Logger.getLogger(InterfaceFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public AuthenticationInterface getAuthenticationInterface() {
        try {
            return (AuthenticationInterface) registry.lookup(Util.INTERFACE_AUTHENTICATION);
        } catch (RemoteException ex) {
            Logger.getLogger(InterfaceFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(InterfaceFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ChallengeInterface getChallengeInterface() {
        try {
            return (ChallengeInterface) registry.lookup(Util.INTERFACE_CHALLENGE);
        } catch (RemoteException ex) {
            Logger.getLogger(InterfaceFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(InterfaceFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ChatInterface getChatInterface() {
        try {
            return (ChatInterface) registry.lookup(Util.INTERFACE_CHAT);
        } catch (RemoteException ex) {
            Logger.getLogger(InterfaceFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(InterfaceFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public InvitationChallengeInterface getInvitationChallengeInterface() {
        try {
            return (InvitationChallengeInterface) registry.lookup(Util.INTERFACE_INVITATION_CHALLENGE);
        } catch (RemoteException ex) {
            Logger.getLogger(InterfaceFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(InterfaceFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public InvitationTeamInterface getInvitationTeamInterface() {
        try {
            return (InvitationTeamInterface) registry.lookup(Util.INTERFACE_INVITATION_TEAM);
        } catch (RemoteException ex) {
            Logger.getLogger(InterfaceFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(InterfaceFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    public QuestionInterface getQuestionInterface() {
        try {
            return (QuestionInterface) registry.lookup(Util.INTERFACE_QUESTION);
        } catch (RemoteException ex) {
            Logger.getLogger(InterfaceFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(InterfaceFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
    
    
}
