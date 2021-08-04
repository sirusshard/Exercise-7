//AUTHOR: Zach Buesing
//COURSE: CPT 187
//PURPOSE: Selection and calculation of order information, including item and discount selection, prize generation, and creation and tracking of user accounts. 
//		   Order information and item stock counts are tracked and displayed to the user.
//CREATEDATE: 7/22/2021
package edu.cpt187.buesing.exercise6;

//Importing Scanner
import java.util.Scanner;

//Beginning of BuesingMainClass
public class BuesingMainClass 
{
	//Declaring and Initializing Class Constants
	public static final char[] MENU_CHARS = {'A','B', 'Q'};
	public static final String[] MENU_OPTIONS = {"Login","Create an Account", "Quit"};
	public static final char[] FILE_MENU_CHARS = {'A','B', 'R'};
	public static final String[] FILE_MENU_OPTIONS = {"Load Inventory","Create Order", "Return to Main Menu"};
	public static final char[] SUB_MENU_CHARS = {'A', 'B', 'C', 'D'};
	public static final String INVENTORY_FILE_NAME = "MasterInventoryFile.dat";
	public static final String ACCOUNTS_FILE_NAME = "MasterUserFile.dat";

	//Beginning of Main Method
	public static void main(String[] args) 
	{
		//Initializing Scanner
		Scanner input = new Scanner(System.in);	

		//Declaring and Initializing Local Variables
		String userName = "";
		char menuSelection = ' ';

		//Instantiating Order
		Order currentOrder = new Order();

		//Instantiating Inventory
		Inventory currentInventory = new Inventory();

		//Instantiating WriteOrder
		WriteOrder orders = new WriteOrder(INVENTORY_FILE_NAME);

		//Instantiating UserAccounts
		UserAccounts currentUser = new UserAccounts(ACCOUNTS_FILE_NAME);

		//Displaying Welcome Banner
		displayWelcomeBanner();

		//Prime Read of Main Menu
		menuSelection = validateMenuSelection(input);

		//Beginning of Run-While loop (Not Quit)
		while (menuSelection != 'Q')
		{
			//Setting User Account Arrays
			currentUser.setUserAccountArrays(); 

			//Getting Username
			userName = getUserName(input);

			//Selection Structure to Test menuSelection
			if(menuSelection == 'A')
			{
				//Setting Searched Index with userName
				currentUser.setSearchedIndex(userName);

				//Selection Structure to Test getSearchedIndex
				if(currentUser.getSearchedIndex() >= 0)
				{
					//Displaying the Account Results For Duplicate Username Entries
					displayAccountResults(userName);
				}//End of If In Selection Structure to Test getSearchedIndex

				//Last Option In Selection Structure to Test getSearchedIndex
				else
				{
					//Setting Write One Record with userName and getPassword Input
					currentUser.setWriteOneRecord(userName, getPassword(input));

					//Displaying Account Results For New Users
					displayAccountResults();
				}//End of Selection Structure to Test getSearchedIndex
			}//End of If In Selection Structure to Test menuSelection

			//Last Option In Selection Structure to Test menuSelection
			else
			{
				//Setting Searched Index with userName and getPassword Input
				currentUser.setSearchedIndex(userName, getPassword(input));	

				//Selection Structure to Test getSearchedIndex
				if(currentUser.getSearchedIndex() >= 0)
				{
					//Displaying Login Error
					displayLoginError();
				}//End of If In Selection Structure to Test getSearchedIndex

				//Last Option in Selection Structure to Test getSearchedIndex
				else
				{
					//Prime Read of File Menu
					menuSelection = validateFileSelection(input);

					//Beginning of Run-While loop (Not Return)
					while (menuSelection != 'R')
					{
						//Selection Structure to Test MenuSelection
						if(menuSelection == 'A')
						{
							//Setting Load Items with getFileName input
							currentInventory.setLoadItems(getFileName(input));

							//Selection Structure to Test getRecordCount
							if(currentInventory.getRecordCount() <= 0)
							{
								//Displaying File Error
								displayFileError();
							}//End of If In Selection Structure to Test getRecordCount

							//Last Option In Selection Structure to Test getRecordCount
							else
							{
								//Displaying Record Report
								displayRecordReport(currentInventory.getRecordCount());
							}//End of Selection Structure to Test getRecordCount
						}//End of If In Selection Structure to Test MenuSelection

						//Last Option In Selection Structure to Test MenuSelection
						else
						{
							//Setting Search Index with validateSearchValue Input
							currentInventory.setSearchIndex(validateSearchValue(input));

							//Selection Structure to Test getItemSearchIndex
							if(currentInventory.getItemSearchIndex() < 0)
							{
								//Displaying Not Found Error
								displayNotFound();
							}//End of If In Selection Structure to Test getItemSearchIndex

							//Last Option In Selection Structure to Test getItemSearchIndex
							else
							{
								//Setting Last Item Selected Index with getItemSearchIndex
								currentOrder.setLastItemSelectedIndex(currentInventory.getItemSearchIndex());

								//Setting Item ID with getItemIDs
								currentOrder.setItemID(currentInventory.getItemIDs());

								//Setting Item Price with getItemPrices
								currentOrder.setItemPrice(currentInventory.getItemPrices());

								//Setting Item Name with getItemNames
								currentOrder.setItemName(currentInventory.getItemNames());

								//Setting How Many with validateHowMany Input
								currentOrder.setHowMany(validateHowMany(input)); 

								//Selection Structure to Compare getInStockCount to getHowMany
								if(currentOrder.getInStockCount(currentInventory.getInStockCounts()) < currentOrder.getHowMany())
								{
									//Displaying Out Of Stock Error
									displayOutOfStock();
								}//End of If In Selection Structure to Compare getInStockCount to getHowMany

								//Last Option In Selection Structure to Compare getInStockCount to getHowMany
								else
								{
									//Setting Discount Type With ValidateDiscountMenu Input, getDiscountNAmes, getDiscountRates
									currentOrder.setDiscountType (validateDiscountMenu(input, currentInventory.getDiscountNames(), currentInventory.getDiscountRates()));

									//Setting Discount Name with getDiscountNames
									currentOrder.setDiscountName(currentInventory.getDiscountNames());

									//Setting Discount Rate With getDiscountRates
									currentOrder.setDiscountRate(currentInventory.getDiscountRates());

									//Setting Decrease In Stock
									currentOrder.setDecreaseInStock(currentInventory);

									//Setting Prize Name with getPrizeNames and getRandomNumber
									currentOrder.setPrizeName(currentInventory.getPrizeNames(), currentInventory.getRandomNumber());

									//Setting Write Order with getItemID, getItemName, getItemPrice, getHowMany, getTotalCost
									orders.setWriteOrder (currentOrder.getItemID(), currentOrder.getItemName(), currentOrder.getItemPrice(), currentOrder.getHowMany(), currentOrder.getTotalCost());

									//Selection Structure to Test getDiscountRate
									if(currentOrder.getDiscountRate() > 0.0)
									{
										//Displaying Order Report With Discount Information
										displayOrderReport(userName, currentOrder.getItemName(), currentOrder.getItemPrice(), currentOrder.getHowMany(), currentOrder.getDiscountName(), currentOrder.getDiscountRate(), currentOrder.getDiscountAmt(), currentOrder.getDiscountPrice(), currentOrder.getSubTotal(), currentOrder.getTaxRate(), currentOrder.getTaxAmt(), currentOrder.getTotalCost(), currentOrder.getPrizeName(), currentOrder.getInStockCount( currentInventory.getInStockCounts()));
									}//End of If In Selection Structure to Test getDiscountRate

									//Last Option In Selection Structure to Test getDiscountRate
									else
									{
										//Displaying Order Report Without Discount Information
										displayOrderReport(userName, currentOrder.getItemName(), currentOrder.getItemPrice(), currentOrder.getHowMany(), currentOrder.getSubTotal(), currentOrder.getTaxRate(), currentOrder.getTaxAmt(), currentOrder.getTotalCost(), currentOrder.getPrizeName(), currentOrder.getInStockCount(currentInventory.getInStockCounts()));
									}//End of Selection Structure to Test getDiscountRate
								}//End of Selection Structure to Compare getInStockCount to getHowMany
							}//End of Selection Structure to Test getItemSearchIndex
						}//End of Selection Structure to Test MenuSelection

						//Update Read of File Menu
						menuSelection = validateFileSelection(input);
					}//End of Run-While loop (Not Return)
				}//End of Selection Structure to Test getSearchedIndex
			}//End Of Selection Structure to Test menuSelection

			//Update Read of Main Menu
			menuSelection = validateMenuSelection(input);
		}//End of Run-While loop (Not Quit)

		//Setting Load Items with getFileName and getRecordCount
		currentInventory.setLoadItems(orders.getFileName(), orders.getRecordCount());

		//Selection Structure to test getRecordCount
		if(orders.getRecordCount() > 0)
		{
			//Displaying Final Report With getItemIDs, getItemNames, getItemPrices, getOrderQuantities, getOrderTotals, getRecordCount, getGrandTotal
			displayFinalReport(currentInventory.getItemIDs(), currentInventory.getItemNames(), currentInventory.getItemPrices(), currentInventory.getOrderQuantities(), currentInventory.getOrderTotals(), currentInventory.getRecordCount(), currentInventory.getGrandTotal());
		}//End of Selection Structure to test getRecordCount

		//Displaying Farewell Message
		displayFarewellMessage();

		//Closing Scanner
		input.close();
	}//End of Main Method

