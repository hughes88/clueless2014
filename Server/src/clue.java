import java.util.*; //needed for functions

public class clue {
	//create arraylist for the rooms,peoples and weapons

	private String[] room = {"BALLROOM", "KITCHEN", "BILLARD ROOM", "CONSERVATORY",
			"DINING ROOM", "LIBRARY", "LOUNGE", "HALL", "STUDY"};
	public ArrayList<String> rooms = new ArrayList<String>(Arrays.asList(room));
	private String[] persons ={"COLONEL MUSTARD","MISS SCARLET", "PROFESSOR PLUM",
			"MR GREEN", "MRS WHITE", "MRS PEACOCK"};
	public ArrayList<String> people = new ArrayList<String> (Arrays.asList(persons));
	private String[] weapon = {"ROPE", "LEAD PIPE", "KNIFE", "WRENCH", "CANDLESTICK", "REVOLVER"};
	public ArrayList<String> weapons = new ArrayList<String> (Arrays.asList(weapon));
	public ArrayList<String> restofcards = new ArrayList<String> ();
	public String[] convict ={"room","person","weapon"};
	public boolean[][] board = new boolean[5][5];
	
	//decide who did the murder
	//pick 1 room, weapon and person from all possible values return string array
	//one = room, two = person, three = weapon
	void whodoneit() {
		int len = rooms.size()-1;
		int place = randomnumber(len);
		convict[0]= rooms.get(place);
		rooms.remove(place);
		len = people.size()-1;
		place = randomnumber(len);
		convict[1] = people.get(place);
		people.remove(place);
		len = weapons.size()-1;
		place = randomnumber(len);
		convict[2] = weapons.get(place);
		weapons.remove(place);
	}
	
	//return who done it
	String[] murder() {
		return convict;
	}
	//generate random number for picking who died, where they died and what weapon killed
	int randomnumber (int ran) {
		Random rand = new Random();
		int number = rand.nextInt((ran) + 1);
		return number;
	}

	//combine the "cards" and shuffle them to randomize it
	void shufflecards () {
		restofcards.addAll(weapons);
		restofcards.addAll(rooms);
		restofcards.addAll(people);
		Collections.shuffle(restofcards);
	}

	String [] dealcards() {
		String one,two,three;
		one = restofcards.get(0);
		restofcards.remove(0);
		two = restofcards.get(0);
		restofcards.remove(0);
		three = restofcards.get(0);
		restofcards.remove(0);
		String [] deal = {one, two, three};
		return deal;
	}

	void printboard() {
		for(int i= 0;i < 5; i++){
			for(int j=0;j<5;j++){
				System.out.println(board[i][j]);
			}
		}
	}

	void initialboard() {
		board[0][3] = true;
		board[4][1] = true;
		board[1][4] = true;
		board[1][0] = true;
		board[4][3] = true;
		board[3][0] = true;
	}

}
