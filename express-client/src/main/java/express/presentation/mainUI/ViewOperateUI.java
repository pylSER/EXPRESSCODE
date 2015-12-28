package express.presentation.mainUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import express.businessLogic.IDKeeper;
import express.businessLogic.statisticBL.OperateStatistic;
import express.businesslogicService.financialBLService.OperateFinanceBLService;
import express.businesslogicService.financialBLService.OperateManagerBLService;
import express.po.PaymentItem;
import express.vo.OperateFormVO;
import express.vo.ReceiveDocVO;

public class ViewOperateUI extends JPanel {

	private JPanel[] panelList = null;
	private JTabbedPane tabpane;
	private String[][] docs = null;
	// private JTable[] table;
	// private DefaultTableModel[] tableModel;
	// private JTextField nametf, phonetf, datetf, idtf;
	// private JComboBox gendercb, positioncb, orgcb;
	// private String[][] header;
	// private String[][][] data;
	private JButton excel, exit;
	//	private int leftside = 10;
	//	private int textwidth = 120;
	//	private int textheight = 30;
	//	private int labelwidth = 50;
	//	private int labelheight = 30;
	private int tablewidth = 720;
	private int tableheight = 500;
	private Font font = new Font("楷体", Font.PLAIN, 18);
	private Font f = new Font("仿宋", Font.PLAIN, 16);

	public ViewOperateUI() {
		setLayout(null);
		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);
		Font font2 = new Font("楷体", Font.BOLD, 18);
		
		JLabel title = new JLabel("经 营 状 态 表",JLabel.CENTER);
		title.setBounds(50, 50, tablewidth, 30);
		title.setFont(font2);
		this.add(title);
		
		tabpane = new JTabbedPane(JTabbedPane.TOP);
		initPanelList();
		tabpane.setFont(f);
		tabpane.setBackground(Color.white);
		tabpane.setBounds(50, 80, tablewidth, tableheight);
		this.add(tabpane);

		Listener listen = new Listener();

		excel = new JButton("导出到Excel");
		excel.setBounds(350, 620, 150, 40);
		excel.setVisible(true);
		excel.setBackground(Color.WHITE);
		excel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		excel.setFont(new Font("隶书", Font.PLAIN, 20));
		excel.addMouseListener(listen);
		this.add(excel);

//		exit = new JButton("返回");
//		exit.setBounds(430, 620, 150, 40);
//		exit.setVisible(true);
//		exit.setBackground(Color.WHITE);
//		exit.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
//		exit.setFont(new Font("隶书", Font.PLAIN, 20));
//		exit.addMouseListener(listen);
//		this.add(exit);

	}

	private void initPanelList() {
		OperateManagerBLService operate = new OperateStatistic();
		ArrayList<String> list = operate.getOperateFormListTitle();

		if (list != null) {
			panelList = new JPanel[list.size()];
			for (int i = 0; i < list.size(); i++) {
				JPanel p = new JPanel();
				String title = list.get(i);
				panelList[i] = p;
				initPanel(i);
				tabpane.add(title,p);
			}
		}
	}

	private void initPanel(int index) {

		JPanel p = panelList[index];
		p.setLayout(null);
		getdocs(index);

		String[] head = { "收款日期", "收款金额", "付款日期", "付款金额", "付款条目" };

		DefaultTableModel tableModel = new DefaultTableModel(docs, head);
		JTable operatetable = new JTable(tableModel);
		operatetable.getTableHeader().setFont(f);
		operatetable.setRowHeight(40);
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		//设置居中
		r.setHorizontalAlignment(JLabel.CENTER);
		operatetable.setDefaultRenderer(Object.class, r);
		operatetable.setFont(f);
		operatetable.setBounds(2, 0, tablewidth-7, tableheight-30);
		// logtable.setBorder(BorderFactory.createEtchedBorder());
		// this.add(logtable);
		
		JScrollPane scrollPane = new JScrollPane(operatetable);
		scrollPane.setFont(font);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		// scrollPane.setViewportView(logtable);
		scrollPane.setBounds(2, 0, tablewidth-7, tableheight-30);
		p.add(scrollPane);

	}

	private void getdocs(int index) {

		OperateManagerBLService operate = new OperateStatistic();
		
		OperateFormVO vo = operate.getOperateForm(index);
		ArrayList<ReceiveDocVO> receList = vo.getReceiveDoc();
		ArrayList<PaymentItem> payList = vo.getPaymentDoc();
		
		int lenRece = 0;
		int lenPay = 0;
		int max = 0;

		if (receList != null) {
			lenRece = receList.size();
		}
		if (payList != null) {
			lenPay = payList.size();
		}

		max = Math.max(lenRece, lenPay);

		docs = new String[max][5];
		for (int i = 0; i < lenRece; i++) {
			ReceiveDocVO rece = receList.get(i);
			docs[i][0] = rece.getReceiveDate();
			docs[i][1] = rece.getReceivePrice() + "";
		}
		for (int i = 0; i < lenPay; i++) {
			PaymentItem pay = payList.get(i);
			docs[i][2] = pay.getDate();
			docs[i][3] = pay.getSum() + "";
			docs[i][4] = pay.getEntry();
		}
	}
	
	private void exportExcel(int index){
		OperateManagerBLService operate = new OperateStatistic();
		
		OperateFormVO vo = operate.getOperateForm(index);
		
		boolean succ = operate.exportExcel(vo);
		if(succ){
			JOptionPane.showConfirmDialog(null,
				       "导 出 成 功！", null,JOptionPane.DEFAULT_OPTION,
				       JOptionPane.INFORMATION_MESSAGE, null);
		}
		else{
			JOptionPane.showConfirmDialog(null,
				       "导 出 失 败！", null,JOptionPane.DEFAULT_OPTION,
				       JOptionPane.WARNING_MESSAGE, null);
		}
	}

	private class Listener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == excel) {
				int index = tabpane.getSelectedIndex();
				if(index <0){
					JOptionPane.showConfirmDialog(null,
						       "未 选 择 表 格！", null,JOptionPane.DEFAULT_OPTION,
						       JOptionPane.WARNING_MESSAGE, null);
				} else {
					exportExcel(index);
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
}
