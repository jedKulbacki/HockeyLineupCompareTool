package lineup;

public class LineUp {
	
	public PlayerObject rightWingOne;
	public PlayerObject leftWingOne;
	public PlayerObject centerOne;
	public PlayerObject dManOne;
	public PlayerObject rightWingTwo;
	public PlayerObject leftWingTwo;
	public PlayerObject centerTwo;
	public PlayerObject dManTwo;
	public PlayerObject goalie;
	public int salaryTotal;
	public double fanPoints;
	
	//Finished Object ready to be printed
	public LineUp()
	{
		
	}
	
	public LineUp(PlayerObject rightWingOne, PlayerObject leftWingOne,
			PlayerObject centerOne, PlayerObject dManOne,
			PlayerObject rightWingTwo, PlayerObject leftWingTwo,
			PlayerObject centerTwo, PlayerObject dManTwo, PlayerObject goalie,
			int salaryTotal, double fPoints) {
		super();
		this.rightWingOne = rightWingOne;
		this.leftWingOne = leftWingOne;
		this.centerOne = centerOne;
		this.dManOne = dManOne;
		this.rightWingTwo = rightWingTwo;
		this.leftWingTwo = leftWingTwo;
		this.centerTwo = centerTwo;
		this.dManTwo = dManTwo;
		this.goalie = goalie;
		this.salaryTotal = salaryTotal;
		this.fanPoints = fPoints;
	}
	
	public  LineUp (Stack stackOne, Stack stackTwo, PlayerObject goalie, int salary, double points)
	{
		this(stackOne.rightWing, stackOne.leftWing, stackOne.center, stackOne.dMan,
				stackTwo.rightWing, stackTwo.leftWing, stackTwo.center, stackTwo.dMan,
				goalie, salary, points);
	}

}
