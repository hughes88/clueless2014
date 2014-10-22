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
    
    public String RoomName(String name);
    
    public int numberOfOccupents(int occupants);
    
    public void allowEntry();
            
    public void removePlayer();
    
   
    
}
