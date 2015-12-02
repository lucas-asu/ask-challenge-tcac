/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.controller;

import game.bean.Message;
import game.bean.Player;
import game.bean.Team;
import game.interfaces.AuthenticationInterface;
import game.interfaces.PlayerInterface;
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
public class ChatController extends Thread {

    private AuthenticationInterface ai;
    private Map<String, Message> messagesAll;
    private List<String> words;
    private Map<String, Map<String, Message>> messagesTeam;

    public ChatController(AuthenticationInterface ai) {
        this.ai = ai;
        this.messagesAll = new HashMap<>();
        this.messagesTeam = new HashMap<>();
        this.words = new ArrayList();
        words.add("puta");
        words.add("fdp");
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!messagesAll.isEmpty()) {
                for (Message msg : messagesAll.values()) {
                    sendMessageAll(msg);
                    removeMessageToAll(msg);
                }
            }
            if (!messagesTeam.isEmpty()) {
                for (Map.Entry<String, Map<String, Message>> entrySet : messagesTeam.entrySet()) {
                    String key = entrySet.getKey();
                    Map<String, Message> value = entrySet.getValue();
                    if (value != null && !value.isEmpty()) {
                        try {
                            Team t = ai.getTeam(key);
                            for (Message msg : value.values()) {
                                sendMessageTeam(t, msg);
                                removeMessageToTeam(key, msg);
                            }
                        } catch (RemoteException ex) {
                            Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
    }

    public synchronized void addMessageToAll(Message msg) {
        this.messagesAll.put(msg.getID(), msg);
    }

    public synchronized void removeMessageToAll(Message msg) {
        this.messagesAll.remove(msg.getID());
    }

    public synchronized void addMessageToTeam(String teamID, Message msg) {
        if (messagesTeam.containsKey(teamID)) {
            HashMap<String, Message> msgs = (HashMap<String, Message>) messagesTeam.get(teamID);
            msgs.put(msg.getID(), msg);
            messagesTeam.put(teamID, msgs);
        } else {
            HashMap<String, Message> msgs = new HashMap<>();
            msgs.put(msg.getID(), msg);
            messagesTeam.put(teamID, msgs);
        }
    }

    public synchronized void removeMessageToTeam(String teamID, Message msg) {
        HashMap<String, Message> msgs = (HashMap<String, Message>) messagesTeam.get(teamID);
        msgs.remove(msg.getID());
        messagesTeam.put(teamID, msgs);
    }

    public void sendMessageAll(Message msg) {
        msg = filterWords(msg);
        try {
            ArrayList<Player> ps = ai.getAllPlayers();
            for (Player p : ps) {
                PlayerInterface pi = ai.getPlayerInterface(p.getID());
                pi.addMessageAll(msg);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendMessageTeam(Team t, Message msg) {
        msg = filterWords(msg);
        try {
            ArrayList<Player> ps = new ArrayList(t.getMembers().values());
            for (Player p : ps) {
                PlayerInterface pi = ai.getPlayerInterface(p.getID());
                pi.addMessageTeam(msg);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Message filterWords(Message msg) {
        for (String w : words) {
            msg.setDescription(msg.getDescription().replaceAll(w, "$%#@"));
        }
        return msg;
    }
}
