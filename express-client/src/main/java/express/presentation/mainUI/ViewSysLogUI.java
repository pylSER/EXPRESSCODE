package express.presentation.mainUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import express.businessLogic.syslogBL.SysLog;
import express.businesslogicService.managerBLService.SysLogBLService;
import express.vo.LogVO;


public class ViewSysLogUI extends JPanel{

	private JTable logtable;
	private MyTableModel tableModel;
    private JScrollPane scrollPane; 
	
	public ViewSysLogUI(){
		setLayout(null);
		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);
		
		Font font = new Font("楷体",Font.PLAIN,20);
		Font f = new Font("仿宋",Font.PLAIN,18);
		
		String[] tableheader = {"操作","时间"};
		String[][] data = getoperationdata() ;
		Class[] typeArray = { Object.class, Object.class };
		
		tableModel = new MyTableModel(data, tableheader, typeArray);
		logtable = new JTable(tableModel);
		logtable.setRowHeight(40);
		logtable.setFont(f);
		logtable.setBounds(100, 50, 650, 550);	
		logtable.getTableHeader().setFont(font);
		logtable.getTableHeader().setReorderingAllowed(false);
		
		TableColumnModel columns = logtable.getColumnModel();
		TableColumn column1 = columns.getColumn(0); 
		column1.setPreferredWidth(150);
		TableColumn column2 = columns.getColumn(1); 
		column2.setPreferredWidth(500);
//		logtable.setBorder(BorderFactory.createEtchedBorder());
		
		scrollPane = new JScrollPane(logtable); 
		scrollPane.setFont(font);
		scrollPane.setBounds(100, 50, 650, 550);
		this.add(scrollPane);
		
	}
	
	private String[][] getoperationdata(){
		SysLogBLService log = new SysLog();
		ArrayList<LogVO> logvo = log.getSystemLog();
		String[][]  data = null;
		
		if(logvo!=null){
			data = new String[logvo.size()][2];
			for(int i = 0;i<logvo.size();i++){
				data[i][0] = logvo.get(i).getOperation();
				data[i][1] = logvo.get(i).getTime();
			}
		}
		
		return data;		
	}
}
