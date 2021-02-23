package slotmachine.model.slots;

public enum LineNum {
	
	LINE1("Line #1"),
	LINE2("Line #2"),
	LINE3("Line #3"),
	LINE4("Line #4"),
	LINE5("Line #5");

	private String lineNum;
	
	private LineNum(String lineNum) {
		this.lineNum = lineNum;
	}
	
	@Override
	public String toString()
	{
		return lineNum;
	}

}

