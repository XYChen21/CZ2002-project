package restaurant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import restaurant.Item.KindofFood;

public class Menu implements Serializable {
	private HashMap<Integer, Item> menu;
//	private List<Item> menu;

	public Menu() {
		this.menu = new HashMap<Integer, Item>();
	}

	public void addMenu(int index, Item i) {
		boolean duplicate = false;
		if (menu.get(index) != null)
			duplicate = true;
		if (duplicate == false) {
			menu.put(index, i);
		}
		else {
			System.out.println("Duplicate item in this menu.");
		}
	}

	public void removeMenu(int index) {
		boolean removed = false;
		if (menu.get(index) != null)
			removed = true;
		if (removed == true) {
			menu.remove(index);
			System.out.println("Item is removed.");
		}
		if (removed == false) {
			System.out.println("Item with that index does not exist. Nothing is removed.");
		}
	}

	public boolean updateMenu(Item a) {
		Scanner sc = new Scanner(System.in);
		System.out.println(
				"What do you want to update? Enter 1 for Index, 2 for Name, 3 for Description, 4 for Price and 5 for Type of food.");
		int scan = sc.nextInt();

		if (scan == 1) {
			System.out.println("Enter the new index you want to update the item with.");
			int b = sc.nextInt();
			boolean duplicate = false;
			if (menu.get(b) != null)
				duplicate = true;
			
			if (duplicate == false) {
				a.setIndex(b);
				System.out.println("Updated successfully");
				return false;
			}
			else {
				System.out.println("Duplicate index of items in this menu.");
				return false;
			}
		}

		else if (scan == 2) {
			System.out.println("Enter the new name you want to update the item with.");
			sc.nextLine();
			String b = sc.nextLine();
			a.setName(b);
			System.out.println("Updated successfully");
			return false;
		}

		else if (scan == 3) {
			System.out.println("Enter the new description you want to update the item with.");
			sc.nextLine();
			String b = sc.nextLine();
			a.setDescription(b);
			System.out.println("Updated successfully");
			return false;

		}

		else if (scan == 4) {
			System.out.println("Enter the new price you want to update the item with.");
			double b = sc.nextDouble();
			a.setPrice(b);

			System.out.println("Updated successfully");
			return true;
		}

		else if (scan == 5) {
			System.out.println(
					"Enter the new type of food you want to update the item with. Enter 1 for Mains, 2 for Sides, 3 for Beverages and 4 for Dessert.");
			int b = sc.nextInt();
			KindofFood f = KindofFood.getTypeByOrdinal(b);
			a.setType(f);
			System.out.println("Updated successfully");
			return false;
		}

		return false;
	}

//		public void filterMenuByType(KindofFood k)
//		{List<Item> newMenu=new ArrayList<Item>(); 
//		boolean empty = true;
//		for(Item food:menu)
//			{if (food.getType() == k)
//				{newMenu.add(food);
//				empty = false;
//				}}
//
//		sortByDefault(newMenu);
//		printMenu(newMenu);
//
//		if (empty == true)
//		{System.out.println("There are no items with that category.");}
//		}

//		public void filterMenuByPrice(Double a, Double b)
//		{List<Item> newMenu2=new ArrayList<Item>(); 
//		boolean empty = true;
//
//		for(Item food:menu)
//		{if (food.getPrice() >= a && food.getPrice()<=b)
//			{newMenu2.add(food);
//			empty = false;
//			}}
//
//		sortByDefault(newMenu2);
//		printMenu(newMenu2);
//
//		if (empty == true)
//		{System.out.println("There are no items within that price range.");}
//		}

//		public void sortMenuByAlphabet() {
//			menu.sort(Comparator.comparing(Item::getName));
//			printMenu(menu);
//		}

	public void viewMenu() {
		System.out.println("************** MENU ****************" + '\n');
		for (int index : menu.keySet())
			System.out.println(menu.get(index).toString());
	}

//	private void sortByDefault(List<Item> k) {
//		k.sort(Comparator.comparing(Item::getIndex));
//	}

//		private void printMenu(List<Item>a)
//		{System.out.println("************** MENU ****************" + '\n');
//		for(Item food:a)  
//		   System.out.println(food.toString());}

	public Item getItem(int index) {
		return menu.get(index);
	}
}