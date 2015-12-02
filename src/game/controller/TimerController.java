/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.controller;

/**
 *
 * @author Lucas
 */
public class TimerController extends Thread {
    
   private final PlayerController controller;
    
   public TimerController(PlayerController controller){
       this.controller = controller;
   }
    
    public void run(){
        
    }
}
