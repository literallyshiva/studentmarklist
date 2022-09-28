package firstQn1;


public class Student {
	private String name;
	private int id;
	private int physicsmark;
	private int mathsmark;
	private int chemistrymark;
	
	private int totalmark ;
	private int percentage;
	private String physicsGrade;
	private String mathsGrade;
	private String chemistryGrade;
	private double physicsPoint;
	private double mathsPoint;
	private double chemistrypoint;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPhysicsMark() {
		return physicsmark;
	}
	public void setPhysicsMark(int phyMark) {
		physicsmark = phyMark;
	}
	public int getMathsMark() {
		return mathsmark;
	}
	public void setMathsMark(int mathsMark) {
		mathsmark = mathsMark;
	}
	public int getChemistryMark() {
		return chemistrymark;
	}
	public void setChemistryMark(int chemMark) {
		chemistrymark = chemMark;
	}
	public int getTotalMark() {
		return totalmark;
	}
	public void setTotalMark(int totalMark) {
		totalmark = totalMark;
	}
	public int getPercentage() {
		return percentage;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	public String getPhysicsGrade() {
		return physicsGrade;
	}
	public void setPhysicsGrade(String phyGrade) {
		this.physicsGrade = phyGrade;
	}
	public String getMathsGrade() {
		return mathsGrade;
	}
	public void setMathsGrade(String mathsGrade) {
		this.mathsGrade = mathsGrade;
	}
	public String getChemistryGrade() {
		return chemistryGrade;
	}
	public void setChemistryGrade(String chemGrade) {
		this.chemistryGrade = chemGrade;
	}
	public double getPhysicsPoint() {
		return physicsPoint;
	}
	public void setPhysicsPoint(double phyPoint) {
		this.physicsPoint = phyPoint;
	}
	public double getMathsPoint() {
		return mathsPoint;
	}
	public void setMathsPoint(double mathsPoint) {
		this.mathsPoint = mathsPoint;
	}
	public double getChemistrypoint() {
		return chemistrypoint;
	}
	public void setChemistrypoint(double chempoint) {
		this.chemistrypoint = chempoint;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", id=" + id + ", phymark=" + physicsmark + ", mathsmark=" + mathsmark
				+ ", chemmark=" + chemistrymark + ", totalmark=" + totalmark + ", percentage=" + percentage + ", phyGrade="
				+ physicsGrade + ", mathsGrade=" + mathsGrade + ", chemGrade=" + chemistryGrade + ", phyPoint=" + physicsPoint
				+ ", mathsPoint=" + mathsPoint + ", chempoint=" + chemistrypoint + "]";
	}
	
	
	}


