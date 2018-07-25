import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Highscores {
ArrayList<Score> scores;
//creating a array list for scores
	
	public Highscores(){
		this.scores = new ArrayList<Score>();
	}

//add the new score into the scores list
//sorted with highest to lowest value 
	void addScore (String playername , int value ){
		Score score = new Score(playername,value);
		int i=0;
		while (i<scores.size()){
			if (value> scores.get(i).getValue()){
				break;
			}
			++i;
			//add the new scores
		}if (i<scores.size()){
			scores.add( i, score);
			}
			else{
			scores.add(score); 
		}
scores.add(score);}
	
	//load the data into the file
	 Boolean load (String data) throws IOException {
		 FileReader readFile = null;
			BufferedReader reader = null;
				try {
				readFile = new FileReader ("scores.txt");
				reader = new BufferedReader(readFile);
				return true;
				}catch (Exception e)
				{
					return false;
				}
				finally { 
					if(reader != null);
					reader.close();
				}
		}
			
		//save the data to the file using print writer
	 Boolean save (String data) throws FileNotFoundException{
			String filename = "scores.txt";
			try {
				
			
			PrintWriter outputStream= new PrintWriter(filename);
			outputStream.println(scores);
			outputStream.flush();// clear the file to load new scores
			outputStream.close();
			
			}catch (FileNotFoundException e){
				
			}
			return false;
		}
		
	 //get the TopScores
	 public ArrayList<Score> getTopScores(int totalScores) {
		 ArrayList<Score> topScores = new ArrayList<Score>();
		 for (int i=0 ; i<this.scores.size() & i< totalScores; i++){
			 topScores.add(this.scores.get(i));
		 }
		return topScores;
	 }
}