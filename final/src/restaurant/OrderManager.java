package restaurant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.io.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.lang.model.element.Element;

public class OrderManager implements Serializable{
	private ArrayList<Order> ordersbyID; 
	private int orderID;
	private LocalDate _start;
	private LocalDate _end;
	private double totalRevenue;
	
	public OrderManager() {
		ordersbyID = new ArrayList<Order>();
		orderID = -1;
		totalRevenue = 0.0;
	}
	
	public boolean checkValidOrderID(int id) {
		if (id >= 0 && id <= orderID) {return true;}
		else {return false;}
	}
	
	public void createOrder(int tableID, String staffServer) {
		if (orderID == -1) {orderID = 0;}
		Order newOrder = new Order(tableID, orderID, staffServer);
		ordersbyID.add(newOrder);
		System.out.println("New order for table " + tableID + " with orderID " + orderID + " has been successfully created.");
		orderID++;
	}
	
	public int getorderID() {
		return orderID;
	}
	
	public Order getOrder(int orderid) {
		return ordersbyID.get(orderid);
	}
	
	public void viewOrder(int orderID) {
		if (ordersbyID.get(orderID).haveOrder()) {
			HashMap<Food, Integer> orders = ordersbyID.get(orderID).getOrders();
			System.out.println("======== Current order summary ========");
			orders.forEach((key, value) -> {
				System.out.println(value + " " + key.getName());
			});
		} 
		else {
			System.out.println("You have not ordered any food yet.");
		}
	}
	
	public void addOrder(int orderID, Food food, int quantity) {
	
		// Check if order exists -> exists, increment. If not, create new
		if (ordersbyID.get(orderID).getOrders().get(food) != null) { // Food exists in the order
			int currentQuantity = ordersbyID.get(orderID).getOrders().get(food);
			ordersbyID.get(orderID).getOrders().put(food, currentQuantity + quantity);
		} 
		else { // Food does not exist in the order yet
			ordersbyID.get(orderID).getOrders().put(food, quantity);
		}
		System.out.println("You have ordered " + ordersbyID.get(orderID).getOrders().get(food) + " " + food.getName());
	}
	
	public void removeOrder(int orderID, Food food, int quantity) {
		
		if (ordersbyID.get(orderID).haveOrder()) { 
			if (ordersbyID.get(orderID).getOrders().get(food) != null) { // Food exists in the order
				int currentQuantity = ordersbyID.get(orderID).getOrders().get(food);
				if (currentQuantity - quantity == 0) { // Customer wants to completely remove food from the order
					ordersbyID.get(orderID).getOrders().remove(food);
					System.out.println(food.getName() + " is removed from your order.");
				} 
				else if (currentQuantity - quantity > 0) { // Customer wants a reduced quantity of current food ordered
					ordersbyID.get(orderID).getOrders().put(food, currentQuantity - quantity);
					System.out.println("You have ordered " + ordersbyID.get(orderID).getOrders().get(food) + " " + food.getName());
				} 
				else { // Invalid quantity entered -- error
					System.out.println("Please re-enter a valid quantity to remove from current order of " + currentQuantity + " " + food.getName());
				}
			}
			else { // Food does not exist in the order, error
				System.out.println(food.getName() + " does not exist in your order yet. Please input a valid food to remove from order.");
			}
		} 
		else { 
			System.out.println("You have not created order yet");
		}
	}
	
