/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.interfaces.AuthenticationInterface;
import game.interfaces.ChallengeInterface;
import game.interfaces.ChatInterface;
import game.interfaces.InvitationChallengeInterface;
import game.interfaces.QuestionInterface;
import game.rmi.AuthenticationRMI;
import game.rmi.ChallengeRMI;
import game.rmi.ChatRMI;
import game.rmi.InvitationChallengeRMI;
import game.rmi.QuestionRMI;
import game.controller.AuthenticationController;
import game.controller.ChallengeController;
import game.controller.ChatController;
import game.controller.InvitationChallengeController;
import game.controller.InvitationTeamController;
import game.controller.QuestionController;
import game.interfaces.InvitationTeamInterface;
import game.rmi.InvitationTeamRMI;
import game.util.Util;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas
 */
public class Server {
    
    public static void main(String[] args) {
        
        try {
            Registry reg = LocateRegistry.createRegistry(1090);
            
            AuthenticationController ac = new AuthenticationController();
            AuthenticationInterface ai = new AuthenticationRMI(ac);
            ac.start();            
            reg.rebind(Util.INTERFACE_AUTHENTICATION, ai);      
            
            ChatController cc = new ChatController(ai);
            ChatInterface ci = new ChatRMI(cc);
            cc.start();
            reg.rebind(Util.INTERFACE_CHAT, ci);
            
            ChallengeController chc = new ChallengeController(ai);
            ChallengeInterface chi = new ChallengeRMI(chc);
            chc.start();
            reg.rebind(Util.INTERFACE_CHALLENGE, chi);
            
            QuestionController qtc = new QuestionController(ai);
            QuestionInterface qi = new QuestionRMI(qtc);
            qtc.start();
            reg.rebind(Util.INTERFACE_QUESTION, qi);
            
            InvitationChallengeController ic = new InvitationChallengeController(ai,chi);
            InvitationChallengeInterface ii = new InvitationChallengeRMI(ic);
            ic.start();
            reg.rebind(Util.INTERFACE_INVITATION_CHALLENGE, ii);
            
            InvitationTeamController itc = new InvitationTeamController(ai);
            InvitationTeamInterface iti = new InvitationTeamRMI(itc);
            itc.start();
            reg.rebind(Util.INTERFACE_INVITATION_TEAM,iti);
            
        } catch (RemoteException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
