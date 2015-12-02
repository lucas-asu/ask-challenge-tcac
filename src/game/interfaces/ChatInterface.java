/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.interfaces;


import game.bean.Message;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Lucas
 */
public interface ChatInterface extends Remote{
    
     void newMessageToAll(Message msg) throws RemoteException;
     void newMessageToTeam(String teamID, Message msg) throws RemoteException;
     void newMessageToPlayer(Message msg) throws RemoteException;
    
}
