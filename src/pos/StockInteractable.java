package pos;

import java.util.ArrayList;

public class StockInteractable implements IInteractable{

    @Override
    public void handleInput() {
        // TODO Auto-generated method stub
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

            Stock stock = new Stock(IDGenerator.GenerateUniqueID(),name,q);
            StockManager.instance.getStocks().add(stock);  

        }else if(choice == 2){
            
            System.out.println("select stock to delete:");
            displayAllStocks(true);
            int index = Integer.parseInt(Application.scanner.next());
            String removed = StockManager.instance.getStocks().get(index-1).getName();
            StockManager.instance.getStocks().remove(index-1);
            System.out.println("\'" + removed + "\' removed.");

        }else if (choice == 3){
            System.out.println("Current inventory:");
            displayAllStocks(false);
            System.out.println();
        }
    }

    @Override
    public String getTitle() {
        // TODO Auto-generated method stub
        return "Manage stock";
    }

    private void displayAllStocks(boolean showIndexing){

        ArrayList<Stock> ref = StockManager.instance.getStocks();
       
        for(int i = 0; i < ref.size(); i++){

            Stock temp = ref.get(i);

            if(showIndexing)
                System.out.println((i+1) + ") " + temp.getName() + " " + temp.getQuantity());
            else
                System.out.println(temp.getName() + " " + temp.getQuantity());
        }
    
    }

    
    
}
