package pos;

public class DebugInteractable implements IInteractable{

    @Override
    public void handleInput() {
        // TODO Auto-generated method stub
        String[] temp = {"jelly"};

        String[] temp2 = {"beans"};
        GenericFileWriter.writeFile(temp,"stock_data.csv");
        GenericFileWriter.writeFile(temp2,"stock_data.csv");
    }

    @Override
    public String getTitle() {
        // TODO Auto-generated method stub
        return "Execute debug code";
    }
    
}
