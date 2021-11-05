package projectoop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import projectoop.Item.KindofFood;

public class Menu {
		private List<Item> menu; 
		
		public Menu()
		{this.menu=new ArrayList<Item>();  
		}

		
		public  void addMenu(Item i)
		{boolean duplicate = false;
		for(Item food:menu)
			{if (food.getIndex() == i.getIndex())
				{duplicate = true;
				break;
				
				}}
		if (duplicate == false)
		{
		menu.add(i);}

		else
		{System.out.println("Duplicate index of items in this menu.");}
		}
		


		public void removeMenu(int a)
		{boolean removed = false;
		int count = 0;

		for(Item food:menu)
			{if (food.getIndex() == a)
				{
				removed = true;
				break;
				}
			count++;
		}

		if (removed == true)
		{menu.remove(count);
		System.out.println("Item is removed.");}

		if (removed == false)
		{System.out.println("Item with that index does not exist. Nothing is removed.");}
		}

		public void filterMenuByType(KindofFood k)
		{List<Item> newMenu=new ArrayList<Item>(); 
		boolean empty = true;
		for(Item food:menu)
			{if (food.getType() == k)
				{newMenu.add(food);
				empty = false;
				}}

		sortByDefault(newMenu);
		printMenu(newMenu);

		if (empty == true)
		{System.out.println("There are no items with that category.");}
		}

		public void filterMenuByPrice(Double a, Double b)
		{List<Item> newMenu2=new ArrayList<Item>(); 
		boolean empty = true;

		for(Item food:menu)
		{if (food.getPrice() >= a && food.getPrice()<=b)
			{newMenu2.add(food);
			empty = false;
			}}

		sortByDefault(newMenu2);
		printMenu(newMenu2);

		if (empty == true)
		{System.out.println("There are no items within that price range.");}
		}


		public void sortMenuByAlphabet() {
			menu.sort(Comparator.comparing(Item::getName));
			printMenu(menu);
		}


		public void viewMenu()
		{System.out.println("************** MENU ****************" + '\n');
		sortByDefault(menu);
		for(Item food:menu)  
		   System.out.println(food.toString());
		}

		private void sortByDefault(List<Item> k)
		{k.sort(Comparator.comparing(Item::getIndex));
		}

		private void printMenu(List<Item>a)
		{System.out.println("************** MENU ****************" + '\n');
		for(Item food:a)  
		   System.out.println(food.toString());}
		
		public int getItem(int index)
		{int a = 0;
		for(Item food:menu)
		{if (food.getIndex() == index)
			{return a;}
			a++;}

		return -1;

		}
		

		public Item getItemByIndex(int a)
		{
		System.out.println(menu.get(a).toString());
		return menu.get(a);
		
			
			
		}}
		
		

