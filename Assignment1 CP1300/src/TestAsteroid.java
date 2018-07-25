
public class TestAsteroid {
	public static void main (String[]args){
		//adjust the Asteroid position to (1,1)
		Asteroid Asteroid = new Asteroid() ;
		Asteroid.position.x = 1; Asteroid.position.y = 1 ; 

		 int Boundary_Width = 10 ; 
		 int Boundary_Height = 6 ;
		 int Left = 0 ;
		 int Right = Boundary_Width ;
		 int Top = 0 ;
		 int Bottom = Boundary_Height ;
		//move and bounce within the field
		System.out.println("(Test Asteroid) :" ) ;
		boolean hitTop = false , hitLeft=false , hitBottom = false , hitRight=false ;
		System.out.println(Asteroid);
		do {
			Asteroid.move();
			Asteroid.asteroidbounce(Boundary_Width , Boundary_Height) ;
			System.out.println(Asteroid); 
			if(Asteroid.getPosition().y==Bottom){
				hitBottom =true;        /*when Asteroid hits bottom*/
				System.out.println("Hit bottom!");
			}
			else if(Asteroid.getPosition().x==Right){
				hitRight = true;     /*when Asteroid hits right*/
				System.out.println("Hit right!");
			}
			else if(Asteroid.getPosition().y==Top){
				hitTop = true;   /*when Asteroid hits top*/
				System.out.println("Hit top!");
			}
			else if(Asteroid.getPosition().x==Left){
				hitLeft = true;  /*when Asteroid hits left*/
				System.out.println("Hit left");
			}
			
		/*if all sides are hit,the test will stop*/
		} while (!hitTop || !hitLeft || !hitBottom || !hitRight);
			System.out.println("All sides hit! Test it successful");
		}
		} 


