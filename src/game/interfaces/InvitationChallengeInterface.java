/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.interfaces;

import game.bean.Invitation;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Lucas
 */
public interface InvitationChallengeInterface extends Remote{
    
    void newInternalInvitation(Invitation inv) throws RemoteException;
    void newExternalInvitation(Invitation inv) throws RemoteException;
    void answerInternalInvitation(Invitation inv) throws RemoteException;
    void answerExternalInvitation(Invitation inv) throws RemoteException;
    
}
