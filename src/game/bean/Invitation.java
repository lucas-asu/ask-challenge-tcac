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
public class Invitation implements Serializable {
    
    private final String ID;
    private final String creator;
    private String challengeID;
    private String from;
    private String to; 
    private int type;
    private int answer;
    private int points;

    public Invitation(String challengeID, String creator,  String from, String to,int points) {
        this.ID = "INV:"+(creator+System.currentTimeMillis()).hashCode();
        this.challengeID = challengeID;
        this.creator = creator;
        this.from = from;
        this.to = to;
        this.points = points;
    }  
    public Invitation(String creator, String to) {
        this.ID = "INV:"+(creator+System.currentTimeMillis()).hashCode();
        this.creator = creator;
        this.to = to;
    }  

    public String getID() {
        return ID;
    }

    public String getChallengeID() {
        return challengeID;
    }   

    public String getCreator() {
        return creator;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getType() {
        return type;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getPoints() {
        return points;
    }
    
    

    @Override
    public String toString() {
        return "Invitation{" + "ID=" + ID + ", creator=" + creator + ", from=" + from + ", to=" + to + ", type=" + type + ", answer=" + answer + '}';
    }

   
    
    
}
