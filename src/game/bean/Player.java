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
public class Player implements Serializable {
    
    private final String ID;
    private final String name;

    public Player(String name) {
        this.ID = "PYR:"+(name+System.currentTimeMillis()).hashCode();
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    
    
    
}
