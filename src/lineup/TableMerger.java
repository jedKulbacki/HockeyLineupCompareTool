package lineup;

import java.util.ArrayList;

public class TableMerger {

	public static TeamObj mergeTables(TeamObj fdList, TeamObj dfList)
	{
		TeamObj oneTeam = new TeamObj();
		//oneTeam.setGoalies(dfList.getGoalies());
		ArrayList<PlayerObject> goalies = new ArrayList<PlayerObject>();
		
		for(String teamKey : fdList.teamPlayList.keySet())
		{
			for(String type : fdList.teamPlayList.get(teamKey).keySet())
			{
				ArrayList<PlayerObject> fdPlayerArray = fdList.teamPlayList.get(teamKey).get(type);
				ArrayList<PlayerObject> dfArray = dfList.teamPlayList.get(teamKey).get(type);
				//System.out.println("fd player size " + fdPlayerArray.size() + " df player size " + dfArray.size());
				for(int i = 0; i < fdPlayerArray.size(); i++)
				{
					PlayerObject fdPlayer = fdPlayerArray.get(i);
					for(int j = 0; j < dfArray.size(); j++)
					{
						PlayerObject dfPlayer = dfArray.get(j);
					//	System.out.println("fd pos " + fdPlayer.position + " df pos " +dfPlayer.position);
						//System.out.println(fdPlayer.lastName + " " + dfPlayer.lastName);
						if(fdPlayer.lastName.contains(dfPlayer.lastName) 
								|| dfPlayer.lastName.contains(fdPlayer.lastName))
						{
							if(fdPlayer.firstName.contains(dfPlayer.firstName) 
									|| dfPlayer.firstName.contains(fdPlayer.firstName))
							{
								fdPlayer.line = dfPlayer.line;
								if(fdPlayer.line != 4)
								{
									oneTeam.teamPlayList.get(teamKey).get(type).add(fdPlayer);
									if(type.equals("goalies"))
									{
										goalies.add(fdPlayer);
									}
								}
							}
						}						
					}
				}				
			}
		}
		oneTeam.setGoalies(goalies);
		return oneTeam;
	}
}
