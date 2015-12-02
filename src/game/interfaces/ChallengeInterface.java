/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.interfaces;

import game.bean.Answer;
import game.bean.Challenge;
import game.bean.Question;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Lucas
 */
public interface ChallengeInterface extends Remote{
    
    void newChallenge(Challenge cha) throws RemoteException;
    void removeChallenge(String chaID) throws RemoteException;
    void addQuestion(String challengeID,Question q) throws RemoteException;
    void addAnswer(String challengeID,Answer a) throws RemoteException;    
    void nextRound(String id) throws RemoteException;    
    void start(String id) throws RemoteException;    
    void finish(String id) throws RemoteException;       
    
}
