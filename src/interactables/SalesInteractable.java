package interactables;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import entities.Invoice;
import managers.InvoiceManager;
import pos.InvoicePrinter;
import pos.TemplateAAdapter;

/**
 * 
 * Facade object that generates sales reports.
 *
 */
public class SalesInteractable implements IInteractable{

	InteractableComponent reportAssistant = new InteractableComponent("Back",true);
	Calendar cal = Calendar.getInstance();
	
	public SalesInteractable() {
		
		reportAssistant.addInteractable(new IInteractable() {

			@Override
			public void handleInput() {
				printTotalRevenueFor(LocalDate.now().toString(), InvoiceManager.instance.getInvoiceListForCurrentDay());
			}

			@Override
			public String getTitle() {
				return "Revenue for today";
			}
			
		});

		reportAssistant.addInteractable(new IInteractable() {

			@Override
			public void handleInput() {
				String starting = LocalDate.now().getYear() + "-" + LocalDate.now().getMonthValue() + "-1";
				String ending = LocalDate.now().getYear() + "-" + LocalDate.now().getMonthValue() + "-"+cal.getActualMaximum(Calendar.DATE);
				
				printTotalRevenueFor(starting + " to " + ending ,InvoiceManager.instance.getInvoiceListByMonth(LocalDate.now().getMonthValue()));
			}

			@Override
			public String getTitle() {
				return "Revenue for this month";
			}
			
		});
	}
	
	@Override
	public void handleInput() {
		
		reportAssistant.start();
		
	}

	@Override
	public String getTitle() {
		return "Generate Revenue Report";
	}
	
	private void printTotalRevenueFor(String period, ArrayList<Invoice> list) {
		
		double total = 0;
		
		if(list.size() <= 0) {
			System.out.println("no revenue for this time period :(");
			return;
		}
		
		System.out.println("********************* PRINTING ALL INVOICES *********************");
		System.out.println();
		
		for(Invoice i : list) {
			InvoicePrinter.printTemplate(new TemplateAAdapter(i));
			total += i.getNetTotal();
		}
		
		String revenue = String.format("$%.2f", total);
		
		System.out.println();
		System.out.println("Total revenue for " + period + " :");
		System.out.println(revenue + "\n");
	}

}
