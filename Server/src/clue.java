import java.util.*; //needed for functions

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


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
	public ArrayList<String> convict = new ArrayList<String>();
	public boolean[][] board = new boolean[5][5];
	public boolean playgame = true;
	public String winner;
	public static String gamestatus;
	public static String clientdata;
	
	
	//decide who did the murder
	//pick 1 room, weapon and person from all possible values return string array
	//one = room, two = person, three = weapon
	void whodoneit() {
		int len = rooms.size()-1;
		int place = randomnumber(len);
		convict.add(rooms.get(place));
		rooms.remove(place);
		len = people.size()-1;
		place = randomnumber(len);
		convict.add(people.get(place));
		people.remove(place);
		len = weapons.size()-1;
		place = randomnumber(len);
		convict.add(weapons.get(place));
		weapons.remove(place);
	}
	
	//return who done it
	ArrayList<String> murder() {
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

	//checks to see what board locations are
	void printboard() {
		for(int i= 0;i < 5; i++){
			for(int j=0;j<5;j++){
				System.out.println(board[i][j]);
			}
		}
	}

	//sets characters positions on board
	void initialboard() {
		//character positions
		board[0][3] = true;
		board[4][1] = true;
		board[1][4] = true;
		board[1][0] = true;
		board[4][3] = true;
		board[3][0] = true;
		//set off limit spaces to true
		board[1][1] = true;
		board[1][3] = true;
		board[3][1] = true;
		board[3][3] = true;
	}

	//returns if game should be played
	boolean shouldplaygame() {
		return playgame;
	}

	//set playgame to false once someone has won or no one can move
	void stopgame() {
		playgame = false;
	}
	
	void setwinner (String win){
		winner = win;
	}
	
	ArrayList<String> validmovelocations (String position){
		ArrayList<String> possiblemoves = new ArrayList<String>();
		char part1 = position.charAt(0);
		char part2 = position.charAt(2);
		int row = Character.getNumericValue(part1);
		int column = Character.getNumericValue(part2);
		//check if row(x) is even or odd
		if ( (row & 1) == 0 ){
			if ( (column & 1) == 0){
				//Currently in a room
				if(row == 0){
					System.out.println("row 0");
					if(column == 0){
						//in Study
						String move1 = row + "," + (column+1);
						String move2 = (row+1) + "," + column;
						if(!ishallfull(move1)){	possiblemoves.add(move1);}
						if(!ishallfull(move2)){ possiblemoves.add(move2);}
						//Add kitchen
						String move3 ="4,4";
						possiblemoves.add(move3);
					}
					else if(column == 2){
						//in Hall
						String move1 = row + "," + (column-1);
						String move2 = (row+1) + "," + column;
						String move3 = row + "," + (column+1);
						if(!ishallfull(move1)){	possiblemoves.add(move1);}
						if(!ishallfull(move2)){ possiblemoves.add(move2);}
						if(!ishallfull(move3)){ possiblemoves.add(move3);}						
					}
					else{
						//in Lounge
						String move1 = row + "," + (column-1);
						String move2 = (row+1) + "," + column;
						if(!ishallfull(move1)){	possiblemoves.add(move1);}
						if(!ishallfull(move2)){ possiblemoves.add(move2);}
						//Add Conservatory
						String move3 ="4,0";
						possiblemoves.add(move3);
					}
				}
				else if (row == 2){ 
					if(column == 0){
						//in Library
						String move1 = (row-1) + "," + column;
						String move2 = (row+1) + "," + column;
						String move3 = row + "," + (column+1);
						if(!ishallfull(move1)){	possiblemoves.add(move1);}
						if(!ishallfull(move2)){ possiblemoves.add(move2);}
						if(!ishallfull(move3)){ possiblemoves.add(move3);}	
					}
					else if(column == 2){
						//in Billiard Room
						String move1 = (row-1) + "," + column;
						String move2 = (row+1) + "," + column;
						String move3 = row + "," + (column+1);
						String move4 = row + "," + (column-1);
						if(!ishallfull(move1)){	possiblemoves.add(move1);}
						if(!ishallfull(move2)){ possiblemoves.add(move2);}
						if(!ishallfull(move3)){ possiblemoves.add(move3);}	
						if(!ishallfull(move4)){ possiblemoves.add(move4);}
					}
					else{
						//in Dining Room
						String move1 = (row-1) + "," + column;
						String move2 = (row+1) + "," + column;
						String move3 = row + "," + (column-1);
						if(!ishallfull(move1)){	possiblemoves.add(move1);}
						if(!ishallfull(move2)){ possiblemoves.add(move2);}
						if(!ishallfull(move3)){ possiblemoves.add(move3);}	
					}
				}
				else{
					if(column == 0){
						//in Conservatory
						String move1 = (row-1) + "," + column;
						String move2 = row + "," + (column+1);
						if(!ishallfull(move1)){	possiblemoves.add(move1);}
						if(!ishallfull(move2)){ possiblemoves.add(move2);}
						//Move to lounge
						String move3 = "0,4";
						possiblemoves.add(move3);
						
					}
					else if(column == 2){
						//in Ballroom
						String move1 = row + "," + (column+1);
						String move2 = row + "," + (column-1);
						String move3 = (row-1) + "," + column;
						if(!ishallfull(move1)){	possiblemoves.add(move1);}
						if(!ishallfull(move2)){ possiblemoves.add(move2);}
						if(!ishallfull(move3)){ possiblemoves.add(move3);}	
					}
					else{
						//in Kitchen
						String move1 = (row-1) + "," + column;
						String move2 = row + "," + (column-1);
						if(!ishallfull(move1)){	possiblemoves.add(move1);}
						if(!ishallfull(move2)){ possiblemoves.add(move2);}
						//Move to Study
						String move3 = "0,0";
						possiblemoves.add(move3);
					}
				}
				
			}
			else {
				//currently in hallway, only valid options are to move into a room
				String move1 = row + "," + (column+1);
				String move2 = row + "," + (column-1);
				possiblemoves.add(move1);
				possiblemoves.add(move2);
			}
		}
		else { 
			if ( (column & 1) == 0){
				//Currently in a hallway can only move into a room
				String move1 = (row+1) + "," + column;
				String move2 = (row-1) + "," + column;
				possiblemoves.add(move1);
				possiblemoves.add(move2);
			}
			else {
				//this should never happen since all of these do not exist
				System.out.println("Error in Row Even, Column Odd should not be here");
			}
		}
		
		return possiblemoves;
	}

	Boolean ishallfull (String halllocation){
		char hall1 = halllocation.charAt(0);
		char hall2 = halllocation.charAt(2);
		int hallx = Character.getNumericValue(hall1);
		int hally = Character.getNumericValue(hall2);
		//check if hallway if occupied, if occupied return true
		if(board[hallx][hally]){
			return true;
		}
		else { return false;}
	}

	public static String getgamestatus() {
		return gamestatus;
	}
    
	String getclientdata(){
		return clientdata;
	}
	
	void setclientdata() {
		clientdata=null;
	}
	
	@SuppressWarnings("unchecked")	
    void setgamestatus (String charname, ArrayList<String> currentpersonmoves, Map<String,String> mscarlet, Map<String,String> cmustard,
    		Map<String,String> mgreen, Map<String,String> mwhite, 
    		Map<String,String> mpeacock, Map<String,String> pplum){ 	
    	/*	String json1 = JSONValue.toJSONString(mscarlet);
	    	String json2 = JSONValue.toJSONString(cmustard);
	    	String json3 = JSONValue.toJSONString(mgreen);
	    	String json4 = JSONValue.toJSONString(mwhite);
	    	String json5 = JSONValue.toJSONString(mpeacock);
	    	String json6 = JSONValue.toJSONString(pplum);
	    	String jsontext = json1+json2+json3+json4+json5+json6;
	    	System.out.println(jsontext);
    	*/
    	JSONObject state = new JSONObject();
    	JSONObject currentplayer = new JSONObject();
    	JSONArray array1 = new JSONArray();
    	JSONArray array2 = new JSONArray();
    	JSONObject json1 = new JSONObject();
    	JSONObject json2 = new JSONObject();
    	JSONObject json3 = new JSONObject();
    	JSONObject json4 = new JSONObject();
    	JSONObject json5 = new JSONObject();
    	JSONObject json6 = new JSONObject();
    	json1.putAll(mscarlet);
    	json2.putAll(cmustard);
    	json3.putAll(mwhite);
    	json4.putAll(mgreen);
    	json5.putAll(mpeacock);
    	json6.putAll(pplum);
    	array1.add(json1);
    	array1.add(json2);
    	array1.add(json3);
    	array1.add(json4);
    	array1.add(json5);
    	array1.add(json6);
    	currentplayer.put("player", charname);
    	array2.add(currentpersonmoves);
    	currentplayer.put("moves", array2);
    	state.put("winner", this.winner);
    	state.put("move_state",currentplayer);
    	state.put("players",array1);
    	//System.out.println(state);
    	gamestatus = JSONValue.toJSONString(state);
    	
    }

}
