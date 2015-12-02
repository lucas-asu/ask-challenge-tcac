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
public class Round implements Serializable {
    
    private final String ID;
    private final Question question;
    private final  Answer answer;
    private String winnerID;

    public Round(Question question, Answer answer) {
        this.ID = "RND:"+(question.toString()+System.currentTimeMillis()).hashCode();
        this.question = question;
        this.answer = answer;
    }      

    public String getID() {
        return ID;
    }

    public Question getQuestion() {
        return question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public String getWinnerID() {
        return winnerID;
    }

    public void setWinnerID(String winnerID) {
        this.winnerID = winnerID;
    }
    
}
