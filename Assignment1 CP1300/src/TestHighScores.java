
import java.io.IOException;




public class TestHighScores {
 public static void main(String[]args) throws IOException {
	 Highscores scores = new Highscores();

	
	
	scores.save("scores.txt");//save the score in a text file
	 
	scores  = new Highscores() ;
	 scores.load("scores.txt");
	 
	  
	   if (scores.load("scores.txt")){
		    scores.addScore( "Harry" , 100);
			scores.addScore( "Ron", 10);
			scores.addScore( "HanSolo", 1000);
			scores.addScore("Yoda", 0);
			
		    scores.save("scores.txt");
		    scores.load("scores.txt");
		   
	   }
	   //get the top two scores
		 System.out.println("The top two scores are : " + scores.getTopScores(2));	  
	   if (!scores.load("scores.txt")){		    
	   return; 
 }}}
