package pos;
import java.util.ArrayList;
import java.util.*;

public class InteractableComponent {

	public ArrayList<IInteractable> interactables = new ArrayList<IInteractable>();
	private boolean enabled = true;
	
	public void showInteractables()
	{
		int x = 1; 
		for (IInteractable i : interactables)
		{
			System.out.println(x + " : " + i.getTitle());
			x ++;
		}

		System.out.println(x + " : " + "Exit application.");

		int choice = -1;
		
		while (choice -1 < 0 || choice -1 > interactables.size() - 1 )
		{
			System.out.println("Please Select Your Choice");
			choice = Application.scanner.nextInt();
			
			if(choice == interactables.size()+1){
				enabled = false;
				return;
			}
		}
		
		interactables.get(choice-1).handleInput();
	}

	public boolean IsEnabled(){
		return this.enabled;
	}
	
	
}
