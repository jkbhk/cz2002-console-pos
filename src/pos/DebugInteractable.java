package pos;

public class DebugInteractable implements IInteractable{

    @Override
    public void handleInput() {
        // TODO Auto-generated method stub
        GenericFileWriter.writeFile("stock_data.csv");
    }

    @Override
    public String getTitle() {
        // TODO Auto-generated method stub
        return "Execute debug code";
    }
    
}
