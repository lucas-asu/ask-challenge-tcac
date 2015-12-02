/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.interfaces;

import game.bean.Question;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Lucas
 */
public interface QuestionInterface extends Remote{
    
    void save(String teamID, Question qt) throws RemoteException;  
    void update(String teamID, Question qt) throws RemoteException;  
    void delete(String teamID, Question qt) throws RemoteException;  
    List<Question> getAll(String teamID) throws RemoteException;  
    
}
