package interactables;

public class ManagementToolsInteractable implements IInteractable{

	public InteractableComponent managementTools = new InteractableComponent("Back", true);
	
	@Override
	public void handleInput() {
		
		managementTools.start();
	}

	@Override
	public String getTitle() {
		return "Management Tools";
	}

	
	
}
