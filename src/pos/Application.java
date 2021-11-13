package pos;
import java.util.Scanner;

import interactables.CustomerInteractable;
import interactables.IInteractable;
import interactables.InteractableComponent;
import interactables.LoginInteractable;
import interactables.ManagementToolsInteractable;
import interactables.MenuInteractable;
import interactables.OccupyInteractable;
import interactables.OrderInteractable;
import interactables.ReserveInteractable;
import interactables.SalesInteractable;
import interactables.StaffInteractable;
import interactables.StockInteractable;

/**
 * 
 * Main driver class that also acts as a medium for dependency injection.
 *
 */
public class Application {

	/**
	 * The very first user interface when the application starts.
	 */
	static IInteractable initialPrompt;
	/**
	 * The main loopable user interface of the application.
	 */
	static InteractableComponent interactableComponent;
	/**
	 * Globally accessible scanner object.
	 */
	public static Scanner scanner = new Scanner(System.in);
	
	//Store Details
	/**
	 * Name of the physical store.
	 */
	public static final String STORE_NAME = "COOKHOUSE1";
	/**
	 * Address of the physical store.
	 */
	public static final String STORE_ADDRESS = "Palau Tekong Drive 1";
	/**
	 * Contact number of the store.
	 */
	public static final String STORE_NUMBER = "6555 5555";

	/**
	 * Called before injectInteractables() 
	 */
	public static void initialize(){
		
		initialPrompt = new LoginInteractable();
		interactableComponent = new InteractableComponent("Save and Exit", true);
		AppDependencies.initilializeDependencies();
	}


	/**
	 * Inject all interactable dependecies for the main looping user interface. 
	 */
	public static void injectInteractables(){
				
		ManagementToolsInteractable mtools = new ManagementToolsInteractable();
		mtools.managementTools.addInteractable(new StockInteractable());
		mtools.managementTools.addInteractable(new MenuInteractable());
		mtools.managementTools.addInteractable(new CustomerInteractable());
		mtools.managementTools.addInteractable(new StaffInteractable());
		mtools.managementTools.addInteractable(new LoginInteractable());
		
		interactableComponent.addInteractable(mtools);
		interactableComponent.addInteractable(new ReserveInteractable());
		interactableComponent.addInteractable(new OrderInteractable());
		interactableComponent.addInteractable(new SalesInteractable());
		interactableComponent.addInteractable(new OccupyInteractable());
	}


	public static void main(String[] args) {
		
		initialize();
		injectInteractables();
		
		initialPrompt.handleInput();
		interactableComponent.start();
		
		onExit();
	}

	/**
	 * Called when application is closing.
	 */
	public static void onExit() {
		AppDependencies.onExit();
	}
	
	
}
