//AUTHOR: Zach Buesing
//COURSE: CPT 187
//PURPOSE: Stores and calculates values for order pricing and stores counts of order values, including items, discounts, and prizes. 
//CREATEDATE: 7/22/2021
package edu.cpt187.buesing.exercise6;

//Importing Scanner
import java.util.Scanner;

//Importing FileInputStream
import java.io.FileInputStream;

//Importing IOException
import java.io.IOException;

//Importing Random Generator
import java.util.Random;

//Beginning of Inventory
public class Inventory 
{
	//Declaring and Initializing Class Attributes
	private String[] DISCOUNT_NAMES = {"Subscriber", "Special Offer", "Coupon", "No Discount"};
	private double[] DISCOUNT_RATES = {0.3, 0.2, 0.1, 0};
	private String[] PRIZE_NAMES = {"Tier 1", "Tier 2", "Tier 3"};
	private final int MAX_RECORDS = 25;
	private final int NOT_FOUND = -1;
	private final int ONE = 1;
	private final int RESET_VALUE = 0;
	private int[] itemIDs = new int[25];
	private String[] itemNames = new String[25]; 
	private double[] itemPrices = new double[25]; 
	private int[] orderQuantities = new int[25]; 
	private double[] orderTotals = new double[25];
	private int[] inStockCounts = new int[25]; 
	private int itemSearchIndex = 0;
	private int recordCount = 0;

	private Random prizeGenerator  = new Random();

	//--Constructor

	//Default Constructor
	public Inventory()
	{
	}//End of Default Constructor

	//--Setters

	//This Behavior Will Subtract a borrowed value from inStockCounts at itemSearchIndex
	public void setReduceStock(int borrowedHowMany)
	{
		inStockCounts[itemSearchIndex] = inStockCounts[itemSearchIndex] - borrowedHowMany;
	}//End of setReduceStock

	//This Behavior Will Process File Handling Logic
	public void setLoadItems(String borrowedFileName)
	{
		//Beginning of Try Block
		try
		{
			//Assigning recordCount to reset value
			recordCount = RESET_VALUE;


			//Instantiating Scanner and FileInputStream
			Scanner infile = new Scanner (new FileInputStream(borrowedFileName));

			//Beginning of File Handling While-loop
			while (infile.hasNext() == true && recordCount < MAX_RECORDS)
			{
				//Reading File Record
				itemNames[recordCount] = infile.next();


				//Incrementing Counter
				recordCount++;	
			}//End of While loop

			//Close the Scanner/File
			infile.close();

			//Invoking Bubble Search
			setBubbleSort();
		}//End of try block
		//Beginning of Catch block
		catch (IOException ex)
		{
			//Setting Error With Counter
			recordCount = NOT_FOUND;
		}//End of Catch Block
	}//End of setLoadItems

	//This Behavior Will Process File Handling Logic to Read an Appended File
	public void setLoadItems(String borrowedFileName, int borrowedSize)
	{
		//Beginning of Try Block
		try
		{
			//Assigning recordCount to reset value
			recordCount = RESET_VALUE;

			//Instantiating Scanner and FileInputStream
			Scanner infile = new Scanner (new FileInputStream(borrowedFileName));

			//Beginning of File Handling While-loop
			while (infile.hasNext() == true && recordCount < MAX_RECORDS && recordCount < borrowedSize)
			{
				//Reading File Record
				itemIDs[recordCount] = infile.nextInt();
				itemNames[recordCount] = infile.next();
				itemPrices[recordCount] = infile.nextDouble();
				orderQuantities[recordCount] = infile.nextInt();
				orderTotals[recordCount] = infile.nextDouble();
				inStockCounts[recordCount] = infile.nextInt();

				//Incrementing Counter
				recordCount++;	
			}//End of While loop


			//Close the Scanner/File
			infile.close();

			//Invoking Bubble Search
			setBubbleSort();

		}//End of try block
		//Beginning of Catch block
		catch (IOException ex)
		{
			//Setting Error With Counter
			recordCount = NOT_FOUND;
		}//End of Catch Block
	}//End of setLoadItems

	//This Behavior Will assign a borrowed value to itemSearchedIndex using getBinSearch
	public void setSearchIndex(int borrowedID)
	{
		itemSearchIndex = getBinSearch(borrowedID);
	}//End of setSearchIndex

