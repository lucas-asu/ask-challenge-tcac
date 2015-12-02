/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.view.Init;

/**
 *
 * @author Lucas
 */
public class Game {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        try {
//            Registry reg = LocateRegistry.getRegistry("localhost", 1090);
//            AuthenticationInterface ai = (AuthenticationInterface) reg.lookup(Util.INTERFACE_AUTHENTICATION);
//            ArrayList<Player> ps = ai.getAllPlayers();
//            for (int i = 1; i < 21; i++) {
//                Player p = new Player("Player" + i);
//                if (!ai.existPlayer(p.getName())) {
//                    PlayerInterface pi = new PlayerRMI();
//                    ai.newPlayer(p, pi);
//                    ps.add(p);
//                } else {
//                    System.out.println("Player's name already exist! " + p.getName());
//                }
//            }
////
////             QuestionInterface qi = (QuestionInterface) reg.lookup("QTN");
////            
//            Team t1 = new Team("Team01", ps.get(0));
//            Team t2 = new Team("Team02", ps.get(10));
//            ai.newTeam(t1);            
////            for (int i = 1; i < 10; i++) {
////                ai.newMember(t1.getID(), ps.get(i));
////                PlayerInterface pi = new PlayerRMI();
////                ai.newPlayer(ps.get(i), pi);
////                
////            }
//            ai.newTeam(t2);
////            for (int i = 10; i < 20; i++) {
////                ai.newMember(t2.getID(), ps.get(i));
////                PlayerInterface pi = new PlayerRMI();
////                ai.newPlayer(ps.get(i), pi);
////            }
////
//            ArrayList<Team> ts = ai.getAllTeams();
//            System.out.println(ts);
////
//////            ChatInterface chat = (ChatInterface) reg.lookup("CHAT");
//////            Message msg = new Message(ps.get(0).getName(), "Ola", Constants.CHAT_ALL);
//////            chat.newMessageToAll(msg);
//////            chat.newMessageToTeam(ts.get(0).getID(), msg);
////            InvitationInterface ii = (InvitationInterface) reg.lookup("INV");
////            Invitation inv = new Invitation("Dapa?", ps.get(0).getID(), t1.getID(), t2.getID());
////            System.out.println("");
////            ii.newInternalInvitation(inv);
//
//            QuestionInterface qi = (QuestionInterface) reg.lookup(Util.INTERFACE_QUESTION);
//            Map<Character,String> alt = new HashMap<>();
//            alt.put('A', "Alternativa A");
//            alt.put('B', "Alternativa B");
//            alt.put('C', "Alternativa C");
//            alt.put('D', "Alternativa D");
//            Question qt = new Question(t1.getID(), "Qual o nome do Pele?", alt,'A');
//            qi.save(t1.getID(), qt);
//            System.out.println(qi.getAll(t1.getID()));
//            
//            ChallengeInterface chi = (ChallengeInterface) reg.lookup(Util.INTERFACE_CHALLENGE);
//            Challenge ch = new Challenge(t1, t2, 2, 100);
//            chi.newChallenge(ch);
//            chi.addQuestion(ch.getID(), qt);
//            Answer ans = new Answer(t2.getID(), 'A');
//            chi.addAnswer(ch.getID(), ans);
//            chi.finish(ch.getID());
//            
////            qt.setDescription("Juliana?");
////            qt.setAlternatives('A', "Adriano?");
////            qi.save("T01", qt);
////            System.out.println(qi.getAll("T01"));
////            qi.delete("T01", qt);
////            System.out.println(qi.getAll("T01"));
//        } catch (RemoteException ex) {
//            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (NotBoundException ex) {
//            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
//        }

        Init init = new Init();
        init.setLocationRelativeTo(null);
        init.setVisible(true);
        
    }

}
