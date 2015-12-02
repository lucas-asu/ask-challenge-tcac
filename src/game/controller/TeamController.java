package game.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import game.bean.Answer;
import game.bean.Challenge;
import game.bean.Invitation;
import game.bean.Player;
import game.bean.Question;
import game.bean.Team;
import game.interfaces.ChallengeInterface;
import game.view.InvitationChallenge;
import game.view.InvitationJoinTeam;
import game.view.MainWindow;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas
 */
public class TeamController extends Thread {

    private MainWindow window;
    public Team team;
    public Challenge challenge;
    private final PlayerController controller;

    public TeamController(PlayerController playerController, MainWindow mw) {
        this.controller = playerController;
        this.window = mw;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(15000);
            } catch (InterruptedException ex) {
                Logger.getLogger(TeamController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (team != null) {
                try {
                    team = controller.factory.getAuthenticationInterface().getTeam(team.getID());
                } catch (RemoteException ex) {
                    Logger.getLogger(TeamController.class.getName()).log(Level.SEVERE, null, ex);
                }
                window.updateTeam();
                if(team.getLeader().getID().equalsIgnoreCase(controller.player.getID())){
                    window.beLeader();
                }
            }
        }
    }

    public void addInvitation(Invitation inv) {
        InvitationChallenge ijt = new InvitationChallenge(controller, this, inv, window);
        ijt.setLocationRelativeTo(window);
        ijt.setVisible(true);
    }
    
    public void sendInvitationChallenge(Challenge cha){
        Invitation inv = new Invitation(cha.getID(), controller.player.getID(), team.getID(), cha.getTeamB().getID(),cha.getPoints());
        try {
            controller.factory.getInvitationChallengeInterface().newInternalInvitation(inv);
        } catch (RemoteException ex) {
            Logger.getLogger(TeamController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    public void createTeam(String name) {
        try {
            team = new Team(name, controller.player);
            controller.factory.getAuthenticationInterface().newTeam(team);
            controller.sendMessageGlobal(controller.player.getName() + " has created the Team '" + name + "'");
        } catch (RemoteException ex) {
            Logger.getLogger(TeamController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendInviteJoinTeam(Team t) {
        Invitation inv = new Invitation(controller.player.getID(), t.getID());
        try {
            controller.factory.getInvitationTeamInterface().newInvitation(inv);
        } catch (RemoteException ex) {
            Logger.getLogger(TeamController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void inviteJoinTeam(Invitation inv) {
        InvitationJoinTeam ijt = new InvitationJoinTeam(controller, this, inv, window);
        ijt.setLocationRelativeTo(window);
        ijt.setVisible(true);

//      
    }

    public void joinTeam(Team t) {
        team = t;
        window.setTeam();
    }

    public void leaveTeam() {
        try {
            controller.factory.getAuthenticationInterface().removeMember(team.getID(), controller.player);
            controller.sendMessageTeam(controller.player.getName() + " has left the Team", team);
        } catch (RemoteException ex) {
            Logger.getLogger(TeamController.class.getName()).log(Level.SEVERE, null, ex);
        }
        team = null;
    }

    public boolean checkTeam(String t) {
        try {
            return controller.factory.getAuthenticationInterface().existTeam(t);
        } catch (RemoteException ex) {
            Logger.getLogger(TeamController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public List<Team> getAllTeams() {
        try {
            return controller.factory.getAuthenticationInterface().getAllTeams();
        } catch (RemoteException ex) {
            Logger.getLogger(TeamController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void startChallenge(Challenge cha) {
        challenge = cha;
        String msg = "";
        if (team.getID().equalsIgnoreCase(cha.getTeamA().getID())) {
            msg = "The Challenge with Team " + cha.getTeamB().getName() + " will start in a few seconds...";
        } else {
            msg = "The Challenge with Team " + cha.getTeamA().getName() + " will start in a few seconds...";
        }
        window.showNotification(msg);
    }

    public void selectQuestion(Player p) {
        String msg = "";
        if (controller.player.getID().equalsIgnoreCase(p.getID())) {
            msg = "You are the responsable to select a question for your team "
                    + "\nTalk with your team to decide it!";
            window.updateSelectQuestions();
        } else {
            msg = "Your coleague call " + p.getName() + " is the responsale to select the question"
                    + "\nPlease, Talk with him(her)!";
        }
        window.showNotification(msg);
    }

    public void sendQuestion(Question qt) {
        System.out.println("Sending question!");
        try {
            ChallengeInterface ci = controller.factory.getChallengeInterface();
            ci.addQuestion(challenge.getID(), qt);            
            window.cleanQuestion();
        } catch (RemoteException ex) {
            Logger.getLogger(TeamController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void selectAnswer(Question qt, Player p) {
        String msg = "";
        if (controller.player.getID().equalsIgnoreCase(p.getID())) {
            msg = "You are the responsable to select a answer for your team "
                    + "\nTalk with your team to decide it!";
        } else {
            msg = "Your coleague call " + p.getName() + " is the responsale to select the answer"
                    + "\nPlease, Talk with him(her)!";
        }
        window.showNotification(msg);
        window.showQuestion(qt,p);
    }

    public void sendAnswer(Answer ans) {
        try {
            ChallengeInterface ci = controller.factory.getChallengeInterface();
            ci.addAnswer(challenge.getID(), ans);
            window.cleanAnswer();
        } catch (RemoteException ex) {
            Logger.getLogger(TeamController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void waitQuestion() {
        String msg = "Please, wait for the another team select a question!";
        window.showNotification(msg);
    }

    public void waitAnswer() {
        String msg = "Please, wait for the another team select a answer!";
        window.showNotification(msg);
    }

    public void finish(Team t) {
        String msg = "";
        if (t == null) {
            msg = "Your team tied the challenge =|";
        } else if (team.getID().equalsIgnoreCase(t.getID())) {
            msg = "Your team has won the challenge =D";
        } else {
            msg = "Your team has lost =("
                    + "\nDon`t give up, try again!";
        }
        window.showNotification(msg);
        try {
            team = controller.factory.getAuthenticationInterface().getTeam(team.getID());
        } catch (RemoteException ex) {
            Logger.getLogger(TeamController.class.getName()).log(Level.SEVERE, null, ex);
        }
        window.updateTeam();
    }

    public boolean couldILeave() {
        try {
            team = controller.factory.getAuthenticationInterface().getTeam(team.getID());
            if (team.getLeader().getID().equalsIgnoreCase(controller.player.getID()) && team.getMembers().size() > 1) {
                return false;
            }
            return true;
        } catch (RemoteException ex) {
            Logger.getLogger(TeamController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean couldIChallenge() {
        try {
            List<Question> qts = controller.factory.getQuestionInterface().getAll(team.getID());
            if (qts.size() > 0) {
                return true;
            }

        } catch (RemoteException ex) {
            Logger.getLogger(TeamController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
