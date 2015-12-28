package express.presentation.transRepoUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import express.businessLogic.repoBL.RepoController;
import express.businesslogicService.transcenterRepoBLService.AdjustRepoBLService;
import express.po.Area;

public class ShowRepoUI extends JDialog {

	JButton ok, exit;
	JButton[] buttonList = new JButton[12];
	JTextField rowtf, postf;
	String text = "";
	String orgID;

	public ShowRepoUI(String orgID, Area area, int row, JTextField rowtf,
			JTextField postf) {
		this.setTitle("仓库信息");
		this.setLayout(null);

		//this.setSize(400, 500);
		this.setPreferredSize(new Dimension(400,500));
		this.setLocationRelativeTo(null);
		this.rowtf = rowtf;
		this.postf = postf;
		this.orgID = orgID;

		Color green = new Color(34, 139, 34);
		Font font = new Font("楷体", Font.PLAIN, 20);
		JButton[] buttonList = new JButton[12];
		AdjustRepoBLService adjust = new RepoController();
		String[] list = adjust.getPosition(orgID, area, row);

		Action action = new Action();
		JLabel title = new JLabel();
		title.setText("第" + row + "排仓库位置选择表");
		title.setFont(font);
		title.setBounds(0, 20, 400, 30);

		for (int i = 0; i < 12; i++) {
			JButton button = buttonList[i];
			button.setBounds(100 * (i % 4), 60 + 100 * (i / 4), 100, 100);
			button.setBackground(Color.WHITE);
			String s = list[i];
			if (list[i].startsWith("#")) {
				button.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
				s = s.substring(1);
			} else {
				button.setBorder(BorderFactory.createLineBorder(green, 3));
			}
			button.setActionCommand(list[i]);
			String[] text = s.split("+");
			button.addActionListener(action);
			button.setText(text[0] + " 架" + "\n" + text[1] + " 号");
			button.setFont(font);
			this.add(button);
		}
		
		Listener listener = new Listener();
		
		ok = new JButton();
		ok.setText("确定");
		ok.setBackground(Color.WHITE);
		ok.setBounds(20, 390, 150, 30);
		ok.setFont(new Font("隶书",Font.PLAIN,20));
		this.add(ok);
		
		exit = new JButton();
		exit.setText("确定");
		exit.setBackground(Color.WHITE);
		exit.setBounds(200, 390, 150, 30);
		exit.setFont(new Font("隶书",Font.PLAIN,20));
		this.add(exit);
		
		ok.addMouseListener(listener);
		exit.addMouseListener(listener);
	}

	private class Listener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == exit) {
				dispose();
			} else if (e.getSource() == ok) {
				
				if(text.equals("")){
					JOptionPane.showConfirmDialog(null, "请选择一个仓库位置", null,
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
							null);
				}else{
					if(text.startsWith("#")){
						JOptionPane.showConfirmDialog(null, "该位置正在使用", null,
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
								null);
					}else{
						String[] s = text.split("#");
						rowtf.setText(s[0]);
						postf.setText(s[1]);
						dispose();
					}
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	private class Action implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String s = e.getActionCommand();
			JButton button = (JButton) e.getSource();
			text = s;
			for (JButton b : buttonList) {
				b.setBackground(Color.WHITE);
			}
			if (s.startsWith("#")) {
				button.setBackground(new Color(255, 218, 185));
			} else {
				button.setBackground(new Color(127, 255, 212));
			}
			repaint();
		}

	}
}
