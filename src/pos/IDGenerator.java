package pos;

import java.util.Random;
import java.util.UUID;

/*
 * IDGenerator that generates unique IDs and alphanumeric IDs.
 */
public class IDGenerator {

	/**
	 * Random Generator that randomly creates unique strings for IDs.
	 * @return String randomly unique ID.
	 */
    public static String GenerateUniqueID(){
    
        UUID id = UUID.randomUUID();
        return id.toString(); 
    }
    
    /**
     * Random alphanumeric generator that randomly creates alphanumeric strings for IDs.
     * @param n Specified length of String.
     * @return String that is alphanumeric.
     */
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
