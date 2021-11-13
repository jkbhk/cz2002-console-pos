package interactables;
import java.util.ArrayList;

import pos.Application;

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

	public void start(){
		
		if(loopable)
			startLoop();
		else
			showInteractables();
	}

	public void addInteractable(IInteractable i){
		interactables.add(i);
	}

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
			}
		}
		
		interactables.get(choice-1).handleInput();
	}

	public void terminate(){
		this.enabled = false;
		onTerminate();
	}

	protected void startLoop(){
		enabled = true;

		while(enabled){
			showInteractables();			
		}
	}

	protected void onTerminate(){
	}
}