/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.rmi;

import game.bean.Invitation;
import game.controller.InvitationTeamController;
import game.interfaces.InvitationTeamInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Lucas
 */
public class InvitationTeamRMI extends UnicastRemoteObject implements InvitationTeamInterface {
    
    private InvitationTeamController controller;
    
    public InvitationTeamRMI(InvitationTeamController controller)throws RemoteException {
        this.controller = controller;
    }

    @Override
    public void answerInvitation(Invitation inv) throws RemoteException {
        System.out.println(this.getClass()+" Answer team invitation... "+inv);
        controller.addInvitationAnswer(inv); //To change body of generated methods, choose Tools | Templates.
    }
   
    @Override
    public void newInvitation(Invitation inv) throws RemoteException {
        System.out.println(this.getClass()+" new team invitation... "+inv);
        controller.addlInvitation(inv);
    }
    
    
    
}
