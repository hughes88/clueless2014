public class character {

	public int x;
	public int y;
	public String[] mycards;
	public boolean suggestion = true;
	
	//one time call to set cards
	void setcards (String[] cards) {
		mycards = cards;
	}

	//print cards will update to return all cards
	void showcards() {
    	for(String card: mycards){
    		System.out.println("My card is " + card);
    		}
	}

	//updates current postion
	void setposition (int row,int column){
		x = row;
		y = column;
		suggestion = true;
	}

	//return row location
	int returnx () {
		return x;
	}
	
	//return column location
	int returny () {
		return y;
	}

	void suggestionfalse () {
		suggestion = false;
	}

}
