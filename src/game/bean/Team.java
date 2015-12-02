/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Lucas
 */
public class Team implements Serializable {
    
    private final String ID;
    private final String name;
    private final Player creator;
    private Player leader;
    private Map<String,Player> members;
    private int points;

    public Team(String name, Player creator) {
        this.ID = "TM:"+(name+System.currentTimeMillis()).hashCode();
        this.name = name;
        this.creator = creator;
        this.leader = creator;
        this.members = new HashMap<>();
        members.put(creator.getID(), creator);
        this.points = 1000;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public Player getCreator() {
        return creator;
    }

    public Player getLeader() {
        return leader;
    }

    public void setLeader(Player leader) {
        this.leader = leader;
    }    

    public Map<String, Player> getMembers() {
        return members;
    }
    
    public void addMember(Player p){
        members.put(p.getID(), p);
    }
    
    public void removeMember(Player p){
        members.remove(p.getID());
    }
    
    public void addPoint(int pt){
        points += pt;
    }
    
    public void removePoint(int pt){
        points -= pt;
    }

    public int getPoints() {
        return points;
    }

    
    
    @Override
    public String toString() {
//        return "Team{" + "ID=" + ID + ", name=" + name + ", creator=" + creator + ", leader=" + leader + ", members=" + members + '}';
        return name;
    }

       
    
}
