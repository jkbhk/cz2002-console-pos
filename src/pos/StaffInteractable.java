package pos;

public class StaffInteractable implements IInteractable {

	private InteractableComponent staffAssistant = new InteractableComponent("Back", true);
	
	public StaffInteractable() {
		
		staffAssistant.addInteractable(new IInteractable() {

			@Override
			public void handleInput() {
				
				System.out.println("Enter new staff name:");
				String name = Application.scanner.nextLine();
				System.out.println("Enter gender:");
				String gender = Application.scanner.nextLine();
				System.out.println("Select job title:");
				String jobTitle = "";
				StaffManager.instance.displayJobTitles();
				int choice = Integer.parseInt(Application.scanner.nextLine());
				choice-=1;
				if(choice >= 0 && choice < StaffManager.instance.JOB_TITLES.length) {
					jobTitle = StaffManager.instance.JOB_TITLES[choice];
					StaffManager.instance.createNewStaff(IDGenerator.GenerateUniqueID(), name,gender,jobTitle);
					System.out.println("new staff added to system\n");
				}else {
					System.out.println("invalid selection\n");
				}
				
			}

			@Override
			public String getTitle() {
				return "Add New Staff";
			}
			
		});
			
			staffAssistant.addInteractable(new IInteractable() {

				@Override
				public void handleInput() {
					
					if(StaffManager.instance.getStaffCount() <= 0) {
						System.out.println("Staff list is currently empty!");
						return;
					}
					
					System.out.println("Select staff to remove from system : ");
					StaffManager.instance.displayAllStaff();
					int choice = Integer.parseInt(Application.scanner.nextLine());
					boolean success = StaffManager.instance.deleteStaff(choice-1);
					String m = success ? "staff removed from system" : "invalid selection";
					System.out.println(m);
				}

				@Override
				public String getTitle() {
					return "Remove Staff";
				}
				
			});
			
			staffAssistant.addInteractable(new IInteractable() {

				@Override
				public void handleInput() {
					if(StaffManager.instance.getStaffCount() <= 0) {
						System.out.println("Staff list is currently empty!");
						return;
					}
					StaffManager.instance.displayAllStaff();
				}

				@Override
				public String getTitle() {
					return "Show all current staff";
				}
				
			});
		
	}
	
	@Override
	public void handleInput() {
		staffAssistant.start();
	}

	@Override
	public String getTitle() {
		return "Manage Staff";
	}

}
