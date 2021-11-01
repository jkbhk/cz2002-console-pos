package pos;
// Hello Test
import java.util.ArrayList;
import java.util.Scanner;

public class Application {

	static ArrayList<ISerializable> serializableGroup;
	static InteractableComponent interactableComponent = new InteractableComponent();
	public static Scanner scanner = new Scanner(System.in);

	// called before injectDependancies
	public static void initialize(){
		serializableGroup = new ArrayList<>();
		interactableComponent = new InteractableComponent();

		// create all your managers here
		ExampleManager exampleManager = new ExampleManager();
	}


	// inject all dependencies here (add your interactables to the interatableComponent)
	public static void injectDependancies(){
		
		interactableComponent.interactables.add(new ReserveInteractable());
		interactableComponent.interactables.add(new DebugInteractable());
		interactableComponent.interactables.add(new ExampleInteractable());

	}


	public static void main(String[] args) {
		
		initialize();
		injectDependancies();
		
		while(interactableComponent.IsEnabled()){
			interactableComponent.showInteractables();
		}

		// broke from interactableComponent
		onExit();
		
	}
	
	public static void onExit() {
		for(ISerializable s : serializableGroup) {
			s.serialize();
		}
	}
	
	
	
}