	//This behavior will Execute Array Sorting Logic
	public void setBubbleSort()
	{
		//Declaring and Initializing Local Variables
		int localLast = 0;
		int localIndex = 0;
		boolean localSwap;

		//Assigning Calculated Value to Local Variable
		localLast = recordCount - ONE;

		//Beginning of Run-While Loop (localLast is greater than RESET_VALUE)
		while(localLast > RESET_VALUE)
		{
			//Assigning localIndex to RESET_VALUE and Assigning a Value of false to localSwap
			localIndex = RESET_VALUE;
			localSwap = false;

			//Beginning of Run-While Loop (localIndex is greater than localLast)
			while(localIndex < localLast)
			{
				//Selection Structure for Comparing itemIDs at localIndex to itemIDs at an Index Value Greater by ONE than localIndex
				if(itemIDs[localIndex] > itemIDs[localIndex + ONE])
				{
					//Setting Swap Array Elements with localIndex
					setSwapArrayElements(localIndex);

					//Assigning a Value of true to localSwap
					localSwap = true;
				}//End of Selection Structure for Comparing itemIDs at localIndex to itemIDs at an Index Value Greater by ONE than localIndex

				//Incrementing localIndex
				localIndex++;
			}//End of Run-While Loop (localIndex is greater than localLast)

			//Selection Structure to Test if localSwap is Assigned a Value of false
			if(localSwap == false)
			{
				//Assigning localLast to RESET_VALUE
				localLast = RESET_VALUE;
			}//End of If In Selection Structure to Test if localSwap is Assigned a Value of false

			//Last Option In Selection Structure to Test if localSwap is Assigned a Value of false
			else
			{
				//Decrementing localLast
				localLast--;
			}//End of Selection Structure to Test if localSwap is Assigned a Value of false
		}//End of Run-While Loop (localLast is greater than RESET_VALUE)
	}//End of setBubbleSort

	//This Behavior Will Swap Array Elements for All Field Arrays
	public void setSwapArrayElements(int borrowedIndex)
	{
		//Declaring and Initializing Local Variables
		int localItemIDs = 0;
		String localItemNames = " ";
		double localItemPrices = 0.0;
		int localOrderQuantities = 0;
		double localOrderTotals = 0.0;
		int localInStockCounts = 0;

		//Swapping Element Values in itemIDs
		localItemIDs = itemIDs[borrowedIndex];
		itemIDs[borrowedIndex] = itemIDs[borrowedIndex + ONE];
		itemIDs[borrowedIndex + ONE] = localItemIDs;

		//Swapping Element Values in itemNames
		localItemNames = itemNames[borrowedIndex];
		itemNames[borrowedIndex] = itemNames[borrowedIndex + ONE];
		itemNames[borrowedIndex + ONE] = localItemNames;

		//Swapping Element Values in itemPrices
		localItemPrices = itemPrices[borrowedIndex];
		itemPrices[borrowedIndex] = itemPrices[borrowedIndex + ONE];
		itemPrices[borrowedIndex + ONE] = localItemPrices;

		//Swapping Element Values in orderQuantities
		localOrderQuantities = orderQuantities[borrowedIndex];
		orderQuantities[borrowedIndex] = orderQuantities[borrowedIndex + ONE];
		orderQuantities[borrowedIndex + ONE] = localOrderQuantities;

		//Swapping Element Values in orderTotals
		localOrderTotals = orderTotals[borrowedIndex];
		orderTotals[borrowedIndex] = orderTotals[borrowedIndex + ONE];
		orderTotals[borrowedIndex + ONE] = localOrderTotals;

		//Swapping Element Values in inStockCounts
		localInStockCounts = inStockCounts[borrowedIndex];
		inStockCounts[borrowedIndex] = inStockCounts[borrowedIndex + ONE];
		inStockCounts[borrowedIndex + ONE] = localInStockCounts;
	}//End of setSwapArray


	//--Getters--

