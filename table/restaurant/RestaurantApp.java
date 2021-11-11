package restaurant;
import java.util.Scanner;
// import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
public class RestaurantApp {
    public static void main(String[] args)
	{
		Restaurant r = null;
		Scanner sc = new Scanner(System.in);
		r = new Restaurant();
		int choice;
		// if (have serial file)
		// System.out.println("Do you want to 1. restore previous Resetaurant object or 2. create a new one?");
		// int choice;
		// while (true) {
		// 	try {
		// 		choice = sc.nextInt();
		// 		sc.nextLine();
		// 		if (choice != 1 && choice != 2)
		// 			throw new Exception("Invalid choice, please put 1 and 2 only");
		// 		break;
		// 	} catch (Exception e) {
		// 		System.out.println(e.getMessage());
		// 	}
		// }
		// if (choice == 1)
		// {
		// 	try {
		// 		FileInputStream fs = new FileInputStream("restaurant.ser");
		// 		ObjectInputStream is = new ObjectInputStream(fs);
		// 		r = (Restaurant) is.readObject(); // need cast because we'll get back type Object
		// 		is.close();
		// 	} catch (Exception ex) {
		// 		ex.printStackTrace();
		// 	}
		// }
		// else r = new Restaurant();
		// do {
		// 	while (true) {
		// 		System.out.println("Setting up restaurant... Enter your chioce:");
		// 		System.out.println("(1)Add table");
		// 		System.out.println("(2)Remove table");
		// 		System.out.println("(3)Add staff");
		// 		System.out.println("(4)Remove staff");
		// 		System.out.println("(5)Add member");
		// 		System.out.println("(6)Finish and run restaurant");
		// 		try {
		// 			choice = sc.nextInt();
		// 			sc.nextLine();
		// 			break;
		// 		} catch (Exception e) {
		// 			System.out.println(e.getMessage());
		// 		}
		// 	}
		// 	switch (choice) {
		// 		case 1:
		// 			int id = r.tableUI.scanID();
		// 			int cap = r.tableUI.scanCapacity();
		// 			Table t = new Table(id, cap);
		// 			r.addTable(id, t);
        //             break;
		// 		case 2:
		// 			id = r.tableUI.scanID();
		// 			r.removeTable(id);
		// 			break;
		// 		case 3:
		// 			// code
		// 			break;
		// 		case 4:
		// 			// code
		// 			break;
		// 		case 5:
		// 			// code
		// 			break;
		// 		case 6:
		// 			System.out.println("Restaurant is ready to run!");
		// 			break;
		// 		default:
		// 			System.out.println("Invalid choice!");
		// 			break;
		// 	}
		// } while (choice < 6);	
		do {
			// while (true) {
			// 	System.out.println("(1)Dine in (without reservation)"); 
			// 	System.out.println("(2)Dine in (with reservation)"); 
			// 	System.out.println("(3)Make a reservation"); 
			// 	System.out.println("(4)Remove a reservation"); 
			// 	System.out.println("(5)Find a reservation"); 
			// 	System.out.println("(6)Show all reservations"); 
			// 	System.out.println("(7)Close"); 
			// 	System.out.print("Enter the number of your choice: "); 
			// 	try {
			// 		choice = sc.nextInt();
			// 		sc.nextLine();
			// 		break;
			// 	} catch (Exception e) {
			// 		System.out.println(e.getMessage());
			// 	}
			// }
			System.out.println("(1)Dine in (without reservation)"); 
			System.out.println("(2)Dine in (with reservation)"); 
			System.out.println("(3)Make a reservation"); 
			System.out.println("(4)Remove a reservation"); 
			System.out.println("(5)Find a reservation"); 
			System.out.println("(6)Show all reservations"); 
			System.out.println("(7)List all table availability"); 
			System.out.println("(8)Close"); 
			System.out.print("Enter the number of your choice: "); 
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
				case 1:
					int pax = r.tableUI.scanPax();
					r.DineIn(pax);
					break;
				case 2: 
					String name = r.resUI.scanName();
					String contact = r.resUI.scanContact();
                    int re = r.reservedDineIn(name, contact);
                    switch(re){
                    case 0: // res not found
                        pax = r.tableUI.scanPax();
                        r.DineIn(pax);
                        break;
                    case 1: // edge case: early arrival
                        int c = r.resUI.scanEarlyArrivalChoice();
                        if (c == 1)
                            if (r.earlyReservedDineIn(name, contact)) break;
                        pax = r.tableUI.scanPax();
                        r.DineIn(pax);
                        break;
                    case 2: // successfully dine in
                        break;
                    }
					break;
				case 3: 
                    name = r.resUI.scanName();
                    contact = r.resUI.scanContact();
                    LocalDateTime dateTime = r.resUI.scanTime();
                    pax = r.resUI.scanPax();
					r.makeReservation(dateTime, name, contact, pax);
					break;
				case 4: 
                    name = r.resUI.scanName();
                    contact = r.resUI.scanContact();
                    String key = name + contact + LocalDate.now();
					r.removeReservation(key);
					break;
				case 5: 
                    name = r.resUI.scanName();
                    contact = r.resUI.scanContact();
                    LocalDate date = r.resUI.scanTime().toLocalDate();
                    key = name + contact + date;
                    r.showRes(key);
					break;
				case 6: 
					r.showAllRes();
					break;
                case 7:
                    r.listAvail();
                    break;
				case 8: 
					System.out.println("Program terminating ....");
					r.close();
					break;
				default:
					System.out.println("Invalid choice!");
					break;
			}
			System.out.println(""); 
		} while (choice < 8);
        sc.close();
	}
}

