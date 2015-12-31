package express.presentation.mainUI;

import java.awt.Color;
import java.awt.Font;


import javax.swing.JLabel;


public class MySideLabel extends JLabel{
	private boolean isClicked=false;
	
	
	
	public MySideLabel(String contents){
		//96.161.198
		//107.180.215
		init(contents);	
	}
	
	public void whenMouseOnIt(){
		if(!isClicked)
		this.setBackground(new Color(107, 180, 215));
	}
	
	public void whenClickHappend(){
		this.isClicked=true;
		this.setBackground(Color.white);
		this.setForeground(Color.black);	
	}
	
	
	public void init(String contents){
		this.setOpaque(true);
		this.setSize(150,50);
		this.setBackground(new Color(96, 161, 198));
		Font font=new Font("苹方", Font.PLAIN, 15);
		this.setForeground(Color.white);
		this.setFont(font);
		this.setText("   "+contents);	
	}
	
	public void whenMouseleaveit(){
		if(isClicked){
		}
		else {
			this.setBackground(new Color(96, 161, 198));
			
		}
	}
	
	public void restore(){
		this.isClicked=false;
		this.setBackground(new Color(96, 161, 198));
		this.setForeground(Color.white);
	}
	
}
