package lineup;

import java.util.ArrayList;
import java.util.HashMap;

public class TeamObj 
{
	public HashMap<String, HashMap<String, ArrayList<PlayerObject>>> teamPlayList;
	private ArrayList<PlayerObject> goalies;
	
	public TeamObj()
	{
		teamPlayList = new HashMap<String, HashMap<String, ArrayList<PlayerObject>>>();
		teamPlayList.put("AHA", new HashMap<String, ArrayList<PlayerObject>>());
		teamPlayList.put("CGY", new HashMap<String, ArrayList<PlayerObject>>());
		teamPlayList.put("NYR", new HashMap<String, ArrayList<PlayerObject>>());
		teamPlayList.put("CHI", new HashMap<String, ArrayList<PlayerObject>>());
		teamPlayList.put("LA", new HashMap<String, ArrayList<PlayerObject>>());
		teamPlayList.put("WPG", new HashMap<String, ArrayList<PlayerObject>>());
		teamPlayList.put("BOS", new HashMap<String, ArrayList<PlayerObject>>());
		teamPlayList.put("ARI", new HashMap<String, ArrayList<PlayerObject>>());
		teamPlayList.put("BUF", new HashMap<String, ArrayList<PlayerObject>>());
		teamPlayList.put("CAR", new HashMap<String, ArrayList<PlayerObject>>());
		teamPlayList.put("COL", new HashMap<String, ArrayList<PlayerObject>>());
		teamPlayList.put("CLS", new HashMap<String, ArrayList<PlayerObject>>());
		teamPlayList.put("DAL", new HashMap<String, ArrayList<PlayerObject>>());
		teamPlayList.put("DET", new HashMap<String, ArrayList<PlayerObject>>());
		teamPlayList.put("EDM", new HashMap<String, ArrayList<PlayerObject>>());
		teamPlayList.put("FLA", new HashMap<String, ArrayList<PlayerObject>>());
		teamPlayList.put("MIN", new HashMap<String, ArrayList<PlayerObject>>());
		teamPlayList.put("MON", new HashMap<String, ArrayList<PlayerObject>>());
		teamPlayList.put("NSH", new HashMap<String, ArrayList<PlayerObject>>());
		teamPlayList.put("NJ", new HashMap<String, ArrayList<PlayerObject>>());
		teamPlayList.put("NYI", new HashMap<String, ArrayList<PlayerObject>>());
		teamPlayList.put("OTT", new HashMap<String, ArrayList<PlayerObject>>());
		teamPlayList.put("PHI", new HashMap<String, ArrayList<PlayerObject>>());
		teamPlayList.put("PIT", new HashMap<String, ArrayList<PlayerObject>>());
		teamPlayList.put("SJ", new HashMap<String, ArrayList<PlayerObject>>());
		teamPlayList.put("STL", new HashMap<String, ArrayList<PlayerObject>>());
		teamPlayList.put("TB", new HashMap<String, ArrayList<PlayerObject>>());
		teamPlayList.put("TOR", new HashMap<String, ArrayList<PlayerObject>>());
		teamPlayList.put("VAN", new HashMap<String, ArrayList<PlayerObject>>());
		teamPlayList.put("WAS", new HashMap<String, ArrayList<PlayerObject>>());
		
		setGoalies(null);
		
		initTables();

	}
	
	private void initTables()
	{
		for(String key : this.teamPlayList.keySet())
		{
			this.teamPlayList.get(key).put("forwards", new ArrayList<PlayerObject>());
			this.teamPlayList.get(key).put("defense", new ArrayList<PlayerObject>());
			this.teamPlayList.get(key).put("goalies", new ArrayList<PlayerObject>());
		}
	}

	public ArrayList<PlayerObject> getGoalies() {
		return goalies;
	}

	public void setGoalies(ArrayList<PlayerObject> goalies) {
		this.goalies = goalies;
	}
	
	
	
}
