/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.controller;

import game.bean.Answer;
import game.bean.Challenge;
import game.bean.Player;
import game.bean.Question;
import game.bean.Round;
import game.bean.Team;
import game.interfaces.AuthenticationInterface;
import game.util.Util;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas
 */
public class ChallengeController extends Thread {

    private AuthenticationInterface ai;
    private Map<String, Challenge> challenges;
    private Map<String, Integer> challengeState;
    private Map<String, Team> teamAsk;
    private Map<String, Team> teamAnswer;
    private Map<String, Question> questionsByChallenge;
    private Map<String, List<Round>> roundsByChallenge;
    private Map<String, Boolean> challengeLocked;

    public ChallengeController(AuthenticationInterface ai) {
        this.ai = ai;
        this.challenges = new HashMap<>();
        this.challengeState = new HashMap<>();
        this.teamAsk = new HashMap<>();
        this.teamAnswer = new HashMap<>();
        this.challengeLocked = new HashMap<>();
        this.questionsByChallenge = new HashMap<>();
        this.roundsByChallenge = new HashMap<>();
    }

    public void run() {
        Challenge cha = null;
        Team tAsk = null;
        Team tAnswer = null;
        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(ChallengeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            List<Challenge> cs = new ArrayList(challenges.values());
            for (Challenge c : cs) {
                if (c.isStarted()) {
                    /*
                     Verificar os estados da máquina de estado do challenge
                     */
                    Map<String, Integer> chaStat = new HashMap(challengeState);
                    for (Map.Entry<String, Integer> entrySet : chaStat.entrySet()) {
                        String key = entrySet.getKey();
                        Integer value = entrySet.getValue();
                        cha = challenges.get(key);                        
                        if (challengeLocked.containsKey(cha.getID()) && !challengeLocked.get(cha.getID())) {
                            switch (value) {
                                case Util.CHALLANGE_DO_QUESTION:
                                    System.out.println("Challenge DO Question");
                                    whoStart(cha);
                                    tAsk = teamAsk.get(key);
                                    tAnswer = teamAnswer.get(key);
                                    doQuestion(tAsk);
                                    waitQuestion(tAnswer);
                                    challengeLocked.put(cha.getID(), true);
                                    break;
                                case Util.CHALLANGE_DO_ANSWER:
                                    System.out.println("Challenge Do Answer");                                    
                                    tAsk = teamAsk.get(key);
                                    tAnswer = teamAnswer.get(key);
                                    doAnswer(cha.getID(), tAnswer);
                                    waitAnswer(tAsk);
                                    challengeLocked.put(cha.getID(), true);
                                    break;
                            }
                        }
                    }
                }
            }

        }
    }

    public synchronized void newChallenge(Challenge cha) {
        challenges.put(cha.getID(), cha);
    }

    public synchronized void removeChallenge(String chaID) {
        challenges.remove(chaID);
    }

    public synchronized void addQuestion(String challengeID, Question q) {
        System.out.println("Add question "+q);
        questionsByChallenge.put(challengeID, q);
        
        challengeState.put(challengeID, Util.CHALLANGE_DO_ANSWER);
        Challenge cha = challenges.get(challengeID);
        challengeLocked.put(cha.getID(), false);
    }

    public synchronized void addAnswer(String challengeID, Answer a) {
        Question qt = questionsByChallenge.get(challengeID);
        boolean isCorrect = checkAnswer(qt, a);
        Round rd = new Round(qt, a);
        if (isCorrect) {
            rd.setWinnerID(a.getCreatorID());
        } else {
            rd.setWinnerID(qt.getCreatorID());
        }
        saveRound(challengeID, rd);
        Challenge cha = challenges.get(challengeID);
        if (roundsByChallenge.get(cha.getID()).size() < cha.getLimitOfRounds()) {
            nextRound(challengeID);
            challengeLocked.put(challengeID, true);
        } else {
            finish(challengeID);
        }

    }

    public synchronized void nextRound(String id) {
        questionsByChallenge.remove(id);
    }

