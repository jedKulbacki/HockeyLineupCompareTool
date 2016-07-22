package lineup;

public class PlayerObject 
{
	public String position;
	public int line;
	public String firstName;
	public String lastName;
	public double fppg;
	public String team;
	public int salary;
	public String oppTeam;
	
	public PlayerObject(String pos, int li, String fname, String lname, double points, String tm, int sal)
	{
		this.position = pos;
		this.line = li;
		this.firstName = fname;
		this.lastName = lname;
		this.fppg = points;
		this.team = tm;
		this.salary = sal;
		this.oppTeam = "";
	}
	
	public PlayerObject()
	{
		this("", 0, "", "", 0.0, "", 0);
	}

}
