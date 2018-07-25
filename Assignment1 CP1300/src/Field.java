import java.util.Random;
class Field 
{
	int width;   
	int height;	  
    Ship ship ;   
    Ship[] asteroids ; 

	public Field() 
	{
		width=100;	
		height=50;	
		ship  = new Ship();   
		asteroids = new Ship[10];   //limited number of asteroids setting
		for (int i=0 ; i<asteroids.length ; i++ )    
			asteroids[i]= new Ship();
	 }
	
/* Setup the ship and asteroid position within the field*/
	public void setup() 
		{
		this.ship.position.x = this.width / 2 ;         
		this.ship.position.y = this.height / 2;         
		/*the random seed to 0 for asteroid position by random*/
        Random rand = new Random();
		rand.setSeed(0);
		
		for (int i = 0; i <asteroids.length ; i++){
    	this.asteroids[i].position.x = rand.nextInt(this.width);     
		this.asteroids[i].position.y = rand.nextInt(this.height);
		}
	
		
	}
		
public void update() 	
{ 
		
		ship.move();
		ship.shipbounce(width, height);
	

	
	for (Ship asteroid : asteroids ){  
		asteroid.move();
		asteroid.shipbounce(width, height);
	}

}

//return the ship and asteroid postion and motion with toString method//

 public String toString()
 {
	 StringBuilder builder = new StringBuilder();
	  builder.append((String.format("%s:\t%s\n" , " Ship " , ship))) ;
	  int id=1 ;
	  for (Ship asteriod : asteroids ){
		  builder.append((String.format( "Asteroids[%d]:\t%s\n " ,id++ , asteriod)));
	  }
	  return builder.toString();
 	}
}
