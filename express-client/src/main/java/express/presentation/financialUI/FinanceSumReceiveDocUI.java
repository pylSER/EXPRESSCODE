package express.presentation.financialUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import express.businessLogic.documentBL.SumReceiveDoc;
import express.businessLogic.infoManageBL.BankAccount;
import express.businessLogic.infoManageBL.OrgForManager;
import express.businesslogicService.businessSaleBLService.GetReceiveDocBLService;
import express.businesslogicService.financialBLService.BankAccountBLService;
import express.businesslogicService.managerBLService.OrgInfoManageService;
import express.po.OrgProperty;
import express.presentation.mainUI.DateChooser;
import express.presentation.mainUI.MainUIService;
import express.vo.ReceiveDocVO;
import express.vo.SumReceiveVO;

public class FinanceSumReceiveDocUI extends JPanel{

	private JComboBox businesshallcb,bankaccountcb;
	private JButton ok,exit,confirm;
	private JTextField totalmoney,datetf;
	private DateChooser datechooser;
	private JTable table;
	private DefaultTableModel tableModel;
	private String[][] data;
	private String[] header = {"收款日期","收款金额","收款快递员"};
	private String date,orgID,bankaccountID;
	private MainUIService m;
	
	public FinanceSumReceiveDocUI(MainUIService main){
		setLayout(null);
		m = main;
		this.setBounds(0, 0,850, 700);
		this.setBackground(Color.WHITE);
		
		Font font = new Font("楷体",Font.PLAIN,20);
		Font f = new Font("仿宋",Font.PLAIN,18);
		
		JLabel businesslabel = new JLabel("营业厅");
		businesslabel.setBounds(100, 50, 70, 30);
		businesslabel.setFont(font);
		this.add(businesslabel);
		
		businesshallcb=new JComboBox<String>(getbussinesshall());
		businesshallcb.setBounds(180, 50, 100, 30);
		businesshallcb.setFont(f);
		this.add(businesshallcb);
		
		JLabel datelabel = new JLabel("日期");
		datelabel.setBounds(310, 50, 70, 30);
		datelabel.setFont(font);
		this.add(datelabel);
		
		datetf = new JTextField(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		datetf.setBounds(380, 50, 150, 30);
		datetf.setFont(f);
		datetf.setEditable(false);
		this.add(datetf);
		
		datechooser = new DateChooser("yyyy-MM-dd", datetf);
		datechooser.setBounds(540, 45, 40, 40);
		this.add(datechooser);
		
		//收款日期、收款金额、收款快递员		
		data = null;
		tableModel = new DefaultTableModel(data, header);
		
		table = new JTable(tableModel);
		table.getTableHeader().setFont(f);
		table.setRowHeight(40);
		table.setFont(f);
		table.setBounds(100, 100, 650, 400);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setFont(font);
		scrollPane.setBounds(100, 100, 650, 400);
		this.add(scrollPane);
		
		JLabel totallabel = new JLabel("总额");
		totallabel.setBounds(100, 520, 50, 30);
		totallabel.setFont(font);
		this.add(totallabel);
		
		totalmoney = new JTextField();
		totalmoney.setBounds(150, 520, 100, 30);
		totalmoney.setEditable(false);
		totalmoney.setFont(f);
		totalmoney.setText("0");
		this.add(totalmoney);
		
		JLabel bankaccountl = new JLabel("银行账户");
		bankaccountl.setBounds(100, 580, 100, 30);
		bankaccountl.setFont(font);
		this.add(bankaccountl);
		
		bankaccountcb = new JComboBox<String>(getbankaccount());
		bankaccountcb.setBounds(200, 580, 250, 30);
		bankaccountcb.setFont(f);
		this.add(bankaccountcb);
		
		JListener listener = new JListener();
		
		confirm=new JButton("确认");
		confirm.setBounds(620, 50, 90, 30);
		confirm.setFont(font);
		confirm.addMouseListener(listener);
		this.add(confirm);
		
		ok=new JButton("合计");
		ok.setBounds(500, 580, 110, 30);
		ok.setFont(font);
		ok.addMouseListener(listener);
		this.add(ok);
		
//		exit=new JButton("取消");
//		exit.setBounds(400, 490, 110, 40);
//		exit.addMouseListener(listener);
//		this.add(exit);
	}
	
	private String[] getbussinesshall(){
		OrgInfoManageService org=new OrgForManager();
		ArrayList<String> list=org.getAllOrgNameByProperty(OrgProperty.SALE);
		String[] city;
		
		if(list!=null){
			city = new String[list.size()];
			for(int i = 0;i < list.size();i++){
				city[1] = list.get(i);
			}
		}
		else{
			city = new String[0];
		}
		
		return city;		
	}
	
	private String[] getbankaccount(){
		BankAccountBLService bankAccount=new BankAccount();
		ArrayList<String> list = bankAccount.getBankAccountName();
		
		String[] account;
		if(list!=null){
			account=new String[list.size()];
			for(int i = 0;i < list.size();i++){
				account[i] = list.get(i);
			}
		}
		else{
			account = new String[0];
		}
		return account;
	}
	
	private class JListener implements MouseListener {
		
		public void mouseClicked(MouseEvent e) {
			GetReceiveDocBLService getreceive = new SumReceiveDoc();
			if (e.getSource()==confirm){
				date = datetf.getText();
				if(businesshallcb.getSelectedItem()!=null)
					orgID = businesshallcb.getSelectedItem().toString();
				else
					orgID = null;
				
				SumReceiveVO rece = getreceive.getReceiveDocList(date, orgID);
				ArrayList<ReceiveDocVO> receList=rece.getReceiveDoc();
				if(receList!=null){
					data = new String[receList.size()][3];
					for(int i = 0;i<receList.size();i++){
						ReceiveDocVO vo=receList.get(i);
						data[i][0] = vo.getReceiveDate();
						data[i][1] = vo.getReceivePrice()+"";
						data[i][2] = vo.getDeliverManID();
					}
				}
				tableModel.setDataVector(data, header);
				totalmoney.setText(rece.getSum()+"");
				
			}else if (e.getSource()==ok){
				bankaccountID = bankaccountcb.getSelectedItem().toString();
				String sumString = totalmoney.getText().toString();
				double sum = Double.parseDouble(sumString);
				getreceive.getSum(sum, bankaccountID);
			}
			repaint();
		}

		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}
}
