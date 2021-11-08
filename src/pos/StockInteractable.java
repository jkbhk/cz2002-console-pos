package pos;

import java.util.ArrayList;

public class StockInteractable implements IInteractable{

	private InteractableComponent stockAssistant = new InteractableComponent("Back", true);
	
	public StockInteractable() {
		stockAssistant.addInteractable(new 	IInteractable() {

			@Override
			public void handleInput() {
				
				System.out.println("Enter stock name for new stock:");
				String name = Application.scanner.nextLine();
				System.out.println("Enter initial quantity for \'" + name + "\':");
				int q =Integer.parseInt(Application.scanner.nextLine());

				StockManager.instance.createNewStock(IDGenerator.GenerateUniqueID(), name, q);
				
			}

			@Override
			public String getTitle() {
				// TODO Auto-generated method stub
				return "Add Stock";
			}
			
		});
		
		stockAssistant.addInteractable(new IInteractable() {

			@Override
			public void handleInput() {
				
				if(StockManager.instance.getStockCount() <= 0) {
					System.out.println("stock list is empty!");
					return;
				}
				
				System.out.println("Select stock to delete:");
	            StockManager.instance.displayAllStocks(true);
	            int index = Integer.parseInt(Application.scanner.nextLine());
	            Stock removed = StockManager.instance.getstock(index);
	            if(removed != null){
	                StockManager.instance.removeStock(index-1);
	                System.out.println("stock removed.");
	            }else{
	                System.out.println("invalid choice");
	            }

			}

			@Override
			public String getTitle() {
				// TODO Auto-generated method stub
				return "Remove Stock";
			}
			
		});
		
		stockAssistant.addInteractable(new IInteractable() {

			@Override
			public void handleInput() {
				
				if(StockManager.instance.getStockCount() <= 0) {
					System.out.println("stock list is empty!");
					return;
				}
				// TODO Auto-generated method stub
				 System.out.println("Current inventory:");
				 StockManager.instance.displayAllStocks(false);
				 System.out.println();
			}

			@Override
			public String getTitle() {
				// TODO Auto-generated method stub
				return "Display Stock";
			}
			
		});
	}
	
    @Override
    public void handleInput() {
        stockAssistant.start();
    }

    @Override
    public String getTitle() {
        // TODO Auto-generated method stub
        return "Manage stock";
    }

}
