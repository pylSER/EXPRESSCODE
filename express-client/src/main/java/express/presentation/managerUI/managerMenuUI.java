package express.presentation.managerUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import express.businessLogic.userBL.User;
import express.businesslogicService.signBLService.LogInBLService;
import express.presentation.mainUI.MainUIService;
import express.vo.UserInfoSignVO;

public class managerMenuUI extends JPanel{

	private MainUIService m;
	private LogInBLService login;
	private String id;
	private CardLayout card;
//	private JFrame frame;
	private JPanel mainPanel,pane;
	private JLabel username, userid;
	private JButton log;
	private JButton statistic;
	private JButton staff;
	private JButton org;
	private JButton examdoc,salary,cityprice;
	private JButton exit;
	private JMenuItem viewprofits, viewoperate;
	private JPopupMenu viewstatisticpop;

	private boolean isclicked = false;
	
	public managerMenuUI(MainUIService main,String id){

		setLayout(null);
		this.m = main;
		pane = this;
		card = new CardLayout();	
		
		int base = 150;
		int width = 50;
		int height = 150;
		Font font = new Font("隶书",Font.PLAIN,20);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(card);
		mainPanel.setOpaque(false);
		mainPanel.setBounds(150, 0, 850, 700);
		this.add(mainPanel);

		m.setcard1(card);
		m.setpane1(mainPanel);
		
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
		
		log = new JButton("查询日志");
		log.setBounds(0, base, height, width);
		log.setFont(font);
		this.add(log);
		
		staff = new JButton("人员管理");
		staff.setBounds(0, base+width, height, width);
		staff.setFont(font);
		this.add(staff);
		
		org = new JButton("机构管理");
		org.setBounds(0, base+2*width, height, width);
		org.setFont(font);
		this.add(org);
		
		examdoc = new JButton("审批单据");
		examdoc.setBounds(0, base+3*width, height, width);
		examdoc.setFont(font);
		this.add(examdoc);
		
		statistic = new JButton("查看统计分析");
		statistic.setBounds(0, base+4*width, height, width);
		statistic.setFont( new Font("隶书",Font.PLAIN,18));
		this.add(statistic);
		
		salary = new JButton("制定薪水策略");
		salary.setBounds(0, base+5*width, height, width);
		salary.setFont(new Font("隶书",Font.PLAIN,18));
		this.add(salary);
		
		cityprice = new JButton("制定距离价格");
		cityprice.setBounds(0, base+6*width, height, width);
		cityprice.setFont(new Font("隶书",Font.PLAIN,18));
		this.add(cityprice);
		
		viewstatisticpop = new JPopupMenu();
		viewstatisticpop.setFont(font);
		viewprofits = new JMenuItem("查看成本收益表");
		viewprofits.setFont(font);
		viewoperate = new JMenuItem("查看经营状况表");
		viewoperate.setFont(font);
		viewstatisticpop.add(viewprofits);
		viewstatisticpop.add(viewoperate);
		
		exit = new JButton("退出");
		exit.setBounds(0, 600, height, width);
		exit.setFont(font);
		this.add(exit);
		
		this.setBounds(0, 30, 1200, 900);
		
		Listener listener = new Listener();
		ActListener actlis = new ActListener();
		
		log.addMouseListener(listener);		
		staff.addMouseListener(listener);	
		org.addMouseListener(listener);	
		examdoc.addMouseListener(listener);	
		statistic.addMouseListener(listener);	
        exit.addMouseListener(listener);
        salary.addMouseListener(listener);
        cityprice.addMouseListener(listener);
        viewprofits.addActionListener(actlis);
        viewoperate.addActionListener(actlis);
	}
	
	private class Listener implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==exit){
				login.SignOut(id);
				m.jumpToLogInUI();		
			}else if(e.getSource()==log){
				m.jumpToViewSysLogUI();	
			}else if(e.getSource()==staff){
				m.jumpTomanagerMemberUI();
			}else if(e.getSource()==org){
				m.jumpTomanagerOrgUI();
			}else if(e.getSource()==examdoc){
				m.jumpTomanagerExamDocUI();
			}else if (e.getSource() == statistic) {
				if (!isclicked) {
					isclicked = true;
					viewstatisticpop.show(pane, 150, 350);
				} else {
					isclicked = false;
					viewstatisticpop.setVisible(false);
				}
			}else if(e.getSource()==salary){
				m.jumpTomanagerSalaryUI();
			}else if(e.getSource()==cityprice){
				m.jumpTomanagerCityPriceUI();
			}
			updateUI();
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}	
	
	private class ActListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource().equals(viewprofits)) {
				m.jumpToViewProfitUI();
			 //   m.jumpToViewStatisticUI("成本收益表");
			} else if (e.getSource().equals(viewoperate)) {
				m.jumpToViewOperateUI();
			//	m.jumpToViewStatisticUI("经营状况表");
			}
			updateUI();
		}

	}
	
	public void paintComponent(Graphics g) {
		ImageIcon background = new ImageIcon("picture/background.png");
		g.drawImage(background.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
