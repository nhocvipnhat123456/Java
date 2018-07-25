
public class Score {
		private String playername;
		int value ;

	   
	   //creating the score class//
	   public Score ( String playername, int value)
	   {
		   this.playername = playername;  
		   this.setValue(value);       
	   }
	      public int getValue() 
	   {
			return value;
	   }
	 	public void setValue(int value) 
	 	{
	 		this.value= value;
	 	}
	   public String toString()
	   {
		   return String.format(  playername+   ":" + getValue());
	   }
	}

