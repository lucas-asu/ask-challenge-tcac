/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.rmi;

import game.bean.Answer;
import game.bean.Challenge;
import game.bean.Question;
import game.interfaces.ChallengeInterface;
import game.controller.ChallengeController;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Lucas
 */
public class ChallengeRMI extends UnicastRemoteObject implements ChallengeInterface {

    private ChallengeController controller;

    public ChallengeRMI(ChallengeController controller) throws RemoteException {
        this.controller = controller;
    }

    @Override
    public void newChallenge(Challenge cha) throws RemoteException {
        System.out.println(this.getClass()+" >>NEW-CHALLENGE "+cha);
        controller.newChallenge(cha);
    }

    @Override
    public void removeChallenge(String chaID) throws RemoteException {
        System.out.println(this.getClass()+" >>REMOVE-CHALLENGE "+chaID);
        controller.removeChallenge(chaID);
    }
    
    

    @Override
    public void addQuestion(String challengeID,Question q) throws RemoteException {
        System.out.println(this.getClass()+" >>ADD-QUESTION "+q+" on "+challengeID);
        controller.addQuestion(challengeID,q);
    }

    @Override
    public void addAnswer(String challengeID,Answer a) throws RemoteException {
        System.out.println(this.getClass()+" >>ADD-ANSWER "+a+" on "+challengeID);
        controller.addAnswer(challengeID,a);
    }

    @Override
    public void nextRound(String id) throws RemoteException {
        System.out.println(this.getClass()+" >>NEXT-ROUND "+id);
        controller.nextRound(id);
    }

    @Override
    public void start(String id) throws RemoteException {
        System.out.println(this.getClass()+" >>START "+ id);
        controller.start(id);
    }

    @Override
    public void finish(String id) throws RemoteException {
        System.out.println(this.getClass()+" >>FINISH "+id);
        controller.finish(id);
    }
}
