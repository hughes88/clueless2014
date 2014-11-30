
public class mainclue {
	public static void main( String[] args)
	{
		clue game = new clue();
		//figure out who did it, where and with what
		game.whodoneit();
		//place whodoneit into a string for checking, goes room, person, weapon
		String[] murderer= game.murder();
		//deal the cards to the players,
		game.shufflecards();
		//initial game setup, creating all the characters
		character scarlet = new character();
		character green = new character();
		character mustard = new character();
		character plum = new character();
		character white = new character();
		character peacock = new character();
		//dealing cards to each person
		scarlet.setcards(game.dealcards());
		green.setcards(game.dealcards());
		mustard.setcards(game.dealcards());
		plum.setcards(game.dealcards());
		white.setcards(game.dealcards());
		peacock.setcards(game.dealcards());
		//setting default location for each person
		scarlet.setposition(0, 3);
		green.setposition(4, 1);
		mustard.setposition(1, 4);
		plum.setposition(1, 0);
		white.setposition(4, 3);
		peacock.setposition(3, 0);
		//update board with location of all players
		game.initialboard();
		
		//starting the game
		
		
	}
}
