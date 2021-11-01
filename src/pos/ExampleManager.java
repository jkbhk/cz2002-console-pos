package pos;

public class ExampleManager {
   
    public static ExampleManager instance;

    public ExampleManager(){
        instance = this;
    }
    

    public int exampleMethod(int r){
        return 1;
    }

}
