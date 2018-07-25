package Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import view.FieldPanel;
import view.GameFrame;
import Model.Constant;
import Model.Field;
import Model.HighScore;
import Model.MoveableObject;
import Model.Score;
//import Model.Constant;

/**
 * The four main objects - field, fieldPanel, gameFrame, and highScores
 * @author User
 *
 */
public class App implements ActionListener{
	
	static Field field = new Field();
	static GameFrame frame = new GameFrame("Asteroids");
	static JMenuBar menu = new JMenuBar();
	static Dimension d=new Dimension(300,400);
	static HighScore score = new HighScore();
	static MoveableObject mo = new MoveableObject();
	//create HighScore
	static HighScore highScore = new HighScore();
	// create Listscore
	public static TextArea txtListScore = new TextArea(0,20);
	// create labels
	static JPanel jp1 = new JPanel(new BorderLayout());
	static JLabel lbStatus = new JLabel("status: ");
	static JLabel lbScore = new JLabel("score: ");
	// create jcheckBoxMenuItem Easy
	 static JCheckBoxMenuItem chckbxmntmEasy = new JCheckBoxMenuItem("Easy");
	// create jcheckBoxMenuItem Normal
	 static JCheckBoxMenuItem chckbxmntmNormal = new JCheckBoxMenuItem("Normal");
	// create jcheckBoxMenuItem Hard
	 static JCheckBoxMenuItem chckbxmntmHard = new JCheckBoxMenuItem("Hard");
	 static FieldPanel centerPanel = new FieldPanel(field);
	 // check score saved
	 static boolean isSaved = false;
	public static void main(String[] args) throws FileNotFoundException {
		
		
		centerPanel.setPreferredSize(d);
		frame.add(centerPanel, BorderLayout.CENTER); // added to center
		frame.pack();
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		frame.setSize(300, 400);
		//add label, add label, jpanel to frame, and set visible
		txtListScore.setEditable(false);
		txtListScore.setFocusable(false);
		txtListScore.setPreferredSize(new Dimension(100, 400));
		jp1.setBackground(Color.lightGray);
		jp1.setLayout(new BorderLayout());
	
		jp1.add(lbStatus, BorderLayout.WEST);
		jp1.add(lbScore, BorderLayout.EAST);
	
		frame.add(centerPanel);
		frame.add(jp1, BorderLayout.SOUTH);
		frame.add(txtListScore, BorderLayout.EAST);
		frame.pack();
		jp1.setVisible(true);
		highScore.load(Constant.FILE_NAME);
		showListScore(txtListScore, highScore);
		// handle the animation logic
		final Timer timer = new Timer(40, new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.repaint();
				if (centerPanel.isActived()){
					lbScore.setText("Score: " + field.scoreValue);
					if (field.shipHitAsteroid()){
						centerPanel.setActive(false);
						lbStatus.setText("Game over");
						saveHighScore();
						isSaved = true;
					}
				}
			}
		});
		timer.start();
		
		// create menu Game, set hotkey and add to the menu
		JMenu fileGame = new JMenu("Game");
		fileGame.setMnemonic(KeyEvent.VK_G);
		menu.add(fileGame);
		// create menu Setting, set hotkey and add to the menu
		JMenu fileSetting = new JMenu("Settings");
		fileSetting.setMnemonic(KeyEvent.VK_S);
		menu.add(fileSetting);
		// create menu the checkbox for Easy, Normal and Hard mode, set hotkeys and add to the menu
		JMenu Difficulty= new JMenu("Difficulty");
		Difficulty.setMnemonic(KeyEvent.VK_D);
		fileSetting.add(Difficulty);
		Difficulty.add(chckbxmntmEasy);
		chckbxmntmEasy.addActionListener(null);
		Difficulty.add(chckbxmntmNormal);
		chckbxmntmNormal.setSelected(true);
		chckbxmntmNormal.addActionListener(null);
		Difficulty.add(chckbxmntmHard);
		chckbxmntmHard.addActionListener(null);
		fileSetting.addSeparator();
		// create menu ClearScore, set hotkey and add to the menu
		JMenuItem clearScores= new JMenuItem("Clear Scores",
	            KeyEvent.VK_C);	
		fileSetting.add(clearScores);
		// create menu Play, set hotkey and add to the menu
		JMenuItem Play= new JMenuItem("Play",
	             KeyEvent.VK_P);
		Play.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		fileGame.add(Play);
		// create menu Exit, set hotkey and add to the menu
		JMenuItem exitMenuItem= new JMenuItem("Exit",
	            KeyEvent.VK_E);
		fileGame.add(exitMenuItem);
		exitMenuItem.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	timer.stop();
		    	saveHighScore();
		    	try {
					if(highScore.scores!=null && highScore .scores.size()>0){
						highScore.save(Constant.FILE_NAME);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	System.exit(0);
		      }
		});
		
	
		//action when click Play button
		Play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (! centerPanel.isActived()) {
					field.setup(d);
					centerPanel.setActive(true);
					lbStatus.setText("Status : good luck pilot…");
					lbScore.setText("Score: 0");
					isSaved = false;
				}
				
			}
		});
		//action when click Easy button
		chckbxmntmEasy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Easy")) {
					chckbxmntmNormal.setSelected(false);
					chckbxmntmHard.setSelected(false);
					lbScore.setText("Score: 0");
					field.setDifficultyFactor(Field.EASY);
					field.setup(d);
					isSaved = false;
				}	
			}
		});
		//action when click Normal button
		chckbxmntmNormal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Normal")) {
					chckbxmntmEasy.setSelected(false);
					chckbxmntmHard.setSelected(false);
					lbScore.setText("Score: 0");
					field.setDifficultyFactor(Field.NORMAL);
					field.setup(d);
					isSaved = false;
				}	
			}
		});
		//action when click Hard button
		chckbxmntmHard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Hard")) {
					chckbxmntmNormal.setSelected(false);
					chckbxmntmEasy.setSelected(false);
					lbScore.setText("Score: 0");
					field.setDifficultyFactor(Field.HARD);
					field.setup(d);
					isSaved = false;
				}	
			}
		});
		//action when click clearScores button
		clearScores.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				highScore.scores = new ArrayList<Score>();
				showListScore(txtListScore, highScore);
				try {
					highScore.clearScore();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				isSaved = false;
			}
		});
	
		/**
		 * Add event for key up, down, left, and right for center Panel
		 */
		centerPanel.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub		
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				field.ship.stopMoving();
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub	
				int keyCode=e.getKeyCode();
				if (keyCode == KeyEvent.VK_LEFT){
					field.ship.push(-1, 0);	
					field.ship.position.x+=-1;
				}else if (keyCode == KeyEvent.VK_RIGHT){
					field.ship.push(1, 0);
					field.ship.position.x+=1;
				}else if (keyCode == KeyEvent.VK_DOWN){
					field.ship.push(0,1);
					field.ship.position.y+=1;
				}else if (keyCode == KeyEvent.VK_UP){
					field.ship.push(0,-1);
					field.ship.position.y+=-1;
				}
				if (keyCode == KeyEvent.VK_SPACE){
					field.addBullets();
				}
			}
		});
	// set size and set the frame to be visible
		frame.setJMenuBar(menu);
	    frame.setSize(500, 600);
	    frame.setVisible(true);
		
	    d=new Dimension(centerPanel.getWidth(),centerPanel.getHeight());
		centerPanel.setPreferredSize(d);
		field.setSize(d);
		field.update();
		
		frame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				timer.stop();
		    	saveHighScore();
		    	try {
					if(highScore.scores!=null && highScore .scores.size()>0){
						highScore.save(Constant.FILE_NAME);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		frame.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentResized(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				resize();
			}
			
			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
	} 
	/**
	 * function add score to Text Area
	 * @param txtScore 
	 */
	public static void showListScore(TextArea txtScore, HighScore scoreObj){
		txtScore.setText("");
		if(scoreObj.scores!=null && scoreObj.scores.size()>0){
			for (Score s : scoreObj.scores) {
				txtScore.append(s.getPlayer()+": "+ s.getScore()+"\n");
			}
		}
	}
	/**
	 * Save high score into highScore
	 */
	public static void saveHighScore(){
		if(field.scoreValue>0){
			if(!isSaved){
				String name = JOptionPane.showInputDialog("Player name? ");
				if(name!=null && !name.trim().equals("")){
					highScore.addScore(name, field.scoreValue);	
				}
			}
			showListScore(txtListScore,highScore);
			lbScore.setText("Score: 0");
		}
	}
	/**
	 * Resize 
	 */
	public static void resize(){
		int frameWidth = frame.getWidth();
		int frameHeight = frame.getHeight();
		int txtListScoreWidth = txtListScore.getWidth();				
		int heightBotom = jp1.getHeight(); 
		
		int newWidth = frameWidth - txtListScoreWidth;
		
		d =new Dimension(newWidth,frameHeight - (heightBotom + menu.getHeight()));
		// TODO Auto-generated method stub
		centerPanel.setPreferredSize(d);
		field.setSize(d);
		field.update();
	}

}
