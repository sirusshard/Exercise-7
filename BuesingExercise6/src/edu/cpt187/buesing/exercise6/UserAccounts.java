//AUTHOR: Zach Buesing
//COURSE: CPT 187
//PURPOSE: Stores user information, including provided usernames and entered passwords. 
//CREATEDATE: 7/22/2021
package edu.cpt187.buesing.exercise6;

//Importing PrintWriter
import java.io.PrintWriter; 

//Importing FileWriter
import java.io.FileWriter;

//Importing IOException
import java.io.IOException;

//Beginning of UserAccounts
public class UserAccounts 
{
	//Declaring and Initializing Class Attributes
	private final int NOT_FOUND = -1;
	private final int RESET_VALUE = 0;
	private final int MAXIMUM_RECORDS = 50;
	private String[] userNames = new String[50];
	private String[] passwords = new String[50]; 
	private String masterFileName = "";
	private int recordCount = 0;
	private int searchedIndex = 0;

	//--Constructor

	//Non-Default Constructor
	public UserAccounts(String borrowedFileName)
	{
		masterFileName = borrowedFileName;
	}//End of Non-Default Constructor

	//--Setters

	//This Behavior Will Assign RESET_VALUE to userNames and passwords
	public void setUserAccountArrays()
	{
		int localIndex = 0;

		//Beginning of While Loop (localIndex is less than the length of userNames
		while(localIndex < userNames.length)
		{
			userNames[localIndex] = String.valueOf(RESET_VALUE);

			passwords[localIndex] = String.valueOf(RESET_VALUE);
		}//End of While Loop (localIndex is less than the length of userNames


	}//End of setUserAccountArrays

	//This Behavior will Assign The Results of getSeqSearch to searchedIndex
	public void setSearchedIndex(String borrowedUserName)
	{
		searchedIndex = getSeqSearch(borrowedUserName);
	}//End of setSearchedIndex

	//This Behavior will Assign The Results of getSeqSearch to searchedIndex and/or Assign NOT_FOUND to searchedIndex
	public void setSearchedIndex(String borrowedUserName, String borrowedPassword)
	{
		searchedIndex = getSeqSearch(borrowedUserName);

		//Selection Structure to Assign NOT_FOUND to searchedIndex
		if(searchedIndex >= RESET_VALUE && getPasswordMatch(borrowedPassword) == false)
		{
			searchedIndex = NOT_FOUND;
		}//End of Selection Structure to Assign NOT_FOUND to searchedIndex
	}//End of setSearchedIndex

	//This Behavior Will Write The User Name and Password to the Master File
	public void setWriteOneRecord(String borrowedUserName, String borrowedPassword)
	{
		//Beginning of Try Block
		try 
		{
			//Attempting to Open File
			PrintWriter filePW = new PrintWriter (new FileWriter(borrowedUserName, true));

			//Writing Borrowed Values to File
			filePW.printf("%n%s\t%s\t%s", borrowedUserName, borrowedPassword);

			//Closing PrintWriter and File
			filePW.close();
		}//End of Try Block

		//Beginning of Catch Block 
		catch(IOException ex) 
		{
			//Setting fileFoundFalg to false
			searchedIndex = NOT_FOUND;
		}//End of Catch Block
	}//End of setWriteOneRecord

	//--Getters

	//This Behavior Will process search array logic
	public int getSeqSearch(String borrowedBorrowedUserName)
	{
		//Declaring and Initializing local variables
		int localIndex = 0;
		int localFound = NOT_FOUND;

		//Beginning of While Loop (localIndex is greater than recordCount)
		while (localIndex < recordCount)
		{
			//Selection Structure for Comparing Borrowed Value to printSubjects at localIndex
			if (borrowedBorrowedUserName.equalsIgnoreCase(userNames[localIndex]))
			{
				//Assigning localFound to localIndex and assigning localIndex to recordCount
				localFound = localIndex;
				localIndex = recordCount;
			}//End of If In Selection Structure for Comparing Borrowed Value to userNames at localIndex

			//Last Option In Selection Structure for Comparing Borrowed Value to userNames at localIndex
			else
			{
				//Incrementing Local Variable
				localIndex++;
			}//End of Selection Structure for Comparing Borrowed Value to userNames at localIndex
		}//End of While Loop (localIndex is greater than recordCount)

		//Incrementing searchedIndex
		searchedIndex++;

		return localFound;
	}//End of setSeqSearch	

	//This Behavior Will Return A Value Based on the Comparison of passwords at searchedIndex and a Borrowed Value
	public boolean getPasswordMatch(String borrowedBorrowedPassword)
	{

		return passwords[searchedIndex].equals(borrowedBorrowedPassword);
	}//End of getPasswordMatch

	//This Behavior Will Return the Value of masterFileName
	public String getFileName()
	{
		return masterFileName;
	}//End of getFileName

	//This Behavior Will Return the Value of MAXIMUM_RECORDS
	public int getMaximumRecords()
	{
		return MAXIMUM_RECORDS;
	}//End of getMaxRecords

	//This Behavior Will Return the Value of recordCount
	public int getRecordCount()
	{
		return recordCount;
	}//End of getRecordCount

	//This Behavior Will Return the Value of searchedIndex
	public int getSearchedIndex() 
	{
		return searchedIndex;
	}//End of getSearchedIndex
}//End of UserAccounts