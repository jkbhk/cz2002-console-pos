package pos;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GenericFileWriter {
    
    
    private static String filePathSuffix = "../data/";
    private static final char DELIMITTER = ',';

    public static void writeFile(String[][] items,String filename){
        try (PrintWriter writer = new PrintWriter(filePathSuffix + filename)) {

            StringBuilder sb = new StringBuilder();

            for(String[] item : items){

                sb.append(IDGenerator.GenerateUniqueID());

                for(String p : item){
                    sb.append(DELIMITTER);
                    sb.append(p);
                }

                sb.append('\n');
            }

            
            /*sb.append("Id");
            sb.append(',');
            sb.append("Name");
            sb.append('\n');
      
            sb.append(IDGenerator.GenerateUniqueID());
            sb.append(',');
            sb.append("Chicken rice");
            sb.append('\n');
      
            
            sb.append(IDGenerator.GenerateUniqueID());
            sb.append(',');
            for(int i = 0; i<properties.length;i++){
                
                sb.append(properties[i]);
                sb.append(',');
            }
            sb.append('\n');
            */


            writer.write(sb.toString());
      
            System.out.println("write complete!");
      
          } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
          }
    }



    public static void writeFile(String[] properties,String filename){
        try (PrintWriter writer = new PrintWriter(filePathSuffix + filename)) {

            StringBuilder sb = new StringBuilder();
            /*sb.append("Id");
            sb.append(',');
            sb.append("Name");
            sb.append('\n');
      
            sb.append(IDGenerator.GenerateUniqueID());
            sb.append(',');
            sb.append("Chicken rice");
            sb.append('\n');
      */
            
            sb.append(IDGenerator.GenerateUniqueID());
            sb.append(',');
            for(int i = 0; i<properties.length;i++){
                
                sb.append(properties[i]);
                sb.append(',');
            }
            sb.append('\n');
            
            writer.write(sb.toString());
      
            System.out.println("write complete!");
      
          } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
          }
    }

}
