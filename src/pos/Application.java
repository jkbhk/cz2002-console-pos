package pos;
import java.util.Scanner;

// inject dependencies from this class
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
		interactableComponent = new InteractableComponent(true);
		AppDependencies.initilializeDependencies();
	}


	// inject all interactables here (add your interactables to the interatableComponent)
	public static void injectInteractables(){
				
		ManagementToolsInteractable mtools = new ManagementToolsInteractable();
		mtools.managementTools.addInteractable(new StockInteractable());
		mtools.managementTools.addInteractable(new MenuInteractable());
		mtools.managementTools.addInteractable(new CustomerInteractable());
		mtools.managementTools.addInteractable(new StaffInteractable());
		mtools.managementTools.addInteractable(new LoginInteractable());
		
		interactableComponent.addInteractable(mtools);
		interactableComponent.addInteractable(new ReserveInteractable());
		interactableComponent.addInteractable(new ExampleInteractable());
		interactableComponent.addInteractable(new OrderInteractable());
		interactableComponent.addInteractable(new DebugInteractable());
		interactableComponent.addInteractable(new SalesInteractable());
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
