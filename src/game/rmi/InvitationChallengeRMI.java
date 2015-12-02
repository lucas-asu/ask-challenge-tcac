/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.rmi;

import game.bean.Invitation;
import game.interfaces.InvitationChallengeInterface;
import game.controller.InvitationChallengeController;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Lucas
 */
public class InvitationChallengeRMI extends UnicastRemoteObject implements InvitationChallengeInterface {
    
    private InvitationChallengeController controller;
    
    public InvitationChallengeRMI(InvitationChallengeController controller)throws RemoteException {
        this.controller = controller;
    }

    @Override
    public void answerInternalInvitation(Invitation inv) throws RemoteException {
        System.out.println(this.getClass()+" Answer invitation... "+inv);
        controller.addInvitationInternalAnswer(inv); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void newInternalInvitation(Invitation inv) throws RemoteException {
        System.out.println(this.getClass()+" New internal invitation... "+inv);
        controller.addInternalInvitation(inv); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void answerExternalInvitation(Invitation inv) throws RemoteException {
        System.out.println(this.getClass()+" New external invitation... "+inv);
        controller.addInvitationExternalAnswer(inv);//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void newExternalInvitation(Invitation inv) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
