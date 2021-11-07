package pos;

public class DebugInteractable implements IInteractable{

    @Override
    public void handleInput() {
        
    	PaymentManager.instance.requestPayment(100);
    	
    }

    @Override
    public String getTitle() {
        // TODO Auto-generated method stub
        return "Execute debug code";
    }
    
}
