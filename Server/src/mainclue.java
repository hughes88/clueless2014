import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class mainclue {
	public static void main( String[] args)
	{
		clue game = new clue();
		//figure out who did it, where and with what
		game.whodoneit();
		//place whodoneit into a string for checking, goes room, person, weapon
		ArrayList<String> murderer= game.murder();
		//deal the cards to the players,
		game.shufflecards();
		//initial game setup, creating all the characters
		character scarlet = new character();
		character green = new character();
		character mustard = new character();
		character plum = new character();
		character white = new character();
		character peacock = new character();
		//set characters names
		scarlet.setcluecharacter("scarlet");
		green.setcluecharacter("green");
		mustard.setcluecharacter("mustard");
		plum.setcluecharacter("plum");
		white.setcluecharacter("white");
		peacock.setcluecharacter("peacock");
		//dealing cards to each person
		scarlet.setcards(game.dealcards());
		green.setcards(game.dealcards());
		mustard.setcards(game.dealcards());
		plum.setcards(game.dealcards());
		white.setcards(game.dealcards());
		peacock.setcards(game.dealcards());
		//setting default location for each person
		scarlet.initialsetposition (0, 3);
		green.initialsetposition (4, 1);
		mustard.initialsetposition (1, 4);
		plum.initialsetposition (1, 0);
		white.initialsetposition (4, 3);
		peacock.initialsetposition (3, 0);
		scarlet.initialsetlastposition (0, 3);
		green.initialsetlastposition (4, 1);
		mustard.initialsetlastposition (1, 4);
		plum.initialsetlastposition (1, 0);
		white.initialsetlastposition (4, 3);
		peacock.initialsetlastposition (3, 0);
		//update board with location of all players
		game.initialboard();
		
		//starting the game
		String[] users = {"scarlet", "mustard", "green", "white", "peacock", "plum"};
		String user=users[0];
		int count = 0;
		while(game.shouldplaygame()){
			ArrayList<String> movelocations = new ArrayList<String>();
			switch(user){
			case "scarlet":
			if(scarlet.returnactive()){
				//start of turn player can move
				boolean again = false;
				scarlet.setcanmove();
				//get all valid moves to send to player
				movelocations=game.validmovelocations(scarlet.returncurrentposition());
				String stat=game.getgamestatus(scarlet.returncharname(),scarlet.getallvalidmoves(movelocations),
						scarlet.getplayerdata(), mustard.getplayerdata(), 
						green.getplayerdata(), white.getplayerdata(), peacock.getplayerdata(),
						plum.getplayerdata());
				System.out.println(stat);
				//send info to player - stub
				//wait for player's response - stub (sit in loop, get response, parse it)
				//based on response do 1 of 3 things, assume json has hash of somesort
				String playerchoice = "move";
				String newlocation = "0,4";
				if(playerchoice.equalsIgnoreCase("move")){
					//read in new location from json
					scarlet.setposition(newlocation);
					scarlet.cantmove();
					again = true;
				}else if(playerchoice.equalsIgnoreCase("suggestion")){
					again = true;
					scarlet.suggestionfalse();
					scarlet.cantmove();
				}else{
					//assume accusation, need to read in from client
					ArrayList<String> accusation = new ArrayList<String>();
					accusation.add("MR GREEN");
					accusation.add("ROPE");
					accusation.add("STUDY");
					Collections.sort(murderer);
					Collections.sort(accusation);
					List<String> list1 = new ArrayList<String>();
					List<String> list2 = new ArrayList<String>();
					list1.addAll(0, accusation);
					list2.addAll(0, murderer);
					if(list1.equals(list2)){
						game.setwinner(scarlet.returncharname());
						game.stopgame();
					}
					else { 
						scarlet.activefalse();
						//send message to all, saying scarlet guess incorrect
					}
				}
				//player either moved or made an suggestion, now they can either
				//make a suggestion or accusation
				if(again){
					again = false;
					//send info to player - stub
					String stat2=game.getgamestatus(scarlet.returncharname(),scarlet.getallvalidmoves(movelocations),
							scarlet.getplayerdata(), mustard.getplayerdata(), 
							green.getplayerdata(), white.getplayerdata(), peacock.getplayerdata(),
							plum.getplayerdata());
					System.out.println(stat2);
					//wait for player's response - stub (sit in loop, get response, parse it)
					//can only be suggestion or accusation
					String playerchoice2 = "suggestion";
					if(playerchoice2.equalsIgnoreCase("suggestion")){
						again = true;
						scarlet.suggestionfalse();
					}
					else if(playerchoice2.equalsIgnoreCase("accusation")) {
						//need to read accusation in from client
						ArrayList<String> accusation = new ArrayList<String>();
						accusation.add("MR GREEN");
						accusation.add("ROPE");
						accusation.add("STUDY");
						Collections.sort(murderer);
						Collections.sort(accusation);
						List<String> list1 = new ArrayList<String>();
						List<String> list2 = new ArrayList<String>();
						list1.addAll(0, accusation);
						list2.addAll(0, murderer);
						if(list1.equals(list2)){
							game.setwinner(scarlet.returncharname());
							game.stopgame();
						}
						else { 
							scarlet.activefalse();
							//send message to all, saying scarlet guess incorrect
						}
					}				
				}
				//player made suggestion they may now man an accusation
				if(again){
					//wait for player's response - stub (sit in loop, get response, parse it)
					//can only be accusation or end turn
					String playerchoice3 = "endturn";
					if(playerchoice3.equalsIgnoreCase("accusation")){
						//need to read accusation in from client
						ArrayList<String> accusation = new ArrayList<String>();
						accusation.add("MR GREEN");
						accusation.add("ROPE");
						accusation.add("STUDY");
						Collections.sort(murderer);
						Collections.sort(accusation);
						List<String> list1 = new ArrayList<String>();
						List<String> list2 = new ArrayList<String>();
						list1.addAll(0, accusation);
						list2.addAll(0, murderer);
						if(list1.equals(list2)){
							game.setwinner(scarlet.returncharname());
							game.stopgame();
						}
						else { 
							scarlet.activefalse();
							//send message to all, saying scarlet guess incorrect
						}
					}
				}
				//update current location to last position
				if(playerchoice.equalsIgnoreCase("move")){
				scarlet.setlastposition(newlocation);
				}
			}else{System.out.println("Im a loser");}
				break;
			case "mustard":
				mustard.setcanmove();
				break;
			case "green":
				green.setcanmove();
				break;
			case "white":
				white.setcanmove();
				break;		
			case "peacock":
				peacock.setcanmove();
				break;
			case "plum":
				plum.setcanmove();
				game.stopgame();
				break;
				
			}

			if (count < 5){
			count++;
			user=users[count];
			}
			else{ count = 0; user=users[count];}
		}
		System.out.println("leaving while loop");
	}
}
