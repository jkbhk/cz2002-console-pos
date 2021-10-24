package pos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GenericFileReader {
    
    private static BufferedReader buffer;
    private static String filePathSuffix = "../data/";

    public static ArrayList<String> read(String filename){
        String path = filePathSuffix + filename;

        ArrayList<String> fetched = new ArrayList<>();
        String line;

        try
        {
            buffer = new BufferedReader(new FileReader(path));
            
            while ((line = buffer.readLine()) != null)
            {
                fetched.add(line);
            }
        }
        catch (Exception e)
        {
            //e.printStackTrace();
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
       

        System.out.println("data fetch from "+filename+" complete.");        
        return fetched;

    } 
}
