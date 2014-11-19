/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Clue;

/**
 *
 * @author Mario
 */
public interface Room {
    String occupant=null;
      
    
    String getRoomName();
    void setRoomName(String name);
    
    int numberOfOccupents(int occupants);
    
    String Color();
    
    boolean allowEntry();
            
    String removePlayer(String name);
    
   
    
}
