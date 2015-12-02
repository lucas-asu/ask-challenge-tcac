/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.controller;

import game.bean.Question;
import game.interfaces.AuthenticationInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lucas
 */
public class QuestionController extends Thread {

    private AuthenticationInterface ai;
    private Map<String, Map<String, Question>> questions;

    public QuestionController(AuthenticationInterface ai) {
        this.ai = ai;
        this.questions = new HashMap<>();
    }

    public void run() {
        while (true) {
            if (!questions.isEmpty()) {
//                for (Map.Entry<String, List<Question>> entrySet : questions.entrySet()) {
//                    String key = entrySet.getKey();
//                    List<Question> value = entrySet.getValue();
//
//                }
            }
        }
    }

    public synchronized void save(String teamID, Question qt) {
        if (questions.containsKey(teamID)) {
            Map<String, Question> qts = questions.get(teamID);
            qts.put(qt.getID(), qt);
            questions.put(teamID, qts);
        } else {
            Map<String, Question> qts = new HashMap<>();
            qts.put(qt.getID(), qt);
            questions.put(teamID, qts);
        }
        
        
    }

    public synchronized void update(String teamID, Question qt) {
        Map<String, Question> qts = questions.get(teamID);
        qts.put(qt.getID(), qt);
        questions.put(teamID, qts);
    }

    public synchronized void delete(String teamID, Question qt) {
        Map<String, Question> qts = questions.get(teamID);
        qts.remove(qt.getID());
        questions.put(teamID, qts);
    }

    public synchronized List<Question> getAll(String teamID) {
        System.out.println(teamID+questions);
        if(questions.isEmpty() || !questions.containsKey(teamID)){
            questions.put(teamID, new HashMap<>());
        }
        return (List<Question>) new ArrayList(questions.get(teamID).values());
    }
}
