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
public class Answer implements Serializable {
    
    private final String creatorID;
    private final char alternative;

    public Answer(String creatorID, char alternative) {
        this.creatorID = creatorID;
        this.alternative = alternative;
    }

    public String getCreatorID() {
        return creatorID;
    }

    public char getAlternative() {
        return alternative;
    }

    @Override
    public String toString() {
        return "Answer{" + "creatorID=" + creatorID + ", alternative=" + alternative + '}';
    }    
    
}
