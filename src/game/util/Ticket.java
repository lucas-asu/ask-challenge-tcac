/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.util;

import java.io.Serializable;

/**
 *
 * @author Lucas
 */
public class Ticket implements Serializable {
    private final String ID;
    private final String creator;
    private final int type;
    private final Object obj;

    public Ticket(String ID, String creator, int type, Object obj) {
        this.ID = ID;
        this.creator = creator;
        this.type = type;
        this.obj = obj;
    }

    public String getID() {
        return ID;
    }

    public String getCreator() {
        return creator;
    }

    public int getType() {
        return type;
    }

    public Object getObj() {
        return obj;
    }
    
    
}
