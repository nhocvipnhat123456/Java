package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import Model.Field;
import Model.MoveableObject;



public class FieldPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean activated;
	Field field;
	public FieldPanel(Field currentField){
		setBackground(Color.black);
		setFocusable(true);
		field=currentField;
	 }
	 public boolean isActived(){
		 return activated;
	 }
	 public void setActive(boolean activated){
		 this.activated=activated;
	 }
	 public void paintComponent(Graphics g){
		 super.paintComponent(g);
		 if (activated){
			 field.update();
			 g.setColor(Color.GREEN);
			 field.ship.draw(g);
			 g.setColor(Color.WHITE);
			 for(MoveableObject as:field.asteroids){
				 as.draw(g);
			 }
			 g.setColor(Color.RED);			 			 
			 for(MoveableObject bullet:field.bullets){				 
				 bullet.draw(g);
			 }
			field.bulletHitAsteroid();
			field.shipHitAsteroid();	
		 }
	}
	 
}
