package score;

public class Score {

	private int score;
	private String date;
	
	
	public Score(String date, int score) {
		this.date = date;
		this.score = score;
	}
	
	public int getScore(){
		return this.score;
	}
	
	public String getDate(){
		return this.date;
	}
	
	public void setScore(int score){
		this.score = score;
	}

	public void setDate(String date){
		this.date = date;
	}
	
	public String toString(){
		return "" + score + " " + date;
	}
}
