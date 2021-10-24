package pos;

import java.util.UUID;

public class IDGenerator {

    public static String GenerateUniqueID(){
    
        UUID id = UUID.randomUUID();
        return id.toString(); 
    }
}
