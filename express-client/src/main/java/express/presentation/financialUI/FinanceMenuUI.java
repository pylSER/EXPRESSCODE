package express.presentation.financialUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import express.businessLogic.userBL.User;
import express.businesslogicService.signBLService.LogInBLService;
import express.presentation.mainUI.MainUIService;
import express.vo.UserInfoSignVO;

public class FinanceMenuUI extends JPanel {

	private MainUIService m;
	private LogInBLService login;
	private String id;
	private CardLayout card;
	// private JFrame frame;
	private JPanel mainPanel, pane;
	private JLabel username, userid;
//	private JMenuBar viewstatisticbar, createstatisticbar;
//	private JMenu viewstatisticm, createstatisticm;
	private JMenuItem viewprofits, viewoperate, createprofits, createoperate;
	private JPopupMenu viewstatisticpop, createstatisticpop;
	private JButton log;
	private JButton viewstatistic, createstatistic;
	private JButton sumReceiveDoc, initAccount, payment, accountmanage;
	private JButton exit;

	private boolean isclickedv = false;
	private boolean isclickedc = false;

	public FinanceMenuUI(MainUIService main,String id) {
		setLayout(null);
		this.m = main;
		pane = this;
		card = new CardLayout();

		int base = 150;
		int width = 50;
		int height = 150;
		Font font = new Font("隶书", Font.PLAIN, 20);

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

		initAccount = new JButton("期初建账");
		initAccount.setBounds(0, base + width, height, width);
		initAccount.setFont(font);
		this.add(initAccount);

		payment = new JButton("生成付款单");
		payment.setBounds(0, base + 2 * width, height, width);
		payment.setFont(font);
		this.add(payment);

		sumReceiveDoc = new JButton("合计收款单");
		sumReceiveDoc.setBounds(0, base + 3 * width, height, width);
		sumReceiveDoc.setFont(font);
		this.add(sumReceiveDoc);

		accountmanage = new JButton("账户管理");
		accountmanage.setBounds(0, base + 4 * width, height, width);
		accountmanage.setFont(new Font("隶书", Font.PLAIN, 18));
		this.add(accountmanage);

		viewstatistic = new JButton("查看统计分析");
		viewstatistic.setBounds(0, base + 5 * width, height, width);
		viewstatistic.setFont(new Font("隶书", Font.PLAIN, 18));
		this.add(viewstatistic);

		// viewstatisticbar = new JMenuBar();
		// viewstatisticbar.setBounds(0, base + 5 * width, height, width);
		// viewstatisticm = new JMenu("查看统计分析");
		// viewstatisticm.setFont(font);
		viewstatisticpop = new JPopupMenu();
		viewstatisticpop.setFont(font);
		viewprofits = new JMenuItem("查看成本收益表");
		viewprofits.setFont(font);
		viewoperate = new JMenuItem("查看经营状态表");
		viewoperate.setFont(font);
		viewstatisticpop.add(viewprofits);
		viewstatisticpop.add(viewoperate);
		// viewstatisticm.add(viewprofits);
		// viewstatisticm.add(viewoperate);
		// viewstatisticbar.add(viewstatisticm);
		// this.add(viewstatisticbar);

		createstatistic = new JButton("生成统计分析");
		createstatistic.setBounds(0, base + 6 * width, height, width);
		createstatistic.setFont(new Font("隶书", Font.PLAIN, 18));
		this.add(createstatistic);

		// createstatisticbar = new JMenuBar();
		// createstatisticbar.setBounds(0, base + 6 * width, height, width);
		// createstatisticm = new JMenu("生成统计分析");
		// createstatisticm.setFont(font);
		createstatisticpop = new JPopupMenu();
		createstatisticpop.setFont(font);
		createprofits = new JMenuItem("生成成本收益表");
		createprofits.setFont(font);
		createoperate = new JMenuItem("生成经营状况表");
		createoperate.setFont(font);
		createstatisticpop.add(createprofits);
		createstatisticpop.add(createoperate);
		// createstatisticm.add(createprofits);
		// createstatisticm.add(createoperate);
		// createstatisticbar.add(createstatisticm);
		// this.add(createstatisticbar);

		exit = new JButton("退出");
		exit.setBounds(0, 600, height, width);
		exit.setFont(font);
		this.add(exit);

		this.setBounds(0, 0, 1200, 900);

		Listener listener = new Listener();
		ActListener actlis = new ActListener();

		log.addMouseListener(listener);
		sumReceiveDoc.addMouseListener(listener);
		initAccount.addMouseListener(listener);
		payment.addMouseListener(listener);
		accountmanage.addMouseListener(listener);
		// viewstatisticm.addMouseListener(listener);
		// createstatisticm.addMouseListener(listener);
		viewprofits.addActionListener(actlis);
		viewoperate.addActionListener(actlis);
		createprofits.addActionListener(actlis);
		createoperate.addActionListener(actlis);
		viewstatistic.addMouseListener(listener);
		createstatistic.addMouseListener(listener);
		exit.addMouseListener(listener);
	}

	private class Listener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == exit) {
				login.SignOut(id);
				m.jumpToLogInUI();

			} else if (e.getSource() == log) {
				m.jumpToViewSysLogUI();

			} else if (e.getSource() == sumReceiveDoc) {
				m.jumpToFinanceSumReceiveDocUI();

			} else if (e.getSource() == initAccount) {
				m.jumpToFinanceInitAccountUI();

			} else if (e.getSource() == payment) {
				m.jumpToFinancePaymentUI();

			} else if (e.getSource() == accountmanage) {
				m.jumpToFinanceManageBankAccountUI();

			} else if (e.getSource() == viewstatistic) {
				if (!isclickedv) {
					isclickedv = true;
					viewstatisticpop.show(pane, 150, 400);
				} else {
					isclickedv = false;
					viewstatisticpop.setVisible(false);
				}
			} else if (e.getSource() == createstatistic) {
				if (!isclickedc) {
					isclickedc = true;
					createstatisticpop.show(pane, 150, 450);
				} else {
					isclickedc = false;
					createstatisticpop.setVisible(false);
				}
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
			if (e.getSource().equals(createprofits)) {
				m.jumpToFinanceCreateProfitUI();
			} else if (e.getSource().equals(createoperate)) {
				m.jumpToFinanceCreateOperateUI();
			}else if (e.getSource().equals(viewprofits)) {
				m.jumpToViewProfitUI();
			}else if (e.getSource().equals(viewoperate)) {
				m.jumpToViewOperateUI();
			}
			updateUI();
		}

	}

	public void paintComponent(Graphics g) {
		ImageIcon background = new ImageIcon("picture/background.png");
		g.drawImage(background.getImage(), 0, 0, this.getWidth(),
				this.getHeight(), this);
	}
}
