package lineup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class CSVWriter {
	
	public static void writeCSV(ArrayList<LineUp> allLineups, String nameOfFile)
	{
		File newFile = new File(nameOfFile);
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		
		try
		{
			fw = new FileWriter(newFile.getAbsoluteFile());
			System.out.println("Path of file " + newFile.getAbsolutePath());
			bw = new BufferedWriter(fw);
			
			String Headers = "'Center One','Leftwing One','Rightwing One','Defense One','Center Two','Leftwing Two',"
					+ "'Rightwing Two','Defense Two','Goalie','Salary Total','Fantasy Point Total'\n";
			bw.write(Headers);
			
			for(int outerIndex = 0; outerIndex < allLineups.size(); outerIndex++)
			{
				LineUp currentLine =  allLineups.get(outerIndex);
				String newRow = currentLine.centerOne.firstName + " " +  currentLine.centerOne.lastName + "," +
						currentLine.leftWingOne.firstName + " " + currentLine.leftWingOne.lastName + "," +
						currentLine.rightWingOne.firstName + " " + currentLine.rightWingOne.lastName + "," +
						currentLine.dManOne.firstName + " " + currentLine.dManOne.lastName + "," +
						currentLine.centerTwo.firstName + " " +  currentLine.centerTwo.lastName + "," +
						currentLine.leftWingTwo.firstName + " " + currentLine.leftWingTwo.lastName + "," +
						currentLine.rightWingTwo.firstName + " " + currentLine.rightWingTwo.lastName + "," +
						currentLine.dManTwo.firstName + " " + currentLine.dManTwo.lastName + "," +
						currentLine.goalie.firstName + " " + currentLine.goalie.lastName + "," +
						currentLine.salaryTotal + "," + currentLine.fanPoints + "\n";
						
				bw.write(newRow);
			}
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		finally
		{
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
