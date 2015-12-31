package express.presentation.mainUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class MyOtherBlueLabel extends JLabel{
	//blue 37 114 177
	public MyOtherBlueLabel(String content){
		init(content);
	}
	
	public void whenPressed(){
		this.setBackground(new Color(26,89,158));
	}
	
	public void init(String content){
		Font font=new Font("苹方", Font.PLAIN, 18);
		this.setOpaque(true);
		this.setForeground(Color.white);
		this.setFont(font);
		this.setText("     "+content);
		this.setMyColor();
	}

	public void setMyColor(){
		//blue
		this.setBackground(new Color(37,114,177));
	}
	
	
	
}