	//This Behavior Will Execute the Binary Search Logic
	public int getBinSearch(int borrowedBorrowedID)
	{
		//Declaring and Initializing Local Variables
		int localFirst = 0;
		int localLast = 0;
		boolean localFound = false;
		int localMid = 0;

		//Beginning of Run-While Loop (localFirst is less than/equal to localLast and localFound has a Value of false)
		while(localFirst <= localLast && localFound == false)
		{
			//Calculating Value of localMid
			localMid = (localFirst + localLast) / 2;

			//Selection Structure to Test itemIDs at localMid
			if(itemIDs[localMid] == borrowedBorrowedID)
			{
				//Assigning a value of true to localFound
				localFound = true;
			}//End of If In Selection Structure to Test itemIDs at localMid

			//Last Option In Selection Structure to Test itemIDs at localMid
			else
			{
				//Selection Structure to Compare itemIds at localMid to borrowedBorrowedID
				if(itemIDs[localMid] < borrowedBorrowedID)
				{
					//Calculating Value of localFirst
					localFirst = localMid + ONE;
				}//End of If In Selection Structure to Compare itemIds at localMid to borrowedBorrowedID

				//Last Option In Selection Structure to Compare itemIds at localMid to borrowedBorrowedID
				else
				{
					//Calculating Value of localLast
					localLast = localMid - ONE;
				}//End of Selection Structure to Compare itemIds at localMid to borrowedBorrowedID
			}//End of Selection Structure to Test itemIDs at localMid
		}//End of Run-While Loop (localFirst is less than/equal to localLast and localFound has a Value of false)

		//Selection Structure to Test if localFound has a Value of false
		if(localFound == false)
		{
			localMid  = NOT_FOUND;
		}//End of Selection Structure to Test if localFound has a Value of false

		return localMid;
	}//End of getBinSearch

	//This Behavior Will Return the Entirety of inStockCounts
	public int[] getInStockCounts()
	{
		return inStockCounts;
	}//End of getInStockCounts

	//This Behavior Will Return the Entirety of itemIDs
	public int[] getItemIDs()
	{
		return itemIDs;
	}//End of getItemIDs

	//This Behavior Will Return the Entirety of itemNames
	public String[] getItemNames()
	{
		return itemNames;
	}//End of itemNames

	//This Behavior Will Return the Entirety of itemPrices
	public double[] getItemPrices()
	{
		return itemPrices;
	}//End of getItemPrices

	//This Behavior Will Return the Entirety of DISCOUNT_NAMES
	public String[] getDiscountNames()
	{
		return DISCOUNT_NAMES;
	}//End of getDiscountNames

	//This Behavior Will Return the Entirety of DISCOUNT_RATES
	public double[] getDiscountRates()
	{
		return DISCOUNT_RATES;
	}//End of getDiscountRates

	//This Behavior Will Return the Entirety of orderQuantities
	public int[] getOrderQuantities()
	{
		return orderQuantities;
	}//End of orderQuantities

	//This Behavior Will Return the Entirety of orderTotals
	public double[] getOrderTotals()
	{
		return orderTotals;
	}//End of getOrderTotals

	//This Behavior Will Return the Entirety of PRIZE_NAMES
	public String[] getPrizeNames()
	{
		return PRIZE_NAMES;
	}//End of getPrizeNames

	//This Behavior Will Generate A Random Value Using the length of PRIZE_NAMES
	public int getRandomNumber()
	{
		return prizeGenerator.nextInt(PRIZE_NAMES.length);
	}//End of getRandomNumber

	//This Behavior Will Return the Value of MAX_RECORDS
	public int getMaxRecords()
	{
		return MAX_RECORDS;
	}//End of getMaxRecords

	//This Behavior Will Return the Value of itemSearchIndex
	public int getItemSearchIndex()
	{
		return itemSearchIndex;
	}//End of getItemSearchIndex

	//This Behavior Will Return the Value of recordCount
	public int getRecordCount()
	{
		return recordCount;
	}//End of getRecordCount

	//This Behavior will Calculate the Grand Total
	public double getGrandTotal()
	{
		//Declaring and Initializing Local Variables
		int localIndex = 0;
		double localGrandTotal = 0;

		//Beginning of Run-While Loop (localIndex is Less Than the length of orderTotals)
		while (localIndex < orderTotals.length)
		{
			//Accumulating Order Totals and Assigning Value to localGrandTotal
			localGrandTotal = localGrandTotal + orderTotals[localIndex];

			//Incrementing localIndex
			localIndex++;
		}//End of Run-While Loop (localIndex is Less Than the length of orderTotals)

		return localGrandTotal;
	}//End of getGrandTotal
}//End of Inventory