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
public class NonCornerRoom implements Room{
    String roomname=null;
    int roomoccupants;
    String occupant=null;
    
    public NonCornerRoom(String name){
        roomname=name;
        
    }

    @Override
    public String getRoomName() {
        return roomname;
        
    }
    
    @Override
    public void setRoomName(String name) {
        roomname=name;
        
    }
    

    @Override
    public int numberOfOccupents(int occupants) {
        
        roomoccupants=occupants;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String Color() {
        String color;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean allowEntry () {
        boolean roomOpen;
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String removePlayer(String name) {
        occupant=name;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
