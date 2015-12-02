/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.util;

/**
 *
 * @author Lucas
 */
public interface Util {
    
    int CHAT_ALL = 1;
    int CHAT_TEAM = 2;
    int CHAT_PLAYER = 3;
    
    int CHALLANGE = 10;
    int CHALLANGE_DO_QUESTION = 11;
    int CHALLANGE_WAIT_QUESTION = 12;
    int CHALLANGE_DO_ANSWER = 13;
    int CHALLANGE_WAIT_ANSWER = 14;
    
    int INVITATION_ACCEPTED = 20;
    int INVITATION_REJECTED = 21;
    
    int NOTIFICATION = 30; 
    int NOTIFICATION_JOIN_TEAM_ACCEPTED = 31; 
    int NOTIFICATION_JOIN_TEAM_REJECTED = 32; 
    int NOTIFICATION_REQUEST_JOIN_TEAM = 33; 
    
    int QUESTION = 40; 
    
    int AUTHENTICATION = 40;    
    
    String INTERFACE_AUTHENTICATION = "Authehtication";
    String INTERFACE_CHALLENGE = "Challenge";
    String INTERFACE_CHAT = "Chat";
    String INTERFACE_INVITATION_CHALLENGE = "InvitationChallenge";
    String INTERFACE_INVITATION_TEAM = "InvitationTeam";
    String INTERFACE_NOTIFICATION = "Notification";
    String INTERFACE_QUESTION = "Question";
    
}
