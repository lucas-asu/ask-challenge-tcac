/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.bean;

import java.io.Serializable;

/**
 *
 * @author Lucas
 */
public class Notification implements Serializable {
    
    private final String ID;
    private final String message;
    private final int type;
    private String playerID;
    private String teamID;    

    public Notification(String message, String playerID, String teamID, int type) {
        this.ID = "NTF:"+(""+System.currentTimeMillis()).hashCode();
        this.message = message;
        this.playerID = playerID;
        this.teamID = teamID;
        this.type = type;
    }

    public String getID() {
        return ID;
    }

    public String getMessage() {
        return message;
    }

    public String getPlayerID() {
        return playerID;
    }

    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Notification{" + "ID=" + ID + ", message=" + message + ", type=" + type + ", playerID=" + playerID + ", teamID=" + teamID + '}';
    }
    
    
    
}
