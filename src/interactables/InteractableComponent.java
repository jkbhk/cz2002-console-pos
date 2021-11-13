package interactables;
import java.util.ArrayList;

import pos.Application;
/**
 * 
 * A reusable loopable user interface that takes in IInteractable instances as UI options.
 *
 */
public class InteractableComponent {

	private ArrayList<IInteractable> interactables = new ArrayList<IInteractable>();
	private boolean enabled = false;
	private String exit_option_title;
	private boolean loopable = false;

	public InteractableComponent(String exitOption,boolean loopable){
		this.loopable = loopable;
		this.exit_option_title = exitOption;
	}

	
	public InteractableComponent(boolean loopable){
		this.loopable = loopable;
		this.exit_option_title = "Exit";
	}
	
	/**
	 * Starts the interactable component.
	 */

	public void start(){
		
		if(loopable)
			startLoop();
		else
			showInteractables();
	}

	/**
	 * Adds a IInteractable.
	 */
	public void addInteractable(IInteractable i){
		interactables.add(i);
	}

	/**
	 * Loops through all IInteractables and displays them as options.
	 */
	protected void showInteractables()
	{
		int x = 1; 
		for (IInteractable i : interactables)
		{
			System.out.println(x + " : " + i.getTitle());
			x ++;
		}

		System.out.println(x + " : " + exit_option_title);

		int choice = -1;
		
		while (choice -1 < 0 || choice -1 > interactables.size() - 1 )
		{
			System.out.println("Please Select Your Choice");
			choice = Integer.parseInt(Application.scanner.nextLine());
			
			if(choice == interactables.size()+1){
				terminate();
				return;
			}else if(choice -1 < 0 || choice -1 > interactables.size() - 1) {
				System.out.println("Invalid Selection.");
			}
		}
		
		interactables.get(choice-1).handleInput();
	}

	/**
	 * Terminate the interactable.
	 */
	public void terminate(){
		this.enabled = false;
		onTerminate();
	}

	/**
	 * Start the loop of the interactable list.
	 */
	protected void startLoop(){
		enabled = true;

		while(enabled){
			showInteractables();			
		}
	}

	/*
	 * Called when interactable is terminated.
	 */
	protected void onTerminate(){
	}
}
