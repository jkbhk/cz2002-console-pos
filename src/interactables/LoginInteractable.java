package interactables;

import entities.Staff;
import managers.StaffManager;
import pos.Application;
import pos.IDGenerator;

/**
 * 
 * Facade that facilitates the log in of staff into the POS system.
 *
 */
public class LoginInteractable implements IInteractable{

	@Override
	public void handleInput() {
		
		if(StaffManager.instance.getStaffCount() <=0 ) {
			StaffManager.instance.createNewStaff(IDGenerator.GenerateUniqueID(),"annonymous","?",StaffManager.instance.JOB_TITLES[0]);
		}
		
		System.out.println("Select staff for this session.");
		StaffManager.instance.displayAllStaff();
		
		int choice = Integer.parseInt(Application.scanner.nextLine());
		choice-=1;
		if(choice >= 0 && choice < StaffManager.instance.getStaffCount()) {
			Staff selected = StaffManager.instance.getStaff(choice);
			StaffManager.instance.setCurrentStaff(selected);
			System.out.println("Current staff for this session: " + selected.getStaffName() + "\n");
		}else {
			System.out.println("invalid selection.");
			handleInput();
		}
	}

	@Override
	public String getTitle() {
		return "Set Currrent Staff";
	}
	
}
