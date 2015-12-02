/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.controller;

import game.bean.Challenge;
import game.bean.Invitation;
import game.bean.Player;
import game.bean.Team;
import game.interfaces.AuthenticationInterface;
import game.interfaces.ChallengeInterface;
import game.interfaces.PlayerInterface;
import game.util.Util;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas
 */
public class InvitationChallengeController extends Thread {

    private AuthenticationInterface ai;
    private ChallengeInterface ci;
    private Map<String, Invitation> invitationsInternal;
    private Map<String, Invitation> invitationsExternal;
    private Map<String, List<Invitation>> invitationsInternalAnswer;
    private Map<String, List<Invitation>> invitationsExternalAnswer;

    public InvitationChallengeController(AuthenticationInterface ai, ChallengeInterface ci) {
        this.ai = ai;
        this.ci = ci;
        this.invitationsInternal = new HashMap<>();
        this.invitationsExternal = new HashMap<>();
        this.invitationsInternalAnswer = new HashMap<>();
        this.invitationsExternalAnswer = new HashMap<>();
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(InvitationChallengeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!invitationsInternal.isEmpty()) {
                for (Invitation inv : invitationsInternal.values()) {

                    try {
                        Team t = ai.getTeam(inv.getFrom());
                        for (Player p : t.getMembers().values()) {
                            PlayerInterface pi = ai.getPlayerInterface(p.getID());
                            pi.addInternalInvitation(inv);
                        }
                        removeInternalInvitation(inv);
                    } catch (RemoteException ex) {
                        Logger.getLogger(InvitationChallengeController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
            if (!invitationsExternal.isEmpty()) {
                for (Invitation inv : invitationsExternal.values()) {
                    try {
                        Team t = ai.getTeam(inv.getTo());
                        for (Player p : t.getMembers().values()) {
                            PlayerInterface pi = ai.getPlayerInterface(p.getID());
                            pi.addExternalInvitation(inv);
                        }
                        removeExternalInvitation(inv);
                    } catch (RemoteException ex) {
                        Logger.getLogger(InvitationChallengeController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
            if (!invitationsInternalAnswer.isEmpty()) {
//                System.out.println(invitationsResponse.size());
                for (Map.Entry<String, List<Invitation>> entrySet : invitationsInternalAnswer.entrySet()) {

                    try {
                        String key = entrySet.getKey();
                        List<Invitation> value = entrySet.getValue();
                        Team t = ai.getTeam(key);
                        if (value == null && t == null) {
                            break;
                        } else if (value.size() == t.getMembers().size()) {
                            boolean playChallenge = true;
                            String teamID = "";
                            String creator = "";
                            String challengeID = "";
                            int points = 0;
                            for (Invitation inv : value) {
                                creator = inv.getCreator();
                                challengeID = inv.getChallengeID();
                                teamID = inv.getTo();
                                points = inv.getPoints();
                                if (inv.getAnswer() == Util.INVITATION_REJECTED) {
                                    playChallenge = false;
                                }
                            }
                            if (playChallenge) {
                                Invitation inv = new Invitation(challengeID, creator, t.getID(), teamID,points);
                                addExternalInvitation(inv);
                            }
                            removeInvitationInternalAnswer(key);
                        }

                    } catch (RemoteException ex) {
                        Logger.getLogger(InvitationChallengeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
            if (!invitationsExternalAnswer.isEmpty()) {
                for (Map.Entry<String, List<Invitation>> entrySet : invitationsExternalAnswer.entrySet()) {

                    try {
                        String key = entrySet.getKey();
                        List<Invitation> value = entrySet.getValue();
                        Team t = ai.getTeam(key);
                        if (value == null && t == null) {
                            break;
                        } else if (value.size() == t.getMembers().size()) {
                            boolean playChallenge = true;
                            for (Invitation inv : value) {
                                if (inv.getAnswer() == Util.INVITATION_REJECTED) {
                                    playChallenge = false;
                                }
                            }
                            if (playChallenge) {
                                System.out.println("Starting Challenge...");
                                String chaID = value.get(0).getChallengeID();
                                ci.start(chaID);
                            } else {
                                System.out.println("Removing Challenge...");
                                String chaID = value.get(0).getChallengeID();
                                ci.removeChallenge(chaID);
                            }
                            removeInvitationExternalAnswer(key);
                        }

                    } catch (RemoteException ex) {
                        Logger.getLogger(InvitationChallengeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }
    }

    public synchronized void addInternalInvitation(Invitation inv) {
        invitationsInternal.put(inv.getID(), inv);
    }

    public synchronized void addExternalInvitation(Invitation inv) {
        invitationsExternal.put(inv.getID(), inv);
    }

    public synchronized void addInvitationInternalAnswer(Invitation inv) {
        if (invitationsInternalAnswer.containsKey(inv.getFrom())) {
            List<Invitation> ivs = invitationsInternalAnswer.get(inv.getFrom());
            ivs.add(inv);
            invitationsInternalAnswer.put(inv.getFrom(), ivs);
        } else {
            System.out.println("creating internal...");
            List<Invitation> ivs = new ArrayList<>();
            ivs.add(inv);
            invitationsInternalAnswer.put(inv.getFrom(), ivs);
        }
    }

    public synchronized void addInvitationExternalAnswer(Invitation inv) {
        if (invitationsExternalAnswer.containsKey(inv.getTo())) {
            List<Invitation> ivs = invitationsExternalAnswer.get(inv.getTo());
            ivs.add(inv);
            invitationsExternalAnswer.put(inv.getTo(), ivs);
        } else {
            System.out.println("creating external...");
            List<Invitation> ivs = new ArrayList<>();
            ivs.add(inv);
            invitationsExternalAnswer.put(inv.getTo(), ivs);
        }
    }

    public synchronized void removeInvitationInternalAnswer(String key) {
        invitationsInternalAnswer.remove(key);
    }

    public synchronized void removeInvitationExternalAnswer(String key) {
        invitationsExternalAnswer.remove(key);
    }

    public synchronized void removeInternalInvitation(Invitation inv) {
        invitationsInternal.remove(inv.getID());
    }

    public synchronized void removeExternalInvitation(Invitation inv) {
        invitationsExternal.remove(inv.getID());
    }

}