    public synchronized void start(String id) {
        Challenge cha = challenges.get(id);
        cha.setStarted(true);
        challenges.put(id, cha);
        try {
            ai.removePoint(cha.getTeamA().getID(), cha.getPoints());
            ai.removePoint(cha.getTeamB().getID(), cha.getPoints());
            for (Player p : cha.getTeamA().getMembers().values()) {
                ai.getPlayerInterface(p.getID()).startChallenge(cha);
            }
            for (Player p : cha.getTeamB().getMembers().values()) {
                ai.getPlayerInterface(p.getID()).startChallenge(cha);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ChallengeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        challengeLocked.put(id, false);
        challengeState.put(cha.getID(), Util.CHALLANGE_DO_QUESTION);
    }

    public synchronized void finish(String id) {
        Challenge cha = challenges.get(id);
        /*
         processo de dividir os pontos e avisar os envolvidos no desafio
         */
        int tArounds = 0;
        int tBrounds = 0;

        for (Round rd : roundsByChallenge.get(cha.getID())) {
            if (rd.getWinnerID().equalsIgnoreCase(cha.getTeamA().getID())) {
                tArounds++;
            } else {
                tBrounds++;
            }
        }
        Team winner = null;
        try {
            if (tArounds > tBrounds) {
                winner = cha.getTeamA();
                ai.addPoint(cha.getTeamA().getID(), cha.getPoints() * 2);
            } else if (tBrounds > tArounds) {
                ai.addPoint(cha.getTeamB().getID(), cha.getPoints() * 2);
                winner = cha.getTeamB();
            } else {
                ai.addPoint(cha.getTeamA().getID(), cha.getPoints());
                ai.addPoint(cha.getTeamB().getID(), cha.getPoints());
            }

            for (Player p : cha.getTeamA().getMembers().values()) {
                ai.getPlayerInterface(p.getID()).finish(winner);
            }
            for (Player p : cha.getTeamB().getMembers().values()) {
                ai.getPlayerInterface(p.getID()).finish(winner);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ChallengeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        deleteChallenge(cha);
    }

    private void deleteChallenge(Challenge cha) {
        this.challenges.remove(cha.getID());
    }

    private void saveRound(String challengeID, Round rd) {
        List<Round> rds = null;
        if (roundsByChallenge.containsKey(challengeID)) {
            rds = roundsByChallenge.get(challengeID);
            rds.add(rd);
        } else {
            rds = new ArrayList<>();
            rds.add(rd);
        }
        this.roundsByChallenge.put(challengeID, rds);
    }

    private boolean checkAnswer(Question qt, Answer ans) {
        if(qt.getAlternativeCorrect() == ans.getAlternative()) {
            return true;
        }
        return false;
    }

    private void doQuestion(Team teamAsk) {
        Player selector = selectRandomPlayer(new ArrayList(teamAsk.getMembers().values()));
        for (Player p : teamAsk.getMembers().values()) {
            try {
                ai.getPlayerInterface(p.getID()).selectQuestion(selector);
            } catch (RemoteException ex) {
                Logger.getLogger(ChallengeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void waitQuestion(Team teamAnswer) {
        for (Player p : teamAnswer.getMembers().values()) {
            try {
                ai.getPlayerInterface(p.getID()).waitQuestion();
            } catch (RemoteException ex) {
                Logger.getLogger(ChallengeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void doAnswer(String id, Team teamAnswer) {
        Player selector = selectRandomPlayer(new ArrayList(teamAnswer.getMembers().values()));
        for (Player p : teamAnswer.getMembers().values()) {
            try {
                System.out.println(questionsByChallenge.containsKey(id));
                ai.getPlayerInterface(p.getID()).selectAnswer(questionsByChallenge.get(id), selector);
            } catch (RemoteException ex) {
                Logger.getLogger(ChallengeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void waitAnswer(Team teamAsk) {
        for (Player p : teamAsk.getMembers().values()) {
            try {
                ai.getPlayerInterface(p.getID()).waitAnswer();
            } catch (RemoteException ex) {
                Logger.getLogger(ChallengeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void whoStart(Challenge cha) {
        if (new Random().nextBoolean()) {
            teamAsk.put(cha.getID(), cha.getTeamA());
            teamAnswer.put(cha.getID(), cha.getTeamB());
        } else {
            teamAsk.put(cha.getID(), cha.getTeamB());
            teamAnswer.put(cha.getID(), cha.getTeamA());
        }

    }

    private Player selectRandomPlayer(List<Player> pls) {
        for (Player pl : pls) {
            if (new Random().nextBoolean()) {
                return pl;
            }
        }
        return pls.get(pls.size() - 1);
    }

}
