package express.presentation.transRepoUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import express.businessLogic.userBL.User;
import express.businesslogicService.signBLService.LogInBLService;
import express.presentation.mainUI.MainUIService;
import express.vo.UserInfoSignVO;

public class TranscenterRepoMenuUI extends JPanel{
	private CardLayout card=new CardLayout();
    private MainUIService main;
    private LogInBLService login;
	private String id;
    private JPanel repoPanel;
	
	//组件：
    private JLabel username, userid;
	private JButton button_in = new JButton("入库");
	private JButton button_out = new JButton("出库");
	private JButton button_view = new JButton("库存查看");
	private JButton button_inventory = new JButton("库存盘点");
	private JButton button_adjust = new JButton("仓库调整");
	private JButton button_exit = new JButton("退出");
	
	
	
	public TranscenterRepoMenuUI(MainUIService m,String id){
		
		int base = 150;
		int width = 50;
		Font font = new Font("隶书",Font.PLAIN,20);
		
		this.setLayout(null);
		this.main=m;
		
		repoPanel=new JPanel();
		repoPanel.setLayout(card);
		repoPanel.setBounds(150, 0, 850, 700);
		repoPanel.setOpaque(false);
		this.add(repoPanel);
	
		main.setcard1(card);
		main.setpane1(repoPanel);
		
		JListener listener=new JListener();	
		login = new User();
		this.id = id;		
		UserInfoSignVO vo = login.getUserInfo(id);
		String name = vo.getName();
		
		username = new JLabel();
		username.setBounds(50, 50, 70, 20);
		username.setText(name);
		username.setForeground(Color.BLACK);
		username.setFont(new Font("隶书",Font.PLAIN,18));
		this.add(username);
		
		userid = new JLabel();
		userid.setBounds(40, 75, 100, 20);
		userid.setText(id);
		userid.setForeground(Color.BLACK);
		userid.setFont(new Font("隶书",Font.PLAIN,18));
		this.add(userid);
		
		button_in.setBounds(0, base, 150, width);
		button_in.setFont(font);
        button_in.addMouseListener(listener);//加监听
	
		button_out.setBounds(0, base+width, 150, width);
		button_out.setFont(font);
		button_out.addMouseListener(listener);//加监听		
		
		button_view.setBounds(0, base+2*width, 150, width);
		button_view.setFont(font);
		button_view.addMouseListener(listener);//加监听
		
		button_inventory.setBounds(0, base+3*width, 150, width);
		button_inventory.setFont(font);
		button_inventory.addMouseListener(listener);//加监听
		
		button_adjust.setBounds(0, base+4*width, 150, width);
		button_adjust.setFont(font);
		button_adjust.addMouseListener(listener);
		
		button_exit.setBounds(0, 600, 150, 50); 
		button_exit.setFont(font);
		button_exit.addMouseListener(listener);	
		
		add(button_in);
		add(button_out);
		add(button_view);
		add(button_inventory);
		add(button_adjust);
		add(button_exit);
		
		this.setBounds(0,0,1000,700);
	}
	
	class JListener implements MouseListener{

		public void mouseClicked(MouseEvent arg0) {
			 if (arg0.getSource()==button_in){
				 main.jumpToinUI();			 
				 System.out.println("应该跳转到“入库”界面的");
				
			 }
			 else if (arg0.getSource()==button_out){
				 main.jumpTooutUI();
				 System.out.println("应该跳转到“出库”界面的");
					 
			 }
			 
			 else if (arg0.getSource()==button_view){
				 main.jumpToviewUI();
				 System.out.println("应该跳转到“库存查看”界面的");
					
			 }
			 else if (arg0.getSource()==button_inventory){
				 main.jumpToinventoryUI();
				 System.out.println("应该跳转到“库存盘点”界面的");
				
			 }
			 else if (arg0.getSource()==button_adjust){
				 main.jumpToadjustUI();
				 System.out.println("应该跳转到“库存调整”界面的");
				
			 }
			 else if (arg0.getSource()==button_exit){
				 login.SignOut(id);
				 main.jumpToLogInUI();
				 System.out.println("应该回到登陆界面的");
				
			 }
			 updateUI();
		}

		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public void paintComponent(Graphics g) {
		ImageIcon background = new ImageIcon("picture/background.png");
		g.drawImage(background.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
	}

}
