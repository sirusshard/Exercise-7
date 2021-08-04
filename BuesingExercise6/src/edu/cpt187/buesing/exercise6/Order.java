//AUTHOR: Zach Buesing
//COURSE: CPT 187
//PURPOSE: Stores and calculates values for item labeling, searching, and stock counts.
//CREATEDATE: 7/22/2021
package edu.cpt187.buesing.exercise6;

//Beginning of Order
public class Order 
{
	//Declaring and Initializing Class Attributes
	private final double TAX_RATE = .085;
	private int discountType = 0;
	private int itemID = 0;
	private String itemName = "";
	private double itemPrice = 0.0;
	private String discountName = "";
	private double discountRate = 0.0;
	private int howMany = 0;
	private int lastItemSelectedIndex = 0;
	private String prizeName = "";

	//--Constructor--

	//Default Constructor
	public Order()
	{
	}//End of Default Constructor

	//--Setters

	public void setLastItemSelectedIndex(int borrowedSearchIndex)
	{
		lastItemSelectedIndex = borrowedSearchIndex;
	}//End of setLastItemSelectedIndex

	//This Behavior Will Assign a Borrowed Array at lastItemSelectedIndex to itemID;
	public void setItemID(int[] borrowedItemIDs)
	{
		itemID = borrowedItemIDs[lastItemSelectedIndex];
	}//End of setItemID

	//This Behavior Will Assign A borrowed Array at lastItemSelectedIndex to itemName
	public void setItemName(String[] borrowedItemNames)
	{
		itemName = borrowedItemNames[lastItemSelectedIndex];
	}//End of setItemName

	//This Behavior Will Assign A borrowed Array at lastItemSelectedIndex to itemPrice
	public void setItemPrice(double[] borrowedItemPrices)
	{
		itemPrice = borrowedItemPrices[lastItemSelectedIndex];
	}//End of setItemPrice

	//This Behavior Will Assign a Borrowed Value to howMany
	public void setHowMany(String borrowedHowMany)
	{
		howMany = Integer.valueOf(borrowedHowMany);
	}//End of setHowMany

	//This Behavior Will Calculate The Value of discountType Using a borrowed Value
	public void setDiscountType(char borrowedMenuSelection)
	{
		discountType = borrowedMenuSelection - 'A';
	}//End of setDiscountType

	//This Behavior Will Assign a Borrowed Array to discountName at discountType
	public void setDiscountName(String[] borrowedDiscountNames)
	{
		discountName = borrowedDiscountNames[discountType];
	}//End of setDiscountName

	//This Behavior Will Assign a Borrowed Array to discountRate at discountType
	public void setDiscountRate(double[] borrowedDiscountRate)
	{
		discountRate = borrowedDiscountRate[discountType];
	}//End of setDiscountRate

	//This Behavior will Assign a Borrowed Array to prizeName at a borrowed Value
	public void setPrizeName(String[] borrowedPrizeNames, int borrowedPrizeIndex)
	{
		prizeName = borrowedPrizeNames[borrowedPrizeIndex];
	}//End of setPrizeName

	//This behavior Will invoke setReduceStock
	public void setDecreaseInStock(Inventory borrowedInventoryObject)
	{
		borrowedInventoryObject.setReduceStock(howMany);
	}//End of setDescreaseInStock


	//--Getters

	//This Behavior Will Return a borrowed Array at lastItemSelectedIndex
	public int getInStockCount(int[] borrowedInStockCounts)
	{
		return borrowedInStockCounts[lastItemSelectedIndex];
	}//End of getInStockCount

	//This Behavior Will Return the Value of itemID
	public int getItemID()
	{
		return itemID;
	}//End of getItemID

	//This Behavior Will Return the Value of itemName
	public String getItemName()
	{
		return itemName;
	}//End of getItemName

	//This Behavior Will Return the Value of itemPrice
	public double getItemPrice()
	{
		return itemPrice;
	}//End of getItemPrice

	//This Behavior Will Return the Value of howMany
	public int getHowMany()
	{
		return howMany;
	}//End of getHowMany

	//This Behavior Will Return the Value of discountName
	public String getDiscountName()
	{
		return discountName;
	}//End of getDiscountName

	//This Behavior Will Return the Value of discountRate
	public double getDiscountRate()
	{
		return discountRate;
	}//End of getDiscountRate

	//This Behavior Will Calculate and Return the Discount Amount
	public double getDiscountAmt()
	{
		return discountRate * itemPrice;
	}//End of getDiscountAmt

	//This Behavior Will Calculate and Return the Discount Price
	public double getDiscountPrice()
	{
		return itemPrice - getDiscountAmt();
	}//End of getDiscountPrice

	//This Behavior Will Return the Value of prizeName
	public String getPrizeName()
	{
		return prizeName;
	}//End of prizeName

	//This Behavior Will Calculate and Return the SubTotal
	public double getSubTotal()
	{
		return howMany * getDiscountPrice();
	}//End of getSubTotal

	//This Behavior Will Return the Value of TAX_RATE
	public double getTaxRate()
	{
		return TAX_RATE;
	}//End of getTaxRate

	//This Behavior Will Calculate and Return the Tax Amount
	public double getTaxAmt()
	{
		return getSubTotal() * TAX_RATE;
	}//End of getTaxAmt

	//This Behavior Will Calculate and Return the Total Cost
	public double getTotalCost()
	{
		return getSubTotal() + getTaxAmt();
	}//End of getTotalCost
}//End of Order