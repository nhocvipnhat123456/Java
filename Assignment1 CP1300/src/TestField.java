public class TestField 
{

	

	public static void main(String []args  )
	{
		Field field= new Field();		
		field.setup();
		
		
			
		System.out.println("(TestField):");
		for (int i=0; i<10 ; i++){
			System.out.println(field);
			field.update();
		}
	}
}
