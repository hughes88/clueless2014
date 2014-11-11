
public class mainclue {
	public static void main( String[] args)
	{
		clue game = new clue();
		//figure out who did it
		String[] convict = game.whodoneit();
		//deal the cards to the players, assume that number of players is passed in
		int players = 6;
		game.shufflecards();
		
		
		
		System.out.print(convict[0] + " " + convict[1] + " " + convict[2]);
		
		
	}
}
