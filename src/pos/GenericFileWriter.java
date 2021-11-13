package pos;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
/**
 * 
 * A generic file writer that writes to csv file.
 *
 */
public class GenericFileWriter {
    
    private static String filePathSuffix = "src/data/";
    private static final char DELIMITTER = ',';

    /**
     * Writes to the specified csv file.
     * 
     * @param items Outer array will be written to each row of the csv file, inner array will be written to each collumn of the same row referenced by the outer array.
     */
    public static void writeFile(String[][] items,String filename){
        try (PrintWriter writer = new PrintWriter(filePathSuffix + filename)) {

            StringBuilder sb = new StringBuilder();

            for(String[] item : items){

                for(int i = 0; i < item.length; i++){
                    sb.append(item[i]);
                    if(i != item.length-1)
                        sb.append(DELIMITTER);
                }

                sb.append('\n');
            }

            
            writer.write(sb.toString());
      
            System.out.println("write complete!");
      
          } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
          }
    }


/**
 * Writes to the specified csv file.
 * 
 * @param properties Each item in the array will be written to a new row.
 */
    public static void writeFile(String[] properties,String filename){
        try (PrintWriter writer = new PrintWriter(filePathSuffix + filename)) {

            StringBuilder sb = new StringBuilder();
            
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
