package pos;
import java.util.ArrayList;
import java.util.*;

public class InteractableComponent {

	public ArrayList<IInteractable> interactables = new ArrayList<IInteractable>();
	public static Scanner sc = new Scanner(System.in);
	
	public void showInteractables()
	{
		int x = 1; 
		for (IInteractable i : interactables)
		{
			System.out.println(x + " : " + i.getTitle());
			x ++;
		}
		int choice = -1;
		
		while (choice -1 < 0 || choice -1 > interactables.size() - 1 )
		{
			System.out.println("Please Select Your Choice");
			choice = sc.nextInt();
		}
		
		interactables.get(choice-1).handleInput();
	}
	
	
}
