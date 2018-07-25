import java.awt.Point;

class Asteroid 
{
	 Point position ;

     int width , height ;
	 int xOffset , yOffset ;
	 /*create a new Ship*/
	 
	Asteroid()
	{
		
	position = new Point (0,0);

	 width = 1 ;
	 height =1 ; 
	 xOffset=1 ;
	 yOffset=1;
	}
	
	/*get and set the position*/
	public Point getPosition() 
	{
		return position;
	}

	public void setPosition(Point position) 
	{
		this.position = position;
	}

	/*change the current position base on the current motion*/
	public void move()
	{
		 position.x+=xOffset; // Move horizontally.
		 position.y+=yOffset; // Move vertically.
	}
	
	/*change current motion base on the ship's current position */
  void asteroidbounce( int boundarywidth , int boundaryheight){
		

if(( position.x <= 0 & xOffset <0 )
				|| (position.x>= boundarywidth & xOffset >0)) 
		{
			xOffset *= -1 ; 
		}
if ((position.y <=0 & yOffset <0)
				||( position.y>=boundaryheight & yOffset>0))
		{
			yOffset *= -1; }  
		}
				
	/*return the position and motion*/ 
 public String toString()
 	 {
	 String xMotion = xOffset <0 ? "left " : "right" ;
	 String yMotion = yOffset <0 ? "up" : "down" ;
	 String positionStr = String.format("(%d, %d)" , position.x , position.y);
	 String motionStr = String.format("(%s, %s)" , xMotion , yMotion ) ;
	 return String.format("%10s%14s" ,  positionStr , motionStr);
 	 }

}
