package express.presentation.mainUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import express.businessLogic.IDKeeper;
import express.businessLogic.statisticBL.ProfitStatistic;
import express.businesslogicService.financialBLService.ProfitManagerBLService;
import express.vo.ProfitFormVO;

public class ViewProfitUI extends JPanel {

	private MainUIService m;
	private JButton excel, exit, count;
	private JTextField time, income, outcome, profit;
	private JTable profittable;
	private JScrollPane scrollPane;
	private String[] tableheader = { "统计时间", "总收入", "总支出", "总利润" };
	private String[][] data = null;
	private DefaultTableModel tableModel;

	public ViewProfitUI(MainUIService main) {
		setLayout(null);
		m = main;
		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		Font font = new Font("楷体", Font.PLAIN, 18);
		Font f = new Font("仿宋", Font.PLAIN, 18);
		Font font2 = new Font("楷体", Font.BOLD, 18);

		JLabel title = new JLabel("成 本 收 益 表", JLabel.CENTER);
		title.setBounds(70, 70, 700, 30);
		title.setBorder(BorderFactory.createMatteBorder(2, 2, 0, 2, Color.GRAY));
		title.setFont(font2);
		this.add(title);

		Listener listen = new Listener();

		getProfitForm();

		tableModel = new DefaultTableModel(data, tableheader);
		profittable = new JTable(tableModel);
		profittable.getTableHeader().setFont(f);
		profittable.setFont(f);
		// 设置居中
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		profittable.setDefaultRenderer(Object.class, r);

		profittable.setRowHeight(40);
		profittable.addMouseListener(listen);
		this.add(profittable);

		scrollPane = new JScrollPane(profittable);
		scrollPane.setFont(font);
		// scrollPane.setViewportView(logtable);

		// 设置背景透明
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBounds(70, 100, 700, 450);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		this.add(scrollPane);

		excel = new JButton("导出到Excel");
		excel.setBounds(250, 590, 150, 40);
		excel.setVisible(true);
		excel.setBackground(Color.WHITE);
		excel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		excel.setFont(new Font("隶书", Font.PLAIN, 20));
		excel.addMouseListener(listen);
		this.add(excel);

		exit = new JButton("返回");
		exit.setBounds(430, 590, 150, 40);
		exit.setVisible(true);
		exit.setBackground(Color.WHITE);
		exit.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		exit.setFont(new Font("隶书", Font.PLAIN, 20));
		exit.addMouseListener(listen);
		this.add(exit);
	}

	private void getProfitForm() {
		ProfitManagerBLService profit = new ProfitStatistic();

		ArrayList<ProfitFormVO> list = profit.getProfitFormList();
		if (list != null) {
			data = new String[list.size()][4];

			for (int i = 0; i < list.size(); i++) {
				ProfitFormVO vo = list.get(i);
				data[i][0] = vo.getTitle();
				data[i][1] = vo.getIncome() + "";
				data[i][2] = vo.getOutCome() + "";
				data[i][3] = vo.getProfit() + "";
			}
		}
	}

	private void exportExcel(ProfitFormVO p) {
		ProfitManagerBLService profit = new ProfitStatistic();
		if (p == null) {
			JOptionPane.showConfirmDialog(null, "未 选 择 表 格！", null,
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null);
		} else {
			boolean succ = profit.exportExcel(p);
			if (succ) {
				JOptionPane.showConfirmDialog(null, "导 出 成 功！", null,
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null);
			} else {
				JOptionPane.showConfirmDialog(null, "导 出 失 败！", null,
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null);
			}
		}
	}

	private class Listener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == exit) {
				m.jumpToFinanceMenuUI(IDKeeper.getID());
			} else if (e.getSource() == excel) {
				int row = profittable.getSelectedRow();
				if (row < 0) {
					JOptionPane.showConfirmDialog(null, "未 选 择 表 格！", null,
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE, null);

				} else {
					String title = profittable.getValueAt(row, 0).toString();
					String in = profittable.getValueAt(row, 1).toString();
					String out = profittable.getValueAt(row, 2).toString();
					String pro = profittable.getValueAt(row, 3).toString();

					double income = Double.parseDouble(in);
					double outcome = Double.parseDouble(out);
					double profit = Double.parseDouble(pro);

					ProfitFormVO p = new ProfitFormVO(title, income, outcome,
							profit);
					exportExcel(p);
				}
			}
			repaint();
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
}
