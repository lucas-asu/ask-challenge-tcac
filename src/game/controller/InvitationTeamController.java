/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.controller;

import game.bean.Invitation;
import game.bean.Notification;
import game.bean.Team;
import game.interfaces.AuthenticationInterface;
import game.interfaces.PlayerInterface;
import game.util.Util;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas
 */
public class InvitationTeamController extends Thread {

    private AuthenticationInterface ai;
    private Map<String, Invitation> invitations;
    private Map<String, Invitation> invitationsAnswer;

    public InvitationTeamController(AuthenticationInterface ai) {
        this.ai = ai;
        this.invitations = new HashMap<>();
        this.invitationsAnswer = new HashMap<>();
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(InvitationTeamController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!invitations.isEmpty()) {
                for (Invitation inv : invitations.values()) {
                    try {
                        Team t = ai.getTeam(inv.getTo());
                        PlayerInterface pi = ai.getPlayerInterface(t.getLeader().getID());
                        pi.addInvitationTeam(inv);
                        removeInvitation(inv);                        
                    } catch (RemoteException ex) {
                        Logger.getLogger(InvitationTeamController.class.getName()).log(Level.SEVERE, null, ex);
                    }                  
                }
            }
            if (!invitationsAnswer.isEmpty()) {
//                System.out.println(invitationsResponse.size());
                for (Map.Entry<String, Invitation> entrySet : invitationsAnswer.entrySet()) {

                    try {
                        String key = entrySet.getKey();
                        Invitation value = entrySet.getValue();
                        Team t = ai.getTeam(key);
                        PlayerInterface pi = ai.getPlayerInterface(value.getCreator());
                        if(value.getAnswer() == Util.NOTIFICATION_JOIN_TEAM_ACCEPTED){
                            ai.newMember(key, ai.getPlayer(value.getCreator()));    
                            pi.joinTeam(t);
                            Notification ntf = new Notification("The team "+t.getName()+" accepted you", value.getCreator(), key, Util.NOTIFICATION_JOIN_TEAM_ACCEPTED);
                            pi.addNotification(ntf);
                            
                        } else {
                            Notification ntf = new Notification("The team "+t.getName()+" rejected you", value.getCreator(), key, Util.NOTIFICATION_JOIN_TEAM_REJECTED);
                            pi.addNotification(ntf);
                        }
                        
                        removeInvitationAnswer(key);
                    } catch (RemoteException ex) {
                        Logger.getLogger(InvitationTeamController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }
    }

    public synchronized void addlInvitation(Invitation inv) {
        invitations.put(inv.getID(), inv);
    }
   
    public synchronized void addInvitationAnswer(Invitation inv) {
        invitationsAnswer.put(inv.getTo(), inv);
    }
    

    public synchronized void removeInvitationAnswer(String key) {
        invitationsAnswer.remove(key);
    }
    

    public synchronized void removeInvitation(Invitation inv) {
        invitations.remove(inv.getID());
    }
    
}
