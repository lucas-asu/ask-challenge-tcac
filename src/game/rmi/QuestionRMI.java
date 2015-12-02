/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.rmi;

import game.bean.Question;
import game.interfaces.QuestionInterface;
import game.controller.QuestionController;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class QuestionRMI extends UnicastRemoteObject implements QuestionInterface {
    
    private QuestionController controller;
    
    public QuestionRMI(QuestionController controller) throws RemoteException {
        this.controller = controller;
    }

    @Override
    public void save(String teamID, Question qt) throws RemoteException {
        System.out.println(this.getClass()+" >>SAVE-QUESTION "+qt+ " on "+teamID);
        controller.save(teamID, qt);
    }

    @Override
    public void update(String teamID, Question qt) throws RemoteException {
        System.out.println(this.getClass()+" >>UPDATE-QUESTION "+qt+ " on "+teamID);
        controller.update(teamID, qt);
    }

    @Override
    public void delete(String teamID, Question qt) throws RemoteException {
        System.out.println(this.getClass()+" >>DELETE-QUESTION "+qt+ " on "+teamID);
        controller.delete(teamID, qt);
    }

    @Override
    public List<Question> getAll(String teamID) throws RemoteException {
       System.out.println(this.getClass()+" >>GET-ALL on "+teamID);
        return controller.getAll(teamID);
    }
}

   
   

