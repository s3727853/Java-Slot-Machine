package model;

public enum Actions {

	// These values are used as action commands that are sent to action listeners.
	
	SHOW_DIALOG("Show Dialog"),
	HIDE_DIALOG("Hide Dialog"),
	SUBMIT_DIALOG("Submit Dialog"),
	SET_DELAY("Set Delay"),
	CASHOUT("Cashout"),
	CHECKBOX("Checkbox"),
	EXIT_PROGRAM("Exit program"),
	SET_TURNS("Set Turns");
	
	private String action;
	
	Actions(String action){
		this.action = action;
	}
	
	public String getAction() {
		return this.action;
	}
}