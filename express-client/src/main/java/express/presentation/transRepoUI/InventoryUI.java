package express.presentation.transRepoUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import express.presentation.mainUI.DateChooser;
import express.presentation.mainUI.MainUIService;

public class InventoryUI extends JPanel {

	private JButton button_inventory;

	private MainUIService m;

	private JTable table;
	private JScrollPane scrollPane;

	public InventoryUI(MainUIService main) {

		int textlength = 150;
		int textwidth = 30;
		int labellength = 100;
		int labelwidth = 30;

		Font font = new Font("楷体", Font.PLAIN, 18);
		Font f = new Font("仿宋", Font.PLAIN, 16);

		setLayout(null);
		this.m = main;

		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		String[] tableheader = { "快递编号", "位置" };
		String[] data1 = { "10000", "地上" };
		String[] data2 = { "10001", "车上" };

		String[][] data = { data1, data2 };
		table = new JTable(data, tableheader);
		table.setRowHeight(40);
		table.setFont(f);
		table.setBounds(100, 50, 650, 550);
		this.add(table);

		scrollPane = new JScrollPane(table);
		scrollPane.setFont(font);
		scrollPane.setBounds(100, 50, 650, 550);
		this.add(scrollPane);

	}

}
