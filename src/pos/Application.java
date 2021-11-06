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
		ExampleManager exampleManager = new ExampleManager();
		ReservationManager reservationManager = new ReservationManager();
		CustomerManager customerManager = new CustomerManager();


		// add serializables here
		serializableGroup.add(stockManager);
	}


	// inject all interactables here (add your interactables to the interatableComponent)
	public static void injectInteractables(){
		
		interactableComponent.interactables.add(new ReserveInteractable());
		interactableComponent.interactables.add(new CustomerInteractable());
		//interactableComponent.interactables.add(new DebugInteractable());
		interactableComponent.interactables.add(new ExampleInteractable());
		interactableComponent.interactables.add(new StockInteractable());

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
