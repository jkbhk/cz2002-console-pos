package pos;
import java.util.ArrayList;
import java.util.Scanner;

// inject dependencies from this class
public class Application {

	static InteractableComponent interactableComponent;
	public static Scanner scanner = new Scanner(System.in);

	// called before injectInteractables
	public static void initialize(){
		interactableComponent = new InteractableComponent(true);
		AppDependencies.initilializeDependencies();
	}


	// inject all interactables here (add your interactables to the interatableComponent)
	public static void injectInteractables(){
				
		ManagementToolsInteractable mtools = new ManagementToolsInteractable();
		mtools.managementTools.addInteractable(new StockInteractable());
		mtools.managementTools.addInteractable(new MenuInteractable());
		mtools.managementTools.addInteractable(new CustomerInteractable());
		
		interactableComponent.addInteractable(mtools);
		interactableComponent.addInteractable(new ReserveInteractable());
		interactableComponent.addInteractable(new ExampleInteractable());
		interactableComponent.addInteractable(new OrderInteractable());
	}


	public static void main(String[] args) {
		
		initialize();
		injectInteractables();
		
		interactableComponent.start();
		
		onExit();
	}
	
	public static void onExit() {
		AppDependencies.onExit();
	}
	
	
}
