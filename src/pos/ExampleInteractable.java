package pos;

public class ExampleInteractable implements IInteractable {

    @Override
    public void handleInput() {
    
        // print whatever sub menu u want
    	
    	for (Order o : OrderManager.instance.getOrderList()) {
    		System.out.println("Order: " + o.getTotalPrice());
    	}
    	
        System.out.println("Cook what?");
        System.out.println("1) chicken");
        System.out.println("2) cabbage");
        System.out.println("3) tuna");
    
        //request whatever u need
        // use the static scanner object from the Application class like this 
        int choice = Application.scanner.nextInt();
     
        // do anything to complete ur task
        ExampleManager.instance.exampleMethod(choice);
        System.out.println("COOKING COMPLETE!");
        
    }

    @Override
    public String getTitle() {
        // the general use case title that will be displayed in the MAIN menu
        return "Cook food (Just an example)";
    }
    
}
