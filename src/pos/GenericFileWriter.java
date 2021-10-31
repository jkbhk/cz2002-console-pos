package pos;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class GenericFileWriter {
    
    
    private static String filePathSuffix = "../data/";

    public static void writeFile(String filename){
        try (PrintWriter writer = new PrintWriter(filePathSuffix + filename)) {

            StringBuilder sb = new StringBuilder();
            sb.append("Id");
            sb.append(',');
            sb.append("Name");
            sb.append('\n');
      
            sb.append("1");
            sb.append(',');
            sb.append("Chicken rice");
            sb.append('\n');
      
            writer.write(sb.toString());
      
            System.out.println("write complete!");
      
          } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
          }
    }

}
