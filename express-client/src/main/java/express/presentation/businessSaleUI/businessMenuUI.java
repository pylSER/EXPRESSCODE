package express.presentation.businessSaleUI;

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

public class businessMenuUI extends JPanel{
	
	private MainUIService main;
	private LogInBLService login;
	private String id;
	private CardLayout card=new CardLayout();	
	private JPanel businessPanel;	
	private JLabel username, userid;

	private JButton button_shipment = new JButton("车辆装车管理");
	private JButton button_vehicle = new JButton("车辆信息管理");
	private JButton button_driver = new JButton("司机信息管理");
	private JButton button_recieve = new JButton("建立收款单");
	private JButton button_deliver = new JButton("生成派件单");
	private JButton button_arrival = new JButton("生成到达单");
	private JButton button_exit=new JButton("退出");
	
	public businessMenuUI(MainUIService m,String id){
		int base = 150;
		int width = 50;
		Font font = new Font("隶书",Font.PLAIN,19);
		
		this.main=m;
		setLayout(null);
	
		businessPanel=new JPanel();
		businessPanel.setLayout(card);
		businessPanel.setBounds(150, 0, 850, 700);
		businessPanel.setOpaque(false);
		this.add(businessPanel);
		
		main.setcard1(card);
		main.setpane1(businessPanel);
		
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
			
		button_shipment.setBounds(0, base, 150, width);
		button_shipment.setFont(font);
        button_shipment.addMouseListener(listener);//加监听
	
		button_vehicle.setBounds(0, base+width, 150, width);
		button_vehicle.setFont(font);
		button_vehicle.addMouseListener(listener);//加监听		
		
		button_driver.setBounds(0, base+2*width, 150, width);
		button_driver.setFont(font);
		button_driver.addMouseListener(listener);//加监听
		
		button_recieve.setBounds(0, base+3*width, 150, width);
		button_recieve.setFont(font);
		button_recieve.addMouseListener(listener);//加监听
		
		button_deliver.setBounds(0, base+4*width, 150, width);
		button_deliver.setFont(font);
		button_deliver.addMouseListener(listener);
		
		button_arrival.setBounds(0, base+5*width, 150, width);
		button_arrival.setFont(font);
		button_arrival.addMouseListener(listener);
		
		
		button_exit.setBounds(0, 600, 150, 50); 
		button_exit.setFont(font);
		button_exit.addMouseListener(listener);
		
		
		this.add(button_arrival);
		this.add(button_deliver);
		this.add(button_driver);
		this.add(button_recieve);
		this.add(button_shipment);
		this.add(button_vehicle);
		this.add(button_exit);
	}
	
	private class JListener implements MouseListener{

		public void mouseClicked(MouseEvent arg0) {
		 
			if (arg0.getSource()==button_shipment){
				main.jumpTobusinessShipmentUI();
				System.out.println("应该跳转到车辆装车管理界面的");			 
				
			}
			else if (arg0.getSource()==button_vehicle){
				main.jumpTobusinessVehicleUI();
				System.out.println("应该跳转到车辆信息管理界面的");
				
			}
			else if (arg0.getSource()==button_driver){
				main.jumpTobusinessDriverUI();
				System.out.println("应该跳转到司机信息管理界面的");
		
			}
			else if (arg0.getSource()==button_recieve){
				main.jumpTobusinessReceiveUI();
				System.out.println("应该跳转到建立收款单界面的");				
				
			}
			else if (arg0.getSource()==button_deliver){
				main.jumpTobusinessDeliverUI();
				System.out.println("应该跳转到生成派件单界面的");			
				
			}
			else if (arg0.getSource()==button_arrival){
				main.jumpTobusinessArrivalUI();
				System.out.println("应该跳转到生成到达单界面的");

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
