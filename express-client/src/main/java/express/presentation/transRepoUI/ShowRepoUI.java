package express.presentation.transRepoUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import express.businessLogic.repoBL.RepoController;
import express.businesslogicService.transcenterRepoBLService.AdjustRepoBLService;
import express.po.Area;

public class ShowRepoUI extends JDialog {

	JButton ok, exit;
	JButton[] buttonList = new JButton[12];
	JTextField rowtf, postf;
	String text = "";

	public ShowRepoUI(String orgID, Area area, int row, JTextField rowtf,
			JTextField postf) {
		this.setTitle("仓库信息");
		this.setLayout(null);
		
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.rowtf = rowtf;
		this.postf = postf;

		Color green = new Color(34, 139, 34);
		Font font = new Font("楷体", Font.PLAIN, 20);
		JButton[] buttonList = new JButton[12];
		AdjustRepoBLService adjust = new RepoController();
		String[] list = adjust.getPosition(orgID, area, row);

		Action action = new Action();
		JLabel title = new JLabel();
		title.setText("第" + row + "排仓库使用情况");
		title.setBounds(0, 0, 500, 40);
		for (int i = 0; i < 12; i++) {
			JButton button = buttonList[i];
			button.setBounds(0, 0, 125, 100);
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
	}

	private class Listener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == exit) {
				dispose();
			} else if (e.getSource() == ok) {

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
