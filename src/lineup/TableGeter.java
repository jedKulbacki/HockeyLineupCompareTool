package lineup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TableGeter 
{
	private String baseHTML = "http://www2.dailyfaceoff.com/teams/lines/";
	private int startIndex = 13;
	private int endIndex = 42;
	private String defaultCSV = "";
	private HashMap<String, String> teamNameTable;
	public TableGeter()
	{
		defaultCSV = System.getProperty("user.home") + "/Desktop/currentLinups.csv";
		
		teamNameTable = new HashMap<String, String>();
		teamNameTable.put("Anaheim Ducks", "AHA");
		teamNameTable.put("Calgary Flames", "CGY");
		teamNameTable.put("New York Rangers", "NYR");
		teamNameTable.put("Chicago Blackhawks", "CHI");
		teamNameTable.put("Los Angeles Kings", "LA");
		teamNameTable.put("Winnipeg Jets", "WPG");
		teamNameTable.put("Boston Bruins", "BOS");
		teamNameTable.put("Arizona Coyotes", "ARI");
		teamNameTable.put("Buffalo Sabres", "BUF");
		teamNameTable.put("Carolina Hurricanes", "CAR");
		teamNameTable.put("Colorado Avalanche", "COL");
		teamNameTable.put("Columbus Blue Jackets", "CLS");
		teamNameTable.put("Dallas Stars", "DAL");
		teamNameTable.put("Detroit Red Wings", "DET");
		teamNameTable.put("Edmonton Oilers", "EDM");
		teamNameTable.put("Florida Panthers", "FLA");
		teamNameTable.put("Minnesota Wild", "MIN");
		teamNameTable.put("Montreal Canadiens", "MON");
		teamNameTable.put("Nashville Predators", "NSH");
		teamNameTable.put("New Jersey Devils", "NJ");
		teamNameTable.put("New York Islanders", "NYI");
		teamNameTable.put("Ottawa Senators", "OTT");
		teamNameTable.put("Philadelphia Flyers", "PHI");
		teamNameTable.put("Pittsburgh Penguins", "PIT");
		teamNameTable.put("San Jose Sharks", "SJ");
		teamNameTable.put("St. Louis Blues", "STL");
		teamNameTable.put("Tampa Bay Lightning", "TB");
		teamNameTable.put("Toronto Maple Leafs", "TOR");
		teamNameTable.put("Vancouver Canucks", "VAN");
		teamNameTable.put("Washington Capitals", "WAS");
	}
	
	public TeamObj getNewestLineups()
	{
		int currentIndex = startIndex;
		
		ArrayList<PlayerObject> allGoalies = new ArrayList<PlayerObject>(); 
		TeamObj allPlayers = new TeamObj();
		
		
	/*	ArrayList<String> headers = new ArrayList<String>();
		headers.add("Name");
		headers.add("Team");
		headers.add("Position");
		headers.add("Line");
		headers.add("TeamLine");
		
		allForwards.add(headers);	*/	
		
		while(currentIndex <= endIndex)
		{
			String teamHTML = baseHTML + currentIndex + "/";	
			try 
			{
				Document doc = Jsoup.connect(teamHTML).get();
				String teamName = doc.getElementById("newTitle").text();
				teamName = teamName.replace(" Line Combinations" , "");
				teamName = teamNameTable.get(teamName);
				//teamName = teamName.replace(" " , "");
				//System.out.println("Team name " + teamName);
				Element forwardTable = doc.getElementById("forwards");
				
				Elements forwardLines = forwardTable.select(":not(thead) tr");
				
				for(int lineIndex = 0; lineIndex < forwardLines.size(); lineIndex++)
				{
					Element currentLine = forwardLines.get(lineIndex);
					Elements currentForwards = currentLine.select("td"); 
					for(int j = 0; j < currentForwards.size(); j++)
					{
						//System.out.println("id of forward " + currentForwards.get(j).id());
						//System.out.println("name of player " + currentForwards.get(j).child(0).text());
						String positionID = currentForwards.get(j).id();
						String pos = "";
						String line = "";
						if(positionID.contains("C"))
						{
							pos = positionID.substring(0, 1);
							line = positionID.substring(1);
						}
						else
						{
							pos = positionID.substring(0, 2);
							line = positionID.substring(2);
						}
							
						PlayerObject player = new PlayerObject();
						
						String playerName = "";
						try
						{
							playerName = currentForwards.get(j).child(0).text();
						}
						catch(Exception ex)
						{
							continue;
						}
						String firstName = playerName.substring(0, playerName.indexOf(' '));
						String lastName = playerName.substring(playerName.indexOf(' ') + 1);
						player.firstName = firstName;
						player.lastName = lastName;
						player.team = teamName;
						player.position = pos;
						player.line = Integer.parseInt(line);
						
						allPlayers.teamPlayList.get(teamName).get("forwards").add(player);
					}
				}
				
				Element defenseTable = doc.getElementById("defense");
				
				Elements defenseLines = defenseTable.select(":not(thead) tr");
				
				for(int lineIndex = 0; lineIndex < defenseLines.size(); lineIndex++)
				{
					Element currentLine = defenseLines.get(lineIndex);
					Elements currentdefense = currentLine.select("td"); 
					for(int j = 0; j < currentdefense.size(); j++)
					{
						//System.out.println("id of forward " + currentForwards.get(j).id());
						//System.out.println("name of player " + currentForwards.get(j).child(0).text());
						String positionID = currentdefense.get(j).id();
						String pos = "";
						String line = "";
						
						pos = positionID.substring(1, 2);
						line = positionID.substring(2);
					
						PlayerObject player = new PlayerObject();
						
						String playerName = currentdefense.get(j).child(0).text();
						String firstName = playerName.substring(0, playerName.indexOf(' '));
						String lastName = playerName.substring(playerName.indexOf(' ') + 1);
						
						player.firstName = firstName;
						player.lastName = lastName;
						player.team = teamName;
						player.position = pos;
						 Integer.parseInt(line);
						
						
						allPlayers.teamPlayList.get(teamName).get("defense").add(player);
						
						
					}
				}
				
				Element goalieTable = doc.getElementById("goalie_list");
				
				Elements goalieLines = goalieTable.select(":not(thead) tr");
				
				for(int lineIndex = 0; lineIndex < goalieLines.size(); lineIndex++)
				{
					Element currentLine = goalieLines.get(lineIndex);
					Elements currentdefense = currentLine.select("td"); 
					for(int j = 0; j < currentdefense.size(); j++)
					{
						//System.out.println("id of forward " + currentForwards.get(j).id());
						//System.out.println("name of player " + currentForwards.get(j).child(0).text());
						String positionID = currentdefense.get(j).id();
						String pos = "";
						String line = "";
						
						pos = positionID.substring(0, 1);
						line = positionID.substring(1);
					
						PlayerObject player = new PlayerObject();
						
						String playerName = currentdefense.get(j).child(0).text();
						String firstName = playerName.substring(0, playerName.indexOf(' '));
						String lastName = playerName.substring(playerName.indexOf(' ') + 1);
						
						player.firstName = firstName;
						player.lastName = lastName;
						player.team = teamName;
						player.position = pos;
						 Integer.parseInt(line);
						
						
						allPlayers.teamPlayList.get(teamName).get("goalies").add(player);
						
						allGoalies.add(player);
					}
				}
				
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			currentIndex++;
		}
		
		System.out.println("File is done");	
		allPlayers.setGoalies(allGoalies);
		return allPlayers;
	}
}
