//AUTHOR: Zach Buesing
//COURSE: CPT 187
//PURPOSE: Storing changes to chosen items to ensure stock accuracy. 
//CREATEDATE: 7/22/2021
package edu.cpt187.buesing.exercise6;

//Importing PrintWriter
import java.io.PrintWriter; 

//Importing FileWriter
import java.io.FileWriter;

//Importing IOException
import java.io.IOException;

//Beginning of WriteOrder
public class WriteOrder 
{
	//Declaring and Initializing Class Attributes
	private boolean fileFoundFlag = false;
	private String masterFileName = "";
	private int recordCount = 0;

	//--Constructor

	//Non-Default Constructor
	public WriteOrder(String borrowedFileName)
	{
		masterFileName = borrowedFileName;
	}//End of Non-Default Constructor

	//--Setters

	//This Behavior Will Process File Handling (Writing) Logic
	public void setWriteOrder(int borrowedItemID, String borrowedItemName, double borrowedItemPrice, int borrowedQuantity, double borrowedOrderCost)
	{
		//Beginning of Try Block
		try 
		{
			//Attempting to Open File
			PrintWriter filePW = new PrintWriter (new FileWriter(borrowedItemName, true));

			//Writing Borrowed Values to File
			filePW.printf("%n%s\t%s\t%s\t%s", borrowedItemID, borrowedItemPrice, borrowedQuantity, borrowedOrderCost);

			//Closing PrintWriter and File
			filePW.close();
		}//End of Try Block

		//Beginning of Catch Block 
		catch(IOException ex) 
		{
			//Setting fileFoundFalg to false
			fileFoundFlag = false;
		}//End of Catch Block
	}//End of setWriteOrder

	//--Getters

	//This Behavior Will Return the Value of fileFoundFlag
	public boolean getFileFoundFlag()
	{
		return fileFoundFlag;
	}//End of getFileFoundFlag

	//This Behavior Will Return the Value of masterFileName
	public String getFileName()
	{
		return masterFileName;
	}//End of getFileName

	//This Behavior Will Return the Value of recordCount
	public int getRecordCount()
	{
		return recordCount;
	}//End of getRecordCount
}//End of WriteOrder