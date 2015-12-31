package express.presentation.mainUI;

import java.awt.Color;

public class MyOtherRedLabel extends MyOtherBlueLabel{

	public MyOtherRedLabel(String content) {
		super(content);
	}

	
	public void setMyColor(){
		//red
		this.setBackground(new Color(193,39,34));
	}
	
	
	public void whenPressed(){
		this.setBackground(new Color(136,27,22));
	}
	
}
