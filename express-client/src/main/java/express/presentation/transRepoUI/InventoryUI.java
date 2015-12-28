package express.presentation.transRepoUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import express.businessLogic.IDKeeper;
import express.businessLogic.repoBL.RepoController;
import express.businesslogicService.transcenterRepoBLService.InventoryRepoBLService;
import express.po.Area;
import express.po.RepoPosition;
import express.presentation.mainUI.MainUIService;
import express.vo.InDocVO;

public class InventoryUI extends JPanel {

	private MainUIService m;

	private JPanel inventory;
	private JButton excel, exit;
	private String orgID = IDKeeper.getOrgID();

	public InventoryUI(MainUIService main) {

		Font font = new Font("楷体", Font.PLAIN, 20);
		//Font f = new Font("仿宋", Font.PLAIN, 18);
		Font f1 = new Font("隶书", Font.PLAIN, 20);

		setLayout(null);
		this.m = main;

		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		Date d = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(d);
		JLabel title = new JLabel(date + " 库存盘点", JLabel.CENTER);
		// title.setText(date+" 库存盘点");
		title.setFont(new Font("仿宋", Font.BOLD, 20));
		title.setBounds(100, 30, 600, 30);
		this.add(title);

		inventory = new JPanel();
		inventory.setLocation(100, 95);
		inventory.setPreferredSize(new Dimension(640, 460));
		inventory.setOpaque(false);
		inventory.setLayout(null);
		JScrollPane scrollPane = new JScrollPane(inventory);
		scrollPane.setFont(font);
		scrollPane.setBounds(100, 95, 650, 470);
		scrollPane.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0,
				Color.GRAY));
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		// scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBackground(new Color(250, 235, 215));
		this.add(scrollPane);

		excel = new JButton();
		excel.setFont(f1);
		excel.setText("导出Excel");
		excel.setBounds(100, 600, 250, 40);
		excel.setBackground(Color.WHITE);
		this.add(excel);

		exit = new JButton();
		exit.setFont(f1);
		exit.setText("返回菜单");
		exit.setBounds(500, 600, 250, 40);
		exit.setBackground(Color.WHITE);
		this.add(exit);

		Listener listener = new Listener();
		excel.addMouseListener(listener);
		exit.addMouseListener(listener);

		init();
	}

	private void init() {
		InventoryRepoBLService view = new RepoController();
		view.endRepoInventory();

		ArrayList<InDocVO> list = view.inventoryRepo(orgID);

		if (list == null) {
			addNone();
			return;
		}
		if (list.size() == 0) {
			addNone();
			return;
		}
		addTitle();
		int index = 0;
		for (InDocVO vo : list) {
			addLine(vo, index);
			index++;
		}
		int height = 40 * list.size();
		inventory.setPreferredSize(new Dimension(640, height));
	}

	private void addLine(InDocVO vo, int i) {

		int y = i * 40;
		int height = 40;
		Font f = new Font("仿宋", Font.PLAIN, 18);

		JLabel title1 = new JLabel(vo.getdeliveryNumber(), JLabel.CENTER);
		title1.setFont(f);
		title1.setBounds(0, y, 100, height);
		title1.setOpaque(false);
		title1.setBorder(BorderFactory
				.createMatteBorder(3, 0, 2, 1, Color.GRAY));
		inventory.add(title1);

		JLabel title2 = new JLabel(vo.getdate(), JLabel.CENTER);
		title2.setFont(f);
		title2.setBounds(100, y, 100, height);
		title2.setOpaque(false);
		title2.setBackground(new Color(250, 235, 215));
		title2.setBorder(BorderFactory
				.createMatteBorder(3, 0, 2, 1, Color.GRAY));
		inventory.add(title2);

		JLabel title3 = new JLabel(vo.getarrival(), JLabel.CENTER);
		title3.setFont(f);
		title3.setBounds(200, y, 90, height);
		title3.setOpaque(false);
		title3.setBorder(BorderFactory
				.createMatteBorder(3, 0, 2, 1, Color.GRAY));
		inventory.add(title3);

		RepoPosition rp = vo.getRepoPosition();
		Area a = rp.getblock();
		JLabel title4 = new JLabel(getArea(a), JLabel.CENTER);
		title4.setFont(f);
		title4.setBounds(290, y, 90, height);
		title4.setOpaque(false);
		title4.setBorder(BorderFactory
				.createMatteBorder(3, 0, 2, 1, Color.GRAY));
		inventory.add(title4);

		JLabel title5 = new JLabel(rp.getrow() + "", JLabel.CENTER);
		title5.setFont(f);
		title5.setBounds(380, y, 90, height);
		title5.setOpaque(false);
		title5.setBorder(BorderFactory
				.createMatteBorder(3, 0, 2, 1, Color.GRAY));
		inventory.add(title5);

		JLabel title6 = new JLabel(rp.getshelf() + "", JLabel.CENTER);
		title6.setFont(f);
		title6.setBounds(470, y, 90, height);
		title6.setOpaque(false);
		title6.setBorder(BorderFactory
				.createMatteBorder(3, 0, 2, 1, Color.GRAY));
		inventory.add(title6);

		JLabel title7 = new JLabel(rp.getposition() + "", JLabel.CENTER);
		title7.setFont(f);
		title7.setBounds(560, y, 90, height);
		title7.setOpaque(false);
		title7.setBorder(BorderFactory
				.createMatteBorder(3, 0, 2, 0, Color.GRAY));
		inventory.add(title7);
	}

	private void addTitle() {
		Font f = new Font("仿宋", Font.PLAIN, 18);
		JLabel title1 = new JLabel("快递编号", JLabel.CENTER);
		title1.setFont(f);
		title1.setBounds(100, 60, 100, 35);
		title1.setOpaque(true);
		title1.setBackground(new Color(250, 235, 215));
		title1.setBorder(BorderFactory
				.createMatteBorder(3, 0, 2, 1, Color.GRAY));
		this.add(title1);

		JLabel title2 = new JLabel("入库日期", JLabel.CENTER);
		title2.setFont(f);
		title2.setBounds(200, 60, 100, 35);
		title2.setOpaque(true);
		title2.setBackground(new Color(250, 235, 215));
		title2.setBorder(BorderFactory
				.createMatteBorder(3, 0, 2, 1, Color.GRAY));
		this.add(title2);

		JLabel title3 = new JLabel("目的地", JLabel.CENTER);
		title3.setFont(f);
		title3.setBounds(300, 60, 90, 35);
		title3.setOpaque(true);
		title3.setBackground(new Color(250, 235, 215));
		title3.setBorder(BorderFactory
				.createMatteBorder(3, 0, 2, 1, Color.GRAY));
		this.add(title3);

		JLabel title4 = new JLabel("区号", JLabel.CENTER);
		title4.setFont(f);
		title4.setBounds(390, 60, 90, 35);
		title4.setOpaque(true);
		title4.setBackground(new Color(250, 235, 215));
		title4.setBorder(BorderFactory
				.createMatteBorder(3, 0, 2, 1, Color.GRAY));
		this.add(title4);

		JLabel title5 = new JLabel("排号", JLabel.CENTER);
		title5.setFont(f);
		title5.setBounds(480, 60, 90, 35);
		title5.setOpaque(true);
		title5.setBackground(new Color(250, 235, 215));
		title5.setBorder(BorderFactory
				.createMatteBorder(3, 0, 2, 1, Color.GRAY));
		this.add(title5);

		JLabel title6 = new JLabel("架号", JLabel.CENTER);
		title6.setFont(f);
		title6.setBounds(570, 60, 90, 35);
		title6.setOpaque(true);
		title6.setBackground(new Color(250, 235, 215));
		title6.setBorder(BorderFactory
				.createMatteBorder(3, 0, 2, 1, Color.GRAY));
		this.add(title6);

		JLabel title7 = new JLabel("位号", JLabel.CENTER);
		title7.setFont(f);
		title7.setBounds(660, 60, 90, 35);
		title7.setOpaque(true);
		title7.setBackground(new Color(250, 235, 215));
		title7.setBorder(BorderFactory
				.createMatteBorder(3, 0, 2, 0, Color.GRAY));
		this.add(title7);
	}

	private void addNone() {
		JLabel title = new JLabel();
		title.setBounds(100, 60, 650, 35);
		title.setOpaque(true);
		title.setBackground(new Color(250, 235, 215));
		title.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0, Color.GRAY));
		this.add(title);

		JLabel tip = new JLabel("没 有 入 库 信 息", JLabel.CENTER);
		tip.setBounds(0, 100, 640, 200);
		tip.setFont(new Font("仿宋", Font.BOLD, 49));
		tip.setOpaque(false);
		tip.setForeground(new Color(255, 195, 170));
		inventory.add(tip);
	}

	private String getArea(Area a) {
		switch (a) {
		case AIR:
			return "航运区";
		case TRAIN:
			return "铁运区";
		case CAR:
			return "汽运区";
		default:
			return "机动区";
		}
	}

	private class Listener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

			if (e.getSource() == excel) {
				InventoryRepoBLService view = new RepoController();
				boolean succ = view.exportExcel(orgID);
				if (succ) {
					JOptionPane.showConfirmDialog(null, "导 出 成 功！", null,
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE, null);
				} else {
					JOptionPane.showConfirmDialog(null, "导 出 失 败！", null,
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
							null);
				}
			} else if (e.getSource() == exit) {
				m.jumpTotranscenterRepoMenuUI(IDKeeper.getID());
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

}
