package pos;
// Hello Test
import java.util.ArrayList;

public class Application {

	static ArrayList<ISerializable> serializableGroup;
	
	public static void main(String[] args) {
		
		MenuManager menuManager = new MenuManager();
		
		serializableGroup.add(menuManager);
		
	}
	
	public static void onExit() {
		for(ISerializable s : serializableGroup) {
			s.serialize();
		}
	}
	
	
	
}