	//--Voids--
	//Method to Display Welcome Banner
	public static void displayWelcomeBanner()
	{
		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.printf("%-50s\n","Welcome to the order calculater and item browser.");
		System.out.printf("%-50s\n","You can create an account and search item listings.");
		System.out.printf("%-40s\n","Please enter information when prompted.");
		System.out.printf("%60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}//End of displayWelcomeBanner

	//Farewell Message
	public static void displayFarewellMessage()
	{
		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.printf("%-30s\n", "Thank you for your participation.");
		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}//End of displayFarewellMessage

	//This Behavior will Display the Main Menu
	public static void displayMainMenu()
	{
		//Declaring and Initializing Local Variable
		int localIndex = 0;

		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("MAIN MENU");
		//Run While Loop For Displaying Entire Array
		while (localIndex < MENU_OPTIONS.length)
		{
			System.out.printf("%-60s\n", MENU_CHARS[localIndex] + " for " + MENU_OPTIONS[localIndex]);

			localIndex++;
		}//End Of Run While Loop For Displaying Entire Array

		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.printf("%-30s\n","Enter your selection here:");
	}//End of displayMainMenu

	//This Behavior will Display the File Menu
	public static void displayFileMenu()
	{
		//Declaring and Initializing Local Variable
		int localIndex = 0;

		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("FILE MENU");
		//Run While Loop For Displaying Entire Array
		while (localIndex < FILE_MENU_OPTIONS.length)
		{
			System.out.printf("%-60s\n", FILE_MENU_CHARS[localIndex] + " for " + FILE_MENU_OPTIONS[localIndex]);

			localIndex++;
		}//End Of Run While Loop For Displaying Entire Array

		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.printf("%-30s\n","Enter your selection here:");
	}//End of displayFileMenu

	//This Behavior will Display the Discount Menu
	public static void displayDiscountMenu(String[] borrowedDiscountNames, double[] borrowedDiscountRates)
	{
		//Declaring and Initializing Local Variable
		int localIndex = 0;

		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("DISCOUNT MENU");
		//Run While Loop For Displaying Entire Array
		while (localIndex < SUB_MENU_CHARS.length)
		{
			System.out.printf("%-30s%3f.2%1s\n", SUB_MENU_CHARS[localIndex] + borrowedDiscountNames[localIndex], (borrowedDiscountRates[localIndex]*100), "%" );

			localIndex++;
		}//End Of Run While Loop For Displaying Entire Array

		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.printf("%-30s\n","Enter your selection here:");
	}//End of displayDiscountMenu

	//This Behavior will Display the Account Results for New Users
	public static void displayAccountResults()
	{
		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.printf("%-20s\n", "ACCOUNT RESULTS");
		System.out.printf("%-20s\n", "New account created");
		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}//End of displayAccountResults

	//This Behavior will Display the Account Results For Duplicate Username Entries
	public static void displayAccountResults(String userName)
	{
		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.printf("%-20s\n", "ACCOUNT RESULTS");
		System.out.printf("%-20s\n", "username, " + userName +  ", already exists");
		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}//End of displayAccountResults

	//This Behavior will Display the Login Error 
	public static void displayLoginError()
	{
		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.printf("%-20s\n", "LOGIN ERROR");
		System.out.printf("%-20s\n", "Username and/or Password is incorrect");
		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}//End of displayLoginError

	//This Behavior will Display the Record Report
	public static void displayRecordReport(int recordCount)
	{
		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.printf("%-20s\n", "RECORD REPORT");
		System.out.printf("%-20s\n", recordCount + " records processed");
		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}//End of displayRecordReport

	//This Behavior will Display the Out Of Stock Error
	public static void displayOutOfStock()
	{
		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.printf("%-50s\n", "OUT OF STOCK ERROR");
		System.out.printf("%-50s\n", "The quantity entered is greater than the quantity in-stock");
		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}//End Of displayOutOfStock

	//This Behavior will Display the File Opening Failure Error
	public static void displayFileError()
	{
		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.printf("%-50s\n", "FILE ERROR");
		System.out.printf("%-50s\n", "The file was not found or could not be opened");
		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}//End Of displayNotOpen

	//This Behavior will Display the File Not Found Error
	public static void displayNotFound()
	{
		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.printf("%-50s\n", "NOT FOUND ERROR");
		System.out.printf("%-50s\n", "The search value entered was not found");
		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}//End Of displayNotFound

	//This Behavior will Display the Search Prompt
	public static void displaySearchPrompt()
	{
		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.printf("%-50s\n", "Enter the Search Value:");
	}//End of displaySearchPrompt

	//This Behavior Will Display the Order Report Without Discount Information
	public static void displayOrderReport(String userName, String borrowedItemName, double borrowedItemPrice,  int borrowedHowMany, double borrowedSubTotal, double borrowedTaxRate, double borrowedTaxAmt, double borrowedOrderTotal, String borrowedPrizeName, int borrowedInStockCounts)
	{
		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.printf("%-20s\n", "ORDER REPORT");
		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.printf("%-20s%-3s%7s\n", "Customer Name"," ",  userName);
		System.out.printf("%-60s\n", "");	
		System.out.printf("%-20s%-3s%7s\n", "Item Name"," ",  borrowedItemName);
		System.out.printf("%-20s%-3s%7d\n", "Item Price", " ",  borrowedItemPrice);
		System.out.printf("%-60s\n", "");	
		System.out.printf("%-20s%-3s%7d\n", "Quantity", " ",  borrowedHowMany);
		System.out.printf("%-60s\n", "");	
		System.out.printf("%-20s%-3s%7s\n", "SubTotal"," ",  borrowedSubTotal);
		System.out.printf("%-20s%-3s%7s\n", "Tax Rate"," ",  borrowedTaxRate);
		System.out.printf("%-20s%-3s%7s\n", "Tax Amount"," ",  borrowedTaxAmt);
		System.out.printf("%-60s\n", "");	
		System.out.printf("%-20s%-3s%7s\n", "Order Total"," ",  borrowedOrderTotal);
		System.out.printf("%-60s\n", "");	
		System.out.printf("%-20s%-3s%7d\n", "Prize", " ",  borrowedPrizeName);
		System.out.printf("%-60s\n", "");	
		System.out.printf("%-60s\n", "Buy more now: Only " + borrowedInStockCounts +"left in stock!");
		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}//End of displayOrderReport

	//This Behavior Will Display the Order Report With Discount Information
	public static void displayOrderReport(String userName, String borrowedItemName, double borrowedItemPrice,int borrowedHowMany, String borrowedDiscountName, double borrowedDiscountRate, double borrowedDiscountAmount, double borrowedDiscountPrice,  double borrowedSubTotal, double borrowedTaxRate, double borrowedTaxAmt, double borrowedOrderTotal, String borrowedPrizeName, int borrowedInStockCounts)
	{
		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.printf("%-20s\n", "ORDER REPORT");
		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.printf("%-20s%-3s%7s\n", "Customer Name"," ",  userName);
		System.out.printf("%-60s\n", "");	
		System.out.printf("%-20s%-3s%7s\n", "Item Name","",  borrowedItemName);
		System.out.printf("%-20s%4s%7.2f\n", "Item Price", "$",  borrowedItemPrice);
		System.out.printf("%-60s\n", "");	
		System.out.printf("%-20s%-3s%7s\n", "Discount Name","",  borrowedDiscountName);
		System.out.printf("%-23s%-3s%6s\n", "Discount Rate", "$",  (borrowedDiscountRate*100) + " %");
		System.out.printf("%-20s%4s%7.2f\n", "Discount Amount","$",  borrowedDiscountAmount);
		System.out.printf("%-20s%4s%7.2f\n", "Discount Price", "$",  borrowedDiscountPrice);
		System.out.printf("%-60s\n", "");	
		System.out.printf("%-20s%-3s%5d\n", "Quantity", " ",  borrowedHowMany);
		System.out.printf("%-60s\n", "");	
		System.out.printf("%-20s%4s%7.2f\n", "SubTotal","$",  borrowedSubTotal);
		System.out.printf("%-20s%-3s%9s\n", "Tax Rate"," ",  (borrowedTaxRate*100) + " %");
		System.out.printf("%-20s%4s%7.2f\n", "Tax Amount","$",  borrowedTaxAmt);
		System.out.printf("%-60s\n", "");	
		System.out.printf("%-20s%4s%7.2f\n", "Order Total","$",  borrowedOrderTotal);
		System.out.printf("%-60s\n", "");	
		System.out.printf("%-20s%-3s%7s\n", "Prize", "",  borrowedPrizeName);
		System.out.printf("%-60s\n", "");	
		System.out.printf("%-60s\n", "Buy more now: Only " + borrowedInStockCounts +"left in stock!");
		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}//End of displayOrderReport

	//This Behavior Will Display the Final Report
	public static void displayFinalReport(int[] borrowedItemIDs, String[] borrowedItemNames, double[] borrowedItemPrices, int[] borrowedOrderQuantities, double[] borrowedOrderTotals, int borrowedRecordCount, double borrowedGrandTotal)
	{
		//Declaring and Initializing Local Variable
		int localIndex = 0;

		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.printf("%-20s\n", "FINAL REPORT");
		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.printf("%10s%10s%2s%10s%10s%2s%10s\n", "ID", "NAME"," ", "PRICE", "QTY", " ", "TOTAL");

		//Run While Loop For Displaying Entire Array
		while (localIndex < SUB_MENU_CHARS.length)
		{
			System.out.printf("%10d%10s%2s%10f.2%10d%2s%10f.2\n", borrowedItemIDs[localIndex], borrowedItemNames[localIndex],"$", borrowedItemPrices[localIndex], borrowedOrderQuantities[localIndex], "$", borrowedOrderTotals[localIndex]);

			localIndex++;
		}//End Of Run While Loop For Displaying Entire Array

		System.out.printf("%-60s\n", "GRAND TOTAL");
		System.out.printf("%-60f.2\n", borrowedGrandTotal);
	}//End of displayFinalReport

	//--VRs--

	//This Behavior will Get the User's Name
	public static String getUserName(Scanner borrowedInput)
	{
		//Declaring and Initializing local variable
		String localUserName = "";

		//Displaying Input Prompt
		System.out.println("Enter your username:");

		//Accepting User Input
		localUserName = borrowedInput.nextLine();

		return localUserName;
	}//End of getUserName

	//This Behavior will Get the Password
	public static String getPassword(Scanner borrowedInput)
	{
		//Declaring and Initializing local variable
		String localPassword = "";

		//Displaying Input Prompt
		System.out.println("Enter your password:");

		//Accepting User Input
		localPassword = borrowedInput.nextLine();

		return localPassword;
	}//End of getPassword

	//This Behavior will Validate the Item Quantity
	public static String validateHowMany(Scanner borrowedinput)
	{
		//Declaring and Initializing Local Variable
		int localHowMany = 0;

		//Displaying Input Prompt
		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Enter the amount of items:");

		//Prime Read
		localHowMany = borrowedinput.nextInt();

		//Validation Loop
		while(localHowMany <= 0)
		{
			//Displaying Error Message
			System.out.println("~~ERROR: Invalid Quantity.~~~");

			//Displaying Input Prompt
			System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Enter the amount of items:");

			//Update Read
			localHowMany = borrowedinput.nextInt();
		}//End of Validation Loop
		return String.valueOf(localHowMany);
	}//End of validateHowMany

	//This Behavior Will Get The Search Value
	public static int validateSearchValue(Scanner borrowedInput)
	{
		//Declaring and Initializing local variables
		String localSearchValue = "";

		//Prompt for Search Value Input
		displaySearchPrompt();

		localSearchValue = borrowedInput.next();
		return Integer.valueOf(localSearchValue);
	}//End of getSearchValue

	//This Behavior Will Get The File Name
	public static String getFileName(Scanner borrowedInput)
	{
		//Declaring and Initializing local variables
		String localFileName = "";

		//Prompt for Inputting File Name
		System.out.printf("%-60s\n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.printf("%-50s\n", "Enter the file name with extension (i.e. file.txt):");

		localFileName = borrowedInput.next();
		return localFileName;
	}//End of getFileName

	//This Behavior Will Validate the Main Menu Selection
	public static char validateMenuSelection(Scanner borrowedinput)
	{
		//Declaring and Initializing local variables
		char localSelection = ' ';

		//Displaying Menu
		displayMainMenu();

		//Prime Read
		localSelection = borrowedinput.next().toUpperCase().charAt(0);

		//Beginning of Validation Run-While
		while(localSelection!= 'A'&& localSelection!= 'B'&& localSelection!= 'Q') 
		{
			//Displaying Error
			System.out.println("~~ERROR: Invalid Selection.~~~");

			//Displaying Menu
			displayMainMenu();

			//Update Read
			localSelection = borrowedinput.next().toUpperCase().charAt(0);
		}//End of Validation Loop
		return localSelection;
	}//End of validateMainMenu

	//This Behavior Will Validate the Main Menu Selection
	public static char validateFileSelection(Scanner borrowedinput)
	{
		//Declaring and Initializing local variables
		char localSelection = ' ';

		//Displaying File Menu
		displayFileMenu();

		//Prime Read
		localSelection = borrowedinput.next().toUpperCase().charAt(0);

		//Beginning of Validation Run-While
		while(localSelection!= 'A'&& localSelection!= 'B'&& localSelection!= 'R') 
		{
			//Displaying Error
			System.out.println("~~ERROR: Invalid Selection.~~~");

			//Displaying File Menu
			displayFileMenu();

			//Update Read
			localSelection = borrowedinput.next().toUpperCase().charAt(0);
		}//End of Validation Loop
		return localSelection;
	}//End of validateFileMenu

	//This Behavior Will Validate the Discount Selection
	public static char validateDiscountMenu(Scanner borrowedinput, String[] borrowedDiscountNames, double[] borrowedDiscountRates)
	{
		//Declaring and Initializing local variables
		char localSelection = ' ';

		//Displaying Menu
		displayDiscountMenu(borrowedDiscountNames, borrowedDiscountRates);

		//Prime Read
		localSelection = borrowedinput.next().toUpperCase().charAt(0);

		//Beginning of Validation Run-While
		while(localSelection!= 'A'&& localSelection!= 'B'&& localSelection!= 'C'&& localSelection!= 'D') 
		{
			//Displaying Error
			System.out.println("~~ERROR: Invalid Selection.~~~");

			//Displaying Menu
			displayDiscountMenu(borrowedDiscountNames, borrowedDiscountRates);

			//Update Read
			localSelection = borrowedinput.next().toUpperCase().charAt(0);
		}//End of Validation Loop
		return localSelection;
	}//End of validateDiscountMenu
}//End of BuesingMainClass