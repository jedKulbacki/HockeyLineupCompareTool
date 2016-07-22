package lineup;

public class Stack {

	public double fanPoints;
	public PlayerObject rightWing;
	public PlayerObject leftWing;
	public PlayerObject center;
	public PlayerObject dMan;
	public int salaryTotal;
	public String team;
	
	public Stack()
	{
		this(new PlayerObject(),new PlayerObject(),new PlayerObject(),new PlayerObject(), 0, 0.0, "");
	}
	
	public Stack(PlayerObject rw, PlayerObject lw, PlayerObject c, PlayerObject d, int sal, double points, String teamName)
	{
		this.rightWing = rw;
		this.leftWing = lw;
		this.center = c;
		this.dMan = d;
		this.salaryTotal = sal;
		this.fanPoints = points;
		this.team = teamName;
	}
}
