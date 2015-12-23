package express.presentation.financialUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import express.businessLogic.statisticBL.OperateStatistic;
import express.businesslogicService.financialBLService.OperateFinanceBLService;
import express.po.PaymentItem;
import express.presentation.mainUI.DateChooser;
import express.presentation.mainUI.MainUIService;
import express.vo.OperateFormVO;
import express.vo.PaymentDocVO;
import express.vo.ReceiveDocVO;

public class FinanceCreateOperateUI extends JPanel {

	private MainUIService m;
	private JButton ok, exit,count;
	private JTable operatetable;
	private JScrollPane scrollPane;
	private JTextField startdatetf, enddatetf;
	private DateChooser datechoosers, datechoosere;
	private String beginDate ="", endDate = "";
	private String[] tableheader = { "收款日期", "收款金额", "付款日期", "付款金额", "付款条目" };
	private DefaultTableModel tableModel;
	private String[][] data= null;
	public FinanceCreateOperateUI(MainUIService main) {
		setLayout(null);
		m = main;
		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		int textlength = 150;
		int textwidth = 30;
		Font font = new Font("楷体", Font.PLAIN, 18);
		Font f = new Font("仿宋", Font.PLAIN, 16);

		JLabel begindatel = new JLabel("开始日期");
		begindatel.setFont(font);
		begindatel.setBounds(100, 50, 100, 40);
		this.add(begindatel);

		startdatetf = new JTextField();
		startdatetf.setFont(f);
		startdatetf.setBounds(210, 50, textlength, textwidth);
		startdatetf.setEditable(false);
		this.add(startdatetf);

		datechoosers = new DateChooser("yyyy-MM-dd", startdatetf);
		datechoosers.setBounds(370, 45, 40, 40);
		this.add(datechoosers);

		JLabel enddatel = new JLabel("结束日期");
		enddatel.setFont(font);
		enddatel.setBounds(430, 50, 100, 40);
		this.add(enddatel);

		enddatetf = new JTextField();
		enddatetf.setFont(f);
		enddatetf.setBounds(540, 50, textlength, textwidth);
		enddatetf.setEditable(false);
		this.add(enddatetf);

		datechoosere = new DateChooser("yyyy-MM-dd", enddatetf);
		datechoosere.setBounds(700, 45, 40, 40);
		this.add(datechoosere);
	
		tableModel = new DefaultTableModel(data, tableheader);
		operatetable = new JTable(tableModel);
		operatetable.getTableHeader().setFont(f);
		operatetable.setRowHeight(40);
		// TableColumnModel columns = operatetable.getColumnModel();
		// TableColumn column1 = columns.getColumn(0);
		// column1.setPreferredWidth(200);
		// TableColumn column2 = columns.getColumn(1);
		// column2.setPreferredWidth(160);
		// TableColumn column3 = columns.getColumn(2);
		// column2.setPreferredWidth(160);
		// TableColumn column4 = columns.getColumn(3);
		// column2.setPreferredWidth(160);
		// profittable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		operatetable.setFont(f);
		operatetable.setBounds(100, 100, 650, 450);
		// logtable.setBorder(BorderFactory.createEtchedBorder());
		// this.add(logtable);

		scrollPane = new JScrollPane(operatetable);
		scrollPane.setFont(font);
		// scrollPane.setViewportView(logtable);
		scrollPane.setBounds(100, 100, 650, 450);
		this.add(scrollPane);

		Listener listen = new Listener();

		count = new JButton("查看经营状态");
		count.setBounds(300, 570, 200, 40);
		count.setFont(new Font("隶书", Font.PLAIN, 20));
		count.setVisible(true);
		count.addMouseListener(listen);
		this.add(count);
		
		ok = new JButton("添加表格");
		ok.setBounds(250, 570, 120, 40);
		ok.setFont(new Font("隶书", Font.PLAIN, 20));
		ok.setVisible(false);
		ok.addMouseListener(listen);
		this.add(ok);

		exit = new JButton("取消");
		exit.setBounds(420, 570, 120, 40);
		exit.setFont(new Font("隶书", Font.PLAIN, 20));
		exit.setVisible(false);
		exit.addMouseListener(listen);
		this.add(exit);

	}

	private void getdocs() {
		String[][] docs = null;
		
		if (endDate.equals("")) {
			Date d = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String time = format.format(d);
			endDate = time;
		}
		if(beginDate.equals("")){	
			beginDate = " ";
		}
		
		OperateFinanceBLService oper = new OperateStatistic();
		
		if (!oper.checkDateAvailable(beginDate, endDate)) {
			
			JOptionPane.showConfirmDialog(null, "日 期 选 择 错 误：\n"
					+ "起 始 日 期 晚 于 终 止 日 期", null, JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE, null);
		} else {
			OperateFormVO vo = oper.createOperateForm(beginDate, endDate);
			
			beginDate = vo.getStartDate();
			endDate = vo.getEndDate();
			ArrayList<ReceiveDocVO> receList = vo.getReceiveDoc();
			ArrayList<PaymentItem> payList = vo.getPaymentDoc();
			int lenRece = 0;
			int lenPay = 0;
			int max = 0;
			
			if (receList != null) {
				lenRece = receList.size();
			}
			if(payList != null){
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
			
			tableModel.setDataVector(docs, tableheader);
		}

	}
	
	private void addOperateForm(){
		
		OperateFinanceBLService oper = new OperateStatistic();
		boolean succ = oper.addOperateForm(beginDate, endDate);
		if (succ) {
			JOptionPane.showConfirmDialog(null, "添 加 成 功！", null,
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null);
		} else {
			JOptionPane.showConfirmDialog(null, "添 加 失 败！", null,
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null);
		}
		count.setVisible(true);
		ok.setVisible(false);
		exit.setVisible(false);
		repaint();
	}

	private class Listener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == exit) {
				startdatetf.setText("");
				enddatetf.setText("");
				tableModel.setRowCount(0);
				repaint();
				count.setVisible(true);
				ok.setVisible(false);
				exit.setVisible(false);
			} else if (e.getSource() == count) {
				count.setVisible(false);
				ok.setVisible(true);
				exit.setVisible(true);
				beginDate = startdatetf.getText();
				endDate = enddatetf.getText();
				getdocs();
				startdatetf.setText(beginDate);
				enddatetf.setText(endDate);
			} else if(e.getSource() == ok) {
				addOperateForm();
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
