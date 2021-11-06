package pos;
import java.util.ArrayList;
import java.util.Scanner;

// inject dependecies from this class
public class Application {

	static ArrayList<ISerializable> serializableGroup;
	static InteractableComponent interactableComponent;
	public static Scanner scanner = new Scanner(System.in);

	// called before injectInteractables
	public static void initialize(){
		serializableGroup = new ArrayList<>();
		interactableComponent = new InteractableComponent(true);

		// create all your managers here and inject dependencies if required
		StockManager stockManager = new StockManager(new StockDao());
		MenuManager menuManager = new MenuManager(new MenuItemDao());
		ExampleManager exampleManager = new ExampleManager();
		ReservationManager reservationManager = new ReservationManager();
		CustomerManager customerManager = new CustomerManager();
		


		// add serializables here
		serializableGroup.add(stockManager);
		serializableGroup.add(menuManager);
	}


	// inject all interactables here (add your interactables to the interatableComponent)
	public static void injectInteractables(){
		interactableComponent.addInteractable(new ReserveInteractable());
		interactableComponent.addInteractable(new CustomerInteractable());
		interactableComponent.addInteractable(new ExampleInteractable());
		interactableComponent.addInteractable(new StockInteractable());
		interactableComponent.addInteractable(new MenuInteractable());

	}


	public static void main(String[] args) {
		
		initialize();
		injectInteractables();
		
		interactableComponent.start();

		// broke from interactableComponent
		onExit();
		
	}
	
	public static void onExit() {
		for(ISerializable s : serializableGroup) {
			s.serialize();
		}
	}
	
	
	
}
