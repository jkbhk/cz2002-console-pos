package pos;

import java.util.ArrayList;

public class MenuInteractable implements IInteractable{

    InteractableComponent menuAssistant = new InteractableComponent("Back", true);

    public MenuInteractable(){
        menuAssistant.addInteractable(new IInteractable() {

            @Override
            public void handleInput() {
                System.out.println("Enter name for new menu item:");
                Application.scanner.nextLine();
                String name = Application.scanner.nextLine();
                System.out.println("Enter description for \'"+name+"\'");
                String description = Application.scanner.nextLine();
                System.out.println("Enter price for \'"+name+"\'");
                String sprice = Application.scanner.nextLine();               
                Double price = Double.parseDouble(sprice);
                System.out.println("Which page will this be on?");
                String tag = Application.scanner.nextLine();

                boolean done = false;
                ArrayList<String> references = new ArrayList<String>();

                while(!done){
                    System.out.println("Enter stocks that are included in this menu item:");
                    StockManager.instance.displayAllStocks(true);
                    int choice = Integer.parseInt(Application.scanner.nextLine());
                    Stock s = StockManager.instance.getstock(choice-1);
                    
                    if(s!=null){
                        references.add(s.getStockID());
                    }else{
                        System.out.println("invalid choice.");
                    }
                    
                    System.out.println("Add another?");
                    System.out.println("(y) yes ");
                    System.out.println("(n) no");
                    String again = Application.scanner.nextLine();
                    
                    if(!again.equals("y"))
                        done = true;   
                            
                }

                MenuManager.instance.createNewMenuItem(IDGenerator.GenerateUniqueID(), name, description, price, tag, references);
            }

            @Override
            public String getTitle() {
                return "Create Menu Item";
            }
            
        }); 
        
        menuAssistant.addInteractable(new IInteractable() {

            @Override
            public void handleInput() {
                
            }

            @Override
            public String getTitle() {
                return "Remove Menu Item";
            }
            
        });

        menuAssistant.addInteractable(new IInteractable() {

            @Override
            public void handleInput() {
               MenuManager.instance.displayMenu(); 
            }

            @Override
            public String getTitle() {
                return "Show Current Menu Items";
            }
            
        });


    }

    @Override
    public void handleInput() {

        menuAssistant.start();        

    }

    @Override
    public String getTitle() {
        return "Manage Menu Items";
    }
    
}
