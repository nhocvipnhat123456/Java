package view;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import Controller.App;
import Model.Score;

public class GameFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel statusLabel, scoreLabel;
	final JTextArea textArea = new JTextArea();
	FieldPanel mainField;
	App app = new App();
	
	public GameFrame(String title){
		super( title );  
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void updateStatus(String message) {
		statusLabel.setText("Status: " + message);
	}
	public void updateScore(int value) {
		updateScoreLabel(value);
	}
	private void updateScoreLabel(int scoreValue) {
		scoreLabel.setText("Score: " + scoreValue);
	}
	public void updateHighscores(List<Score> topScores) {
		app.txtListScore.setText("");
		
		for (Score score : topScores) {
			app.txtListScore.append(score.toString() + "\n");
		}
	}
}
