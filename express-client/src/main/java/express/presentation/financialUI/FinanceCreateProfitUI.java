package express.presentation.financialUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import express.businessLogic.statisticBL.ProfitStatistic;
import express.businesslogicService.financialBLService.ProfitFinanceBLService;
import express.presentation.mainUI.MainUIService;
import express.vo.ProfitFormVO;

public class FinanceCreateProfitUI extends JPanel {

	private MainUIService m;
	private JButton ok, exit, count;
	private JTextField time,income,outcome,profit;
	/*private JLabel success;
	private JTable profittable;
	private JScrollPane scrollPane;
	private String[] tableheader = { "统计时间", "总收入", "总支出", "总利润" };
	private String[][] data = { { " ", " ", " ", " " } };
	private DefaultTableModel tableModel;*/

	public FinanceCreateProfitUI(MainUIService main) {
		setLayout(null);
		m = main;
		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		Font font = new Font("楷体", Font.PLAIN, 20);
		Font f = new Font("仿宋", Font.PLAIN, 18);
		
		JLabel timeLabel = new JLabel("统计时间");
		timeLabel.setBounds(185, 90, 100, 30);
		timeLabel.setFont(font);
		this.add(timeLabel);
		
		JLabel incomeLabel = new JLabel("总收入");
		incomeLabel.setBounds(200, 190, 100, 30);
		incomeLabel.setFont(font);
		this.add(incomeLabel);
		
		JLabel outcomeLabel = new JLabel("总支出");
		outcomeLabel.setBounds(200, 290, 100, 30);
		outcomeLabel.setFont(font);
		this.add(outcomeLabel);
		
		JLabel profitLabel = new JLabel("总利润");
		profitLabel.setBounds(200, 390, 100, 30);
		profitLabel.setFont(font);
		this.add(profitLabel);
		
		time = new JTextField();
		time.setBounds(300, 90, 200, 30);
		time.setEditable(false);
		time.setFont(f);
		this.add(time);
		
		income = new JTextField();
		income.setBounds(300, 190, 200, 30);
		income.setEditable(false);
		income.setFont(f);
		this.add(income);
		
		outcome = new JTextField();
		outcome.setBounds(300, 290, 200, 30);
		outcome.setEditable(false);
		outcome.setFont(f);
		this.add(outcome);
		
		profit = new JTextField();
		profit.setBounds(300, 390, 200, 30);
		profit.setEditable(false);
		profit.setFont(f);
		this.add(profit);
		//
		/*tableModel = new DefaultTableModel(data, tableheader);
		profittable = new JTable(tableModel);
		profittable.getTableHeader().setFont(f);
		profittable.setRowHeight(40);

		TableColumnModel columns = profittable.getColumnModel();
		TableColumn column1 = columns.getColumn(0);
		column1.setPreferredWidth(200);
		TableColumn column2 = columns.getColumn(1);
		column2.setPreferredWidth(160);
		TableColumn column3 = columns.getColumn(2);
		column2.setPreferredWidth(160);
		TableColumn column4 = columns.getColumn(3);
		column2.setPreferredWidth(160);
		// profittable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		profittable.setFont(f);
		profittable.setBounds(100, 100, 650, 300);
		// logtable.setBorder(BorderFactory.createEtchedBorder());
		// this.add(logtable);

		scrollPane = new JScrollPane(profittable);
		scrollPane.setFont(font);
		// scrollPane.setViewportView(logtable);
		scrollPane.setBounds(100, 100, 650, 300);
		this.add(scrollPane);*/

		Listener listen = new Listener();

		count = new JButton("统计成本收益");
		count.setBounds(300, 480, 200, 40);
		count.setFont(new Font("隶书", Font.PLAIN, 20));
		count.addMouseListener(listen);
		this.add(count);
		
		ok = new JButton("添加表格");
		ok.setBounds(250, 480, 120, 40);
		ok.setVisible(false);
		ok.setFont(new Font("隶书", Font.PLAIN, 20));
		ok.addMouseListener(listen);
		this.add(ok);

		exit = new JButton("取消");
		exit.setBounds(420, 480, 120, 40);
		exit.setVisible(false);
		exit.setFont(new Font("隶书", Font.PLAIN, 20));
		exit.addMouseListener(listen);
		this.add(exit);

	}

	private void getdata() {
		ProfitFinanceBLService pf = new ProfitStatistic();
		//String[][] newdata = new String[1][4];
		ProfitFormVO vo=pf.createProfitForm();
		/*newdata[0][0] = vo.getTitle();
		newdata[0][1] = vo.getIncome()+"";
		newdata[0][2] = vo.getOutCome()+"";
		newdata[0][3] = vo.getProfit()+"";
		tableModel.setDataVector(newdata, tableheader);*/
		time.setText(vo.getTitle());
		income.setText(vo.getIncome()+"");
		outcome.setText(vo.getOutCome()+"");
		profit.setText(vo.getProfit()+"");
	}
	
	private void addData(){
		ProfitFinanceBLService pf = new ProfitStatistic();
		String t=time.getText();
		String i=income.getText();
		String o=outcome.getText();
		String p=profit.getText();
		try{
			double in=Double.parseDouble(i);
			double out=Double.parseDouble(o);
			double pro=Double.parseDouble(p);
			ProfitFormVO vo=new ProfitFormVO(t,in,out,pro);
			boolean succ=pf.addProfitForm(vo);
			//Font font = new Font("楷体", Font.PLAIN, 21);
			if(succ){
				JOptionPane.showConfirmDialog(null,
					       "添 加 成 功！", null,JOptionPane.DEFAULT_OPTION,
					       JOptionPane.INFORMATION_MESSAGE, null);
			}
			else{
				JOptionPane.showConfirmDialog(null,
					       "添 加 失 败！", null,JOptionPane.DEFAULT_OPTION,
					       JOptionPane.WARNING_MESSAGE, null);
			}
			count.setVisible(true);
			ok.setVisible(false);
			exit.setVisible(false);
			repaint();
		}catch(Exception e){
			
		}
	}

	private class Listener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == exit) {
				time.setText("");
				income.setText("");
				outcome.setText("");
				profit.setText("");
				count.setVisible(true);
				ok.setVisible(false);
				exit.setVisible(false);
			} else if (e.getSource() == ok) {
				addData();
			}
			else if(e.getSource() == count){
				getdata();
				count.setVisible(false);
				ok.setVisible(true);
				exit.setVisible(true);
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
