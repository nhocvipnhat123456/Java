package Model;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;


public class MoveableObject {
	public Point position=new Point();
	public int width;
	public int height;
	public int xOffset;
	public int yOffset;
	String hDirection;
	String vDirection;
	
	static final int max_speed=10;
	public void draw(Graphics a){
		a.fillRect(position.x, position.y, width, height);
	}
	public void push(int a,int b){
		xOffset+=a;
		yOffset+=b;
	}
	public void limitMaximumSpeed(){
		if(xOffset>max_speed) xOffset=max_speed;
		if(yOffset>max_speed) yOffset=max_speed;
		if(xOffset<-max_speed) xOffset=-max_speed;
		if(yOffset<-max_speed) yOffset=-max_speed;
	}
	public void stopMoving(){
		xOffset=0;
		yOffset=0;
	}
	public boolean contains(Point a){
		return ((a.x>=position.x && a.x<=(position.x+width)) && 
				(a.y>=position.y && a.y<=(position.y+height)));
	}
	public void move(){		
		position.x+=xOffset;
		position.y+=yOffset;
	}
	public void bounce(Dimension size){
		if (position.x >= size.width & xOffset > 0) {
			xOffset *= -1;
		}
		if (position.x <= 0 & xOffset < 0) {
			xOffset *= -1;
		}
		if (position.y >= size.height & yOffset > 0) {
			yOffset *= -1;
		}
		if (position.y <= 0 & yOffset < 0) {
			yOffset *= -1;
		}
	}
	
}
