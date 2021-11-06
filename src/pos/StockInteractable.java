package pos;

import java.util.ArrayList;

public class StockInteractable implements IInteractable{

    @Override
    public void handleInput() {
        System.out.println("1) Add Stock");
        System.out.println("2) Remove Stock");
        System.out.println("3) Display all current stock");

        int choice = Integer.parseInt(Application.scanner.next());

        if(choice == 1){
            System.out.println("Enter stock name for new stock:");
            Application.scanner.nextLine();
            String name = Application.scanner.nextLine();
            System.out.println("Enter initial quantity for \'" + name + "\':");
            int q =Integer.parseInt(Application.scanner.next());

            StockManager.instance.createNewStock(IDGenerator.GenerateUniqueID(), name, q);

        }else if(choice == 2){
            
            System.out.println("Select stock to delete:");
            StockManager.instance.displayAllStocks(true);
            int index = Integer.parseInt(Application.scanner.next());
            Stock removed = StockManager.instance.getstock(index);
            if(removed != null){
                StockManager.instance.removeStock(index-1);
                System.out.println("\'" + removed + "\' removed.");
            }else{
                System.out.println("invalid choice");
            }

        }else if (choice == 3){
            System.out.println("Current inventory:");
            StockManager.instance.displayAllStocks(false);
            System.out.println();
        }
    }

    @Override
    public String getTitle() {
        // TODO Auto-generated method stub
        return "Manage stock";
    }

}
