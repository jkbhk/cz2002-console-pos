package pos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GenericFileReader {
    
    private static BufferedReader buffer;
    private static String filePathSuffix = "../data/";
    private static String delimiter = ",";

    public static void read(IReceivable r, String filename){
        String path = filePathSuffix + filename;

        //ArrayList<String> fetched = new ArrayList<>();
        String line;

        try
        {
            buffer = new BufferedReader(new FileReader(path));
            
            while ((line = buffer.readLine()) != null)
            {
                String[] stream = line.split(delimiter);
                r.receive(stream);
         
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

    } 
}
