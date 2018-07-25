package Model;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class HighScore {
	public ArrayList<Score> scores;
	public HighScore(){
		scores=new ArrayList<Score>();
	}
	public void addScore(String a, int b){
		Score score=new Score();
		score.set(a,b);
		scores.add(score);
		Collections.sort(scores);
	}
	public boolean save(String fileName) throws IOException{
		boolean status=false;
		File file=new File(fileName);
		if(!file.exists()){
			clearScore();
		}
		PrintWriter printWriter=new PrintWriter(file);
		String string="";
		for (int i = 0; i < scores.size(); i++) {
			string+=String.format("%s:%d  ",scores.get(i).player.trim(),scores.get(i).score);
		}
		printWriter.print(string);
		printWriter.close();
		if (file!=null) {
			status=true;
		}
		return status;
	}
	public boolean load(String fileName) throws FileNotFoundException{
		File file=new File(fileName);
		if(file.exists()){
			Scanner scanner=new Scanner(file);
			if(scanner.hasNextLine()){
				String line=scanner.nextLine();
				String[] list=line.trim().split("  ");
				for (int i=0;i<list.length;i++) {
					String[] data=list[i].split(":");
					Score element=new Score();
					element.set(data[0], Integer.parseInt(data[1]));
					scores.add(element);
				}
			}
			scanner.close();
		}
		boolean bool=false;
		if (scores!=null) { bool=true; }
		return bool;
	}
	public ArrayList<Score> getTopScores(int a){
		ArrayList<Score> reqInput=new ArrayList<Score>();
		if (scores.size()<a){
			reqInput=scores;
		}
		else{ for (int i = 0; i < a; i++) {
			Score scor=new Score();
			scor.set(scores.get(i).player,scores.get(i).score);
			reqInput.add(scor);
		}}
		return reqInput;
	}
	public void clearScore()throws IOException{
		File file=new File(Constant.FILE_NAME);
		if(file.exists()){
			file.delete();
		}
		file=new File(Constant.FILE_NAME);
		file.createNewFile();
	}
}
