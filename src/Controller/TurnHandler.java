package Controller;

public class TurnHandler {
	
	private String color = "White";
	
	public String getColor(){
		return color;
	}
	
	public void swapColor(){
		if(color.equals("White")){
			color = "Black";
		}
		else{
			color = "White";
		}
	}
	
}
