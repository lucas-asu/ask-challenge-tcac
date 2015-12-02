/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.bean;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author Lucas
 */
public class Question implements Serializable {

    private final String ID;
    private final String creatorID;
    private String description;
    private Map<Character, String> alternatives;
    private char alternativeCorrect;

    public Question(String creatorID, String description, Map<Character, String> alternatives, char alternativeCorrect) {
        this.ID = "QTS:" + (creatorID + System.currentTimeMillis()).hashCode();
        this.creatorID = creatorID;
        this.description = description;
        this.alternatives = alternatives;
        this.alternativeCorrect = alternativeCorrect;
    }

    public String getID() {
        return ID;
    }

    public String getCreatorID() {
        return creatorID;
    }

    public String getDescription() {
        return description;
    }

    public Map<Character, String> getAlternatives() {
        return alternatives;
    }

    public char getAlternativeCorrect() {
        return alternativeCorrect;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAlternatives(char alt, String description) {
        this.alternatives.put(alt, description);
    }

    public void setAlternativeCorrect(char alternativeCorrect) {
        this.alternativeCorrect = alternativeCorrect;
    }

    @Override
    public String toString() {
        return "'" + description 
                + "' A)" + alternatives.get('A') 
                + " B)" + alternatives.get('B') 
                + " C)" + alternatives.get('C') 
                + " D)" + alternatives.get('D')
                + " Answer("+alternativeCorrect+")";
    }

}
