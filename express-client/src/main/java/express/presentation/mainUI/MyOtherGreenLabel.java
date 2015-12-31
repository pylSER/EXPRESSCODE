package express.presentation.mainUI;

import java.awt.Color;

public class MyOtherGreenLabel extends MyOtherBlueLabel{

	public MyOtherGreenLabel(String content) {
		super(content);
	}

	public void setMyColor(){
		//green
		this.setBackground(new Color(80,157,19));
	}
	
	public void whenPressed(){
		this.setBackground(new Color(48,113,28));
	}
}
