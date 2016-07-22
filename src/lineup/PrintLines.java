package lineup;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class PrintLines 
{
	public static void createFinalProduct(TeamObj theList, int maxSalary, int minSalary)
	{

		ArrayList<Stack> skaterStacks = createStacks(theList);
		
		ArrayList<LineUp> allLineups = new ArrayList<LineUp>();
		
		ArrayList<PlayerObject> goalies = topGoalies(theList.getGoalies());
		
		System.out.println("Skater size " + skaterStacks.size() + " goalies size " + goalies.size());
		int firstIndex = 0;
		while(firstIndex < skaterStacks.size())
		{
			int secondIndex = firstIndex + 1;
			while(secondIndex < skaterStacks.size())
			{
				if(skaterStacks.get(firstIndex).dMan.lastName.equals(skaterStacks.get(secondIndex).dMan.lastName))
				{
					secondIndex++;
					continue;
				}
				for(int goalieIndex = 0; goalieIndex < goalies.size(); goalieIndex++)
				{
					if(!goalies.get(goalieIndex).oppTeam.equals(skaterStacks.get(firstIndex).team) && 
							!goalies.get(goalieIndex).oppTeam.equals(skaterStacks.get(secondIndex).team))
					{
						int totalSal = skaterStacks.get(firstIndex).salaryTotal + skaterStacks.get(secondIndex).salaryTotal +
								goalies.get(goalieIndex).salary;
						double totalPoints = skaterStacks.get(firstIndex).fanPoints + skaterStacks.get(secondIndex).fanPoints +
								goalies.get(goalieIndex).fppg;
						if(totalSal <= maxSalary && totalSal >= minSalary)
						{
							allLineups.add(new LineUp(skaterStacks.get(firstIndex), skaterStacks.get(secondIndex),
									goalies.get(goalieIndex), totalSal, totalPoints));
						}
					}
				}
				secondIndex++;
			}
			
			firstIndex++;
		}
		Date d = new Date();
		CSVWriter.writeCSV(allLineups, "CSVLineups" + d.getTime() + ".csv");
	}
	
	private static ArrayList<PlayerObject> topGoalies(ArrayList<PlayerObject> goalies) {
		// TODO Auto-generated method stub
		ArrayList<PlayerObject> topGoal = new ArrayList<PlayerObject>();
		
		int findTop = 0;
		while(findTop < 5)
		{
			PlayerObject top = goalies.get(0);
			for(int i = 0; i < goalies.size(); i++)
			{
				if(goalies.get(i).salary > top.salary)
				{
					top = goalies.get(i);
				}
			}
			goalies.remove(top);
			
			topGoal.add(top);
			findTop++;
		}
		return topGoal;
	}

	public static ArrayList<Stack> createStacks(TeamObj players)
	{
		ArrayList<Stack> allStacks = new ArrayList<Stack>();
		
		for(String teamKey : players.teamPlayList.keySet())
		{
			ArrayList<PlayerObject> teamForwards = players.teamPlayList.get(teamKey).get("forwards");
			ArrayList<PlayerObject> teamDefense = players.teamPlayList.get(teamKey).get("defense");
			//System.out.println("Team forward size " + teamForwards.size() + " Team defense size " + teamDefense.size());
			ArrayList<Stack> forwardStacks = new ArrayList<Stack>();
			forwardStacks.add(new Stack());
			forwardStacks.add(new Stack());
			forwardStacks.add(new Stack());
			for(int playerIndex = 0; playerIndex < teamForwards.size(); playerIndex++)
			{
				switch(teamForwards.get(playerIndex).position)
				{
					case "C": 
						forwardStacks.get(teamForwards.get(playerIndex).line - 1).center = teamForwards.get(playerIndex);
						break;
					case "RW": 
						forwardStacks.get(teamForwards.get(playerIndex).line - 1).rightWing = teamForwards.get(playerIndex);
						break;	
					case "LW": 
						forwardStacks.get(teamForwards.get(playerIndex).line - 1).leftWing = teamForwards.get(playerIndex);
						break;
				}
			}
			
			for(int defenseIndex = 0; defenseIndex < teamDefense.size(); defenseIndex++)
			{
				for(int stackIndex = 0; stackIndex < forwardStacks.size(); stackIndex++)
				{
					try
					{
						int salary = forwardStacks.get(stackIndex).center.salary + forwardStacks.get(stackIndex).leftWing.salary +
								forwardStacks.get(stackIndex).rightWing.salary + teamDefense.get(defenseIndex).salary;
						double fPoints = forwardStacks.get(stackIndex).center.fppg + forwardStacks.get(stackIndex).leftWing.fppg +
								forwardStacks.get(stackIndex).rightWing.fppg + teamDefense.get(defenseIndex).fppg;
						Stack fullStack = new Stack(forwardStacks.get(stackIndex).rightWing, 
								forwardStacks.get(stackIndex).leftWing, forwardStacks.get(stackIndex).center, 
								teamDefense.get(defenseIndex), salary, fPoints,
								forwardStacks.get(stackIndex).team);
						if(!fullStack.center.firstName.equals("") && !fullStack.leftWing.firstName.equals("") &&
								!fullStack.rightWing.firstName.equals("") && !fullStack.dMan.firstName.equals(""))
						{
							allStacks.add(fullStack);
						}
					}
					catch(Exception ex)
					{
						System.out.println("Probably index out of bounds " + ex.getLocalizedMessage());
					}
				}
			}
		}
		
		return allStacks;
	}
	
}
