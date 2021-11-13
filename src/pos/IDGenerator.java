package pos;

import java.util.Random;
import java.util.UUID;

public class IDGenerator {

    public static String GenerateUniqueID(){
    
        UUID id = UUID.randomUUID();
        return id.toString(); 
    }
    
    public static String GenerateUniqueAlphaNum(int n)
    {
    	String allowedChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    	String newString = "";
    	Random rand = new Random();
    	for (int i = 0 ; i < n ; i ++)
    	{
    		int randomNumGenerator = rand.nextInt(allowedChar.length());
    		newString += allowedChar.charAt(randomNumGenerator);
    	}
    	return newString;
    }
}
