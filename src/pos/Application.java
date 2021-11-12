package pos;
import java.util.Scanner;


public class Application {

	static IInteractable initialPrompt;
	static InteractableComponent interactableComponent;
	public static Scanner scanner = new Scanner(System.in);
	
	//Store Details
	public static final String STORE_NAME = "WesternWednesday";
	public static final String STORE_ADDRESS = "Palau Tekong Drive 1";
	public static final String STORE_NUMBER = "6555 5555";

	// called before injectInteractables
	public static void initialize(){
		
		initialPrompt = new LoginInteractable();
		interactableComponent = new InteractableComponent("Save and Exit", true);
		AppDependencies.initilializeDependencies();
	}


	// inject all interactable dependecies 
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
	
	public static void onExit() {
		AppDependencies.onExit();
	}
	
	
}
