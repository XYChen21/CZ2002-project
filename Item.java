package projectoop;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Item extends Food implements Comparable<Item>{
	public enum KindofFood {
		MAIN, SIDES, BEVERAGE, DESSERT;
		
		public static KindofFood getTypeByOrdinal(int ordinal) {
	        for(KindofFood t : KindofFood.values()) {
	            if(t.ordinal() == ordinal-1) {
	                return t;
	            }
	        }

	        return null;
	    }
	
	};
	
	
	private String name;
	private String description;
	private double price;
	private int index;
	private KindofFood type;

public Item(int ID, String name, String description, double price, KindofFood type)
{this.index = ID;
this.name = name;
this.description = description;
this.price = price;
this.type = type;
}


public int getIndex()
{return index;}

public KindofFood getType()
{return type;}

public String getName()
{return name;}

public String getDescription()
{return description;}

public double getPrice()
{return price;}

public void setIndex()
{this.index = index;}

public void setType(KindofFood type)
{this.type = type;}

public void setName(String name)
{this.name = name;}

public void setDescription(String description)
{this.description = description;}

public void setPrice(double price)
{this.price = price;}

@Override
public String toString() {
  return 
      "Item Index = " + index + '\n' +
      "Name = " + name + '\n' +
      "Description = " + description + '\n' +
      "Price = " + price + '\n' +
      "Type = " + type + '\n'
      ;
}

public String toStringCust() {
	  return 
	      "Item Index = " + index + '\n' +
	      "Name = " + name + '\n' +
	      "Description = " + description + '\n' +
	      "Type = " + type + '\n'
	      ;
	}


@Override
public int compareTo(Item o) {
	// TODO Auto-generated method stub
	return 0;
}



}
