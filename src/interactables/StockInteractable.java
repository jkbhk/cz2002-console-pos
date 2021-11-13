package interactables;

import entities.Stock;
import managers.StockManager;
import pos.Application;
import pos.IDGenerator;
/**
 * 
 * Facade object that facilitates the creation, deletion, and checking of stocks.
 *
 */
public class StockInteractable implements IInteractable {

	private InteractableComponent stockAssistant = new InteractableComponent("Back", true);

	public StockInteractable() {
		stockAssistant.addInteractable(new IInteractable() {

			@Override
			public void handleInput() {

				System.out.println("Enter stock name for new stock:");
				String name = Application.scanner.nextLine();

				StockManager.instance.createNewStock(IDGenerator.GenerateUniqueID(), name);
				System.out.println("Stock added\n");
			}

			@Override
			public String getTitle() {
				return "Add Stock";
			}

		});

		stockAssistant.addInteractable(new IInteractable() {

			@Override
			public void handleInput() {

				if (StockManager.instance.getStockCount() <= 0) {
					System.out.println("stock list is empty!\n");
					return;
				}

				System.out.println("Select stock to delete:");
				StockManager.instance.displayAllStocks(true);
				int index = Integer.parseInt(Application.scanner.nextLine());
				Stock removed = StockManager.instance.getstock(index - 1);
				if (removed != null) {
					StockManager.instance.removeStock(index - 1);
					System.out.println("stock removed\n");
				} else {
					System.out.println("invalid choice\n");
				}

			}

			@Override
			public String getTitle() {
				return "Remove Stock";
			}

		});

		stockAssistant.addInteractable(new IInteractable() {

			@Override
			public void handleInput() {

				if (StockManager.instance.getStockCount() <= 0) {
					System.out.println("stock list is empty!\n");
					return;
				}
				System.out.println("Current inventory:");
				StockManager.instance.displayAllStocks(false);
				System.out.println();
			}

			@Override
			public String getTitle() {
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
		return "Manage stock";
	}

}
