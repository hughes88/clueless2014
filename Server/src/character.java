import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class character {

	public int x;
	public int y;
	public String[] mycards;
	public boolean suggestion = true;
	public ArrayList<String> moves = new ArrayList<String>();
	public boolean active = true;
	public boolean canmove = true;
	public String name;
	public String cluecharacter;
	public int x2;
	public int y2;
	
	
	void setcluecharacter(String cluechar){
		cluecharacter = cluechar;
	}
	void setname (String playername) {
		name = playername;
	}
	
	//one time call to set cards
	void setcards (String[] cards) {
		mycards = cards;
	}
	void setcanmove () {
		canmove = true;
	}
	void cantmove() {
		canmove = false;
	}

	//print cards will update to return all cards
	void showcards() {
    	for(String card: mycards){
    		System.out.println("My card is " + card);
    		}
	}

	//updates current postion
	void initialsetposition (int row,int column){
		x = row;
		y = column;
		suggestion = true;
	}
	void setposition(String location){
		char part1 = location.charAt(0);
		char part2 = location.charAt(2);
		int row = Character.getNumericValue(part1);
		int column = Character.getNumericValue(part2);
		x = row;
		y = column;
		suggestion = true;
	}
	
	void setlastposition(String location){
		char part1 = location.charAt(0);
		char part2 = location.charAt(2);
		int row = Character.getNumericValue(part1);
		int column = Character.getNumericValue(part2);
		x2 = row;
		y2 = column;
		suggestion = true;
	}
	
	void initialsetlastposition (int row,int column){
		x2 = row;
		y2 = column;
	}

	//return location
	String returncurrentposition (){
		String pos = Integer.toString(x)+","+Integer.toString(y);
		return pos;
	}
	
	String returnlastposition() {
		String pos = Integer.toString(x2)+","+Integer.toString(y2);
		return pos;
	}
	boolean returnactive() {
		return active;
	}

	void activefalse () {
		active = false;
	}

	void suggestionfalse () {
		active = false;
	}

	String returncharname() {
		return cluecharacter;
	}
	Map<String,String> getplayerdata () {
		Map<String,String> currentvalues = new HashMap<String,String>();
		currentvalues.put("name",name);
		currentvalues.put("character",cluecharacter);
		String play;
		if(active){ play = "true";}
		else{ play = "false";}
		currentvalues.put("active",play);
		StringBuilder cards = new StringBuilder();
		int hold = 0;
		for(String s : mycards){
			if(hold == 0){cards.append(s); hold++;}
			else{cards.append(",").append(s);}
		}
		currentvalues.put("cards", cards.toString());
		currentvalues.put("position",this.returncurrentposition());
		return currentvalues;
	}

	@SuppressWarnings("unchecked")
	ArrayList<String> getallvalidmoves(ArrayList<String> spots) {
		ArrayList<String> holder = new ArrayList<String> ();

    	String currentpos = this.returncurrentposition();
    	String lastpos = this.returnlastposition();
    	boolean guess = true;
    	if(currentpos.equalsIgnoreCase(lastpos)){
    		guess = false;
    	}
    	int length = spots.size();
    	if(length > 0 && this.canmove){
    		String possiblemoves = spots.toString();
    		holder.add(possiblemoves);
    	}
    	if(this.active && this.suggestion && guess){
    		String suggest = "suggestion";
    		holder.add(suggest);
    	}
    	if(this.active){
    		String accusation = "accusation";
    		holder.add(accusation);
    	}
    	JSONArray arraymoves = new JSONArray();
    	JSONObject somemoves = new JSONObject();
    	somemoves.put("player", this.cluecharacter);
    	arraymoves.add(holder);
    	somemoves.put("moves", arraymoves);
    	//String MOVES = JSONValue.toJSONString(somemoves);
		//String MOVES = somemoves.toString();
    	
    	return holder;
	}
}
