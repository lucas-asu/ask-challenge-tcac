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
public class Message implements Serializable {
    
    private final String ID;
    private final String from;
    private String to;
    private String description;
    private final int type;

    public Message(String from, String description, int type) {
        this.ID = "MSG:"+(""+System.currentTimeMillis()).hashCode();
        this.from = from;
        this.description = description;
        this.type = type;
    }

    public Message(String from, String to, String description, int type) {
        this.ID = "MSG:"+(""+System.currentTimeMillis()).hashCode();
        this.from = from;
        this.to = to;
        this.description = description;
        this.type = type;
    }

    public String getID() {
        return ID;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDescription() {
        return description;
    }

    public int getType() {
        return type;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    

    @Override
    public String toString() {
        return "Message{" + "ID=" + ID + ", from=" + from + ", to=" + to + ", description=" + description + ", type=" + type + '}';
    }

   
}
