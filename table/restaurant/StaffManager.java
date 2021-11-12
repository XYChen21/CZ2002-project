package restaurant;
import java.util.*;
import java.io.*;

public class StaffManager implements Serializable{
	private HashMap<Integer, Staff> sMap;
	
	public StaffManager() {
		sMap = new HashMap<Integer, Staff>(); //Key: Staff ID; Value: Staff object
	}
	
	public void addStaff(int id, Staff newStaff) {
		Staff s = sMap.get(id);
		if (s != null) { // Staff already exists in staff roster, error
			System.out.println("Staff already exists.");
			s.printStaff();
			System.out.println("Please check if you have made a mistake when entering staff particulars and try again.");
		}
		else { // Staff does not exist in staff roster, can add
			sMap.put(id, newStaff);
			System.out.println("Successfully added staff " + newStaff.getStaffName() + " ID: " + id + ".");
		}
	}
	
	public void removeStaff(int id) {
		Staff s = sMap.get(id);
		if (s != null) { // Staff exists in staff roster, can confirm and proceed to remove
			System.out.println("Here are the particulars of the staff you wish to remove: ");
			s.printStaff();
			System.out.println("To proceed with removal of staff, enter 1");
			System.out.println("To cancel removal of staff, enter 2");
			Scanner sc = new Scanner(System.in);
			boolean exit=false;
			while (!exit) {
				int choice;
				try {
					choice = sc.nextInt();
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return;
				}
				sc.nextLine();
				if (choice == 1) { // Remove Staff from staff roster
					sMap.remove(id);
					System.out.println("Successfully removed staff.");
					exit=true;
				}
				else if (choice == 2) { // Cancel removal of Staff
					System.out.println("Cancelled removal of staff.");
					exit=true;
				}
				else { // Invalid choice given
					System.out.println("Please enter a valid choice of 1 (proceed with removal of staff) or 2 (cancel removal of staff).");
				}
			}
		}
		else { // Staff does not exist in staff roster, error
			System.out.println("The staff you have chosen to remove does not exist.");
			System.out.println("Please check if you have entered the correct staff ID to remove and try again.");
		}
	}
	
	public boolean isStaff(int staffID, String name) {
		if (sMap.get(staffID).getStaffName().equals(name)) {return true;}
		else {System.out.println("Staff with staffID " + staffID + " and name " + name + " does not exist.");
		return false;}
	}
}
