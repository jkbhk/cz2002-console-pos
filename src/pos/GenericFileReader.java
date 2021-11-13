package pos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * 
 * Generic file reader that supports reading from csv files.
 *
 */
public class GenericFileReader {

    private static BufferedReader buffer;
    private static String filePathSuffix = "src/data/";
    private static String delimiter = ",";

    /**
     * Each row in the csv will be read as a string array, representing the columns of the row
     * The array list will be the collection of these rows.
     * @param filename
     * @return the retrieved strings from the specified file.
     */
    public static ArrayList<String[]> read(String filename){

        ArrayList<String[]> retrieved = new ArrayList<>();
        String path = filePathSuffix + filename;
        String line;

        try
        {
            buffer = new BufferedReader(new FileReader(path));
            
            while ((line = buffer.readLine()) != null)
            {
                String[] stream = line.split(delimiter);
                if (!stream[0].equals(""))
                	retrieved.add(stream);
                
            }

            if (retrieved.isEmpty()) {
            	return null;
            }
            
            return retrieved;
        }
        catch (Exception e)
        {
            System.out.println("Fetch Error ("+filename+")");
        }
        finally
        {
            if (buffer != null)
            {
                try
                {
                    buffer.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
       
        System.out.println("failed to read from " + filename);        
        return null;

    } 
}
