package pos;
// Hello Test
import java.util.ArrayList;

public class Application {

	static ArrayList<ISerializable> serializableGroup;
	static InteractableComponent interactableComponent = new InteractableComponent();
	
	
	public static void main(String[] args) {
		
		MenuManager menuManager = new MenuManager();
		interactableComponent.interactables.add(new ReserveInteractable());
		//serializableGroup.add(menuManager);
		interactableComponent.showInteractables();
		
	}
	
	public static void onExit() {
		for(ISerializable s : serializableGroup) {
			s.serialize();
		}
	}
	
	
	
}
