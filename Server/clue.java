import java.util.*; //needed for functions

public class clue {
	//create arraylist for the rooms,peoples and weapons

	private String[] room = {"BALLROOM", "KITCHEN", "BILLARD ROOM", "CONSERVATORY",
			"DINING ROOM", "LIBRARY", "LOUNGE", "HALL", "STUDY"};
	public ArrayList<String> rooms = new ArrayList<String>(Arrays.asList(room));
	private String[] persons ={"Colonel Mustard","Miss Scarlet", "Professor Plum",
			"Mr. Green", "Mrs. White", "Mrs. Peacock"};
	public ArrayList<String> people = new ArrayList<String> (Arrays.asList(persons));
	private String[] weapon = {"Rope", "Lead Pipe", "Knife", "Wrench", "Candlestick", "Revolver"};
	public ArrayList<String> weapons = new ArrayList<String> (Arrays.asList(weapon));
	public ArrayList<String> restofcards = new ArrayList<String> ();
	
	//decide who did the murder
	//pick 1 room, weapon and person from all possible values return string array
	//one = room, two = person, three = weapon
	String [] whodoneit() {
		int len = rooms.size()-1;
		int place = randomnumber(len);
		String one = rooms.get(place);
		rooms.remove(place);
		len = people.size()-1;
		place = randomnumber(len);
		String two = people.get(place);
		people.remove(place);
		len = weapons.size()-1;
		place = randomnumber(len);
		String three = weapons.get(place);
		weapons.remove(place);
		String[] rpw = {one, two, three};
		return rpw;
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

	

}
