package Model;

public class Score implements Comparable<Score> {
	int score;
	String player;
	Score(){
		score=0;
		player=null;
	}
	public String toString(){
		return String.format("%s:%d",player,score);
	}
	public void set(String a,int b){
		this.player=a;
		this.score= b;
	}
	public int getScore(){
		return score;
	}
	public String getPlayer(){
		return player;
	}
	@Override
	public int compareTo(Score a) {
		int compare=a.getScore();
		return compare-this.score;
	}
}
