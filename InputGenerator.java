import java.io.IOException;
import java.io.PrintWriter;

public class InputGenerator {
	public static void main(String args[]){
		try{
			System.out.println("Writing to Hex Input.txt");
	    	PrintWriter writer = new PrintWriter("Hex Input.txt", "UTF-8");
	    
	    	for(int i=1; i<234; i++){
	    	writer.println(i + " " + (int)Math.ceil(Math.random() * 9));
	    	}
	    	writer.close();
	    	
	    	System.out.println("Finished!");
		} catch (IOException e) {
			// do something
		}
	}
}