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
public interface Player {

    public void move();

    public void makeSuggestion();

    public void rollDie();

    public void useSecretPassage();

    public void leaveRoom();
    
    public void joinRoom();

    public void Accusation();
    
    

}
