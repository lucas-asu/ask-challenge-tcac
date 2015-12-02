/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.rmi;

import game.bean.Message;
import game.interfaces.ChatInterface;
import game.controller.ChatController;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Lucas
 */
public class ChatRMI extends UnicastRemoteObject implements ChatInterface {

    private ChatController controller;
    
    public ChatRMI(ChatController controller) throws RemoteException {
        this.controller = controller;
    }

    @Override
    public void newMessageToAll(Message msg) throws RemoteException {
        System.out.println(this.getClass()+" New message all...");
        controller.addMessageToAll(msg); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void newMessageToTeam(String teamID, Message msg) throws RemoteException {
        System.out.println(this.getClass()+" New message team...");
        controller.addMessageToTeam(teamID, msg);//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void newMessageToPlayer(Message msg) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