	public int printInvoice(boolean membership, int orderid) {
		double subTotal = 0;
		// Print the Restaurant name and address
		System.out.println("SCSE Restaurant\n" + "Nanyang Technological University\n" + "50 Nanyang Avenue\n" + "Singapore 639798\n" + "Tel: (65) 6790 5786\n");
		// Print Check number -> orderID
		System.out.println("Check #: " + orderid);
		Order completeOrder = ordersbyID.get(orderid);
		// Print Staff name (Server)
		System.out.println("Served by: " + completeOrder.getStaffServer());
		// Print Table Number
		System.out.println("Table: " + completeOrder.gettableID());
		// Print DateTime dd/mm/yyyy hh:mm
		LocalDateTime dt = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		String invoiceDateTime = dt.format(format);
		System.out.println("Date and time: " + invoiceDateTime);
		System.out.println("----------------------------------------------------");
		// Print Quantity, Name of food, Individual Cost
		for (Food f : completeOrder.getOrders().keySet()) {
			int quantity = completeOrder.getOrders().get(f);
			System.out.println(quantity+ " " + f.getName() + "\t" + String.format("%.2f", f.getPrice()));
			subTotal += f.getPrice();
		}
		// Print SubTotal
		System.out.println("SubTotal: " + String.format("%.2f", subTotal));
		// Print Taxes -> 7% GST
		double taxes = subTotal * 0.17;
		System.out.println("GST and Service Charge: " + String.format("%.2f", taxes));
		subTotal *= 1.17;
		// Print Membership Discount -> if membership == true, apply and print
		if (membership) {
			System.out.println("Membership discount: " + 10 + "%");
			subTotal *= 0.9;
		}
		// Print Total
		System.out.println("Total: $" + String.format("%.2f", subTotal));
		// Revenue = Total - Taxes
		double total = subTotal - taxes;
		ordersbyID.get(orderid).setTotal(total);
		ordersbyID.get(orderid).checkout(true);
				
		return completeOrder.gettableID();
	}
	
	// Revenue report method
	public void parseTme(String...t){
		try {
			this._end = LocalDate.parse(t[1], DateTimeFormatter.ofPattern("d/M/y"));
			this._start = LocalDate.parse(t[0], DateTimeFormatter.ofPattern("d/M/y"));
		} catch (DateTimeParseException _e){
			this._start = LocalDate.MIN;
			this._end = LocalDate.MAX;
			if(t[0] != "" && t[1] != "") System.out.println("Invalid Input! Printing All Paid Orders");
		} catch (IndexOutOfBoundsException _e){
			this._start = LocalDate.MIN;
			this._end = LocalDate.MAX;
		}
	}

	public void printRevenueReport(int choice) {
		/**
		 * choice:
		 * 		0: by item amount
		 * 		1: by orderId
		 * 		2: by order amount
		 */

        ArrayList<Order> orders4print = this.ordersbyID.stream()
        .filter(o -> (o.getTime().toLocalDate().isAfter(this._start) && o.getTime().toLocalDate().isBefore(this._end)))
		.filter(o -> o.paid())
        .collect(Collectors.toCollection(ArrayList::new));
        double total = 0.0;
        double p = 0;

		HashMap<Food, Integer> saledItems = new HashMap<>();
		orders4print.forEach(o -> 
		{
			o.getOrders().forEach((k, v) -> 
			saledItems.put(k, v + saledItems.getOrDefault(k, 0)));
		});

		HashMap<Food, Integer> temp = saledItems;

        System.out.println("                Restaurant Name                 ");
        System.out.println("        ********************************        ");
        System.out.println();
        System.out.println("From \t" + _start.toString() + "To \t" + _end.toString());
        System.out.println();
        System.out.println("------------------------------------------------");
		switch(choice){
			case 0:
				temp.entrySet().stream()
				.sorted((k1, k2) -> -k1.getValue().compareTo(k2.getValue()))
				.forEach(k -> 
				System.out.println(k.getKey() + "\t\t\t\t\t" + k.getValue())
				);
				break;
			case 1:
				orders4print.sort((a, b) -> a.getorderID() - b.getorderID());
				orders4print.forEach(
					o -> {
						System.out.println(o.getorderID() + "\t\t\t\t\t" + o.getTotal());
						this.totalRevenue += o.getTotal();
					}
				);
				break;
			case 2:
				orders4print.sort((a, b) -> (int)(a.getTotal() - b.getTotal()));
				orders4print.forEach(
					o -> {
						System.out.println(o.getorderID() + "\t\t\t\t\t" + o.getTotal());
						this.totalRevenue += o.getTotal();
					}
				);
				break;
		}
		System.out.println("------------------------------------------------");
        if(total != 0.0) System.out.println("                                 Total: " + String.format("%-8.2f", this.totalRevenue));
    }
}
