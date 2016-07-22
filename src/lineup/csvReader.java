package lineup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class csvReader {
	
	public static final int FIRST_NAME_INDEX = 2;
	public static final int LAST_NAME_INDEX = 3;
	public static final int POSITION_INDEX = 1;
	public static final int FPPG_INDEX = 4;
	public static final int SALARY_INDEX = 6;
	public static final int TEAM_INDEX = 8;
	public static final int OPP_INDEX = 9;
	
	public static TeamObj ReadInPlayerCSV(String csvFilePath) throws IOException
	{
		TeamObj fdPlayList = new TeamObj(); 
		
		
		
		final File configFile = new File(csvFilePath);
		
		if (configFile.isDirectory()) 
		{
			throw new FileNotFoundException(csvFilePath + " is a folder not a file.");
	    }			
		
		FileReader fr = new FileReader(configFile);
		BufferedReader textReader = new BufferedReader(fr);

		String currentLine = "";
		currentLine = textReader.readLine();
		while((currentLine = textReader.readLine()) != null)
		{
			PlayerObject playInfo = new PlayerObject();
			
			String fourteenParts[] = currentLine.split(",");

			playInfo.firstName = fourteenParts[FIRST_NAME_INDEX].replace("\"", "");
			playInfo.lastName = fourteenParts[LAST_NAME_INDEX].replace("\"", "");
			playInfo.position = fourteenParts[POSITION_INDEX].replace("\"", "");
			playInfo.team = fourteenParts[TEAM_INDEX].replace("\"", "");
			playInfo.salary = Integer.parseInt(fourteenParts[SALARY_INDEX].replace("\"", ""));
			playInfo.fppg = Double.parseDouble(fourteenParts[FPPG_INDEX].replace("\"", ""));
			playInfo.oppTeam = fourteenParts[OPP_INDEX].replace("\"", "\"");
			String fullPostion = "";
			switch(playInfo.position)
			{
				case "C":	fullPostion = "forwards";
							break;
				case "RW":	fullPostion = "forwards";
							break;
				case "LW":	fullPostion = "forwards";
							break;
				case "G":	fullPostion = "goalies";
							break;
				case "D":	fullPostion = "defense";
							break;
			}
			
			fdPlayList.teamPlayList.get(playInfo.team).get(fullPostion).add(playInfo);
		}
		textReader.close();
		fr.close();
		
		return fdPlayList;
		
	}

}
