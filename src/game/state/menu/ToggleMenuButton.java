package game.state.menu;

public class ToggleMenuButton extends MenuButton {

	private String status;	// ON, OFF
	
	public ToggleMenuButton(String name, String text, String imgUrl) {
		super(name, text, imgUrl);
		status = "ON";
	}
	
	public String getStatus() {
		return status;
	}
	
	public void toggle() {
		if (status == "ON")
			status = "OFF";
		else
			status = "ON";
	}
	
}
