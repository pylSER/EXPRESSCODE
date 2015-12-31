package express.presentation.mainUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TipBlock extends JLabel{
		private int x,y;
	

	public TipBlock(String contents,JPanel mainpane){
		x=300;
		y=180;
		this.setSize(x,y);
		this.setOpaque(true);
		this.setBackground(Color.green);
		this.setLocation(500,500);
		Font font=new Font("苹方", Font.PLAIN, 20);
		this.setFont(font);
		this.setText(contents);
		mainpane.add(this);
		run();
	}
	
	public void run(){
		for (int i = 0; i <= 100; i += 10) {    
            try {    
                this.setLocation(x, y - i);  
                Thread.sleep(15); 
            }catch(Exception e){
            	e.printStackTrace();
            }
		}
	}
	
	
	
	
	
	
	
}
