package pos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.plaf.metal.MetalIconFactory.FileIcon16;

public class GenericFileReader {

    // usage:
    // 1) implement IReceivable
    // 2) call read(<instance of your class>, <file name>)
    // eg. GenericFileReader.read(this, "stock_data.csv")
    


    private static BufferedReader buffer;
    private static String filePathSuffix = "src/data/";
    private static String delimiter = ",";

    public static ArrayList<String[]> read(String filename){

        ArrayList<String[]> retrieved = new ArrayList<>();
        String path = filePathSuffix + filename;

        //ArrayList<String> fetched = new ArrayList<>();
        String line;

        try
        {
            buffer = new BufferedReader(new FileReader(path));
            
            while ((line = buffer.readLine()) != null)
            {
                String[] stream = line.split(delimiter);
                //r.receive(stream);
                retrieved.add(stream);
            }


            return retrieved;
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
       
        System.out.println("failed to read from " + filename);        
        return null;

    } 
}
