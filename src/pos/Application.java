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
	}


	// inject all dependencies here
	public static void injectDependancies(){
		
		interactableComponent.interactables.add(new ReserveInteractable());
		interactableComponent.interactables.add(new DebugInteractable());

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
