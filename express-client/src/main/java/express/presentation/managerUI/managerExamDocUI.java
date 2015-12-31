package express.presentation.managerUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import express.businessLogic.examDocumentBL.ExamDocument;
import express.businesslogicService.managerBLService.ExamDocumentBLService;
import express.po.GoodsArrivalStatus;
import express.presentation.mainUI.MainUIService;
import express.presentation.mainUI.MyCellRenderer;
import express.presentation.mainUI.MyTableModel;
import express.vo.ArrivalDocBusinessHallVO;
import express.vo.ArrivalDocTransCenterVO;
import express.vo.DeliverDocVO;
import express.vo.DocumentVO;
import express.vo.InDocVO;
import express.vo.OrderVO;
import express.vo.OutDocVO;
import express.vo.PaymentDocVO;
import express.vo.ReceiveDocVO;
import express.vo.ShipmentDocBusinessHallVO;
import express.vo.ShipmentDocTransCenterVO;
import express.vo.TransferDocVO;

public class managerExamDocUI extends JPanel {

	private JButton exam;
	private JPanel panel;
	private JTable[] table;
	private MyTableModel[] tableModel;
	private MyCellRenderer[] headerren;
	private ExamDocumentBLService examdoc;
	private ArrayList<OrderVO> orderarr;
	private ArrayList<ShipmentDocBusinessHallVO> shipbusarr;
	private ArrayList<ArrivalDocTransCenterVO> arrivaltransarr;
	private ArrayList<InDocVO> indocarr;
	private ArrayList<TransferDocVO> transdocarr;
	private ArrayList<OutDocVO> outdocarr;
	private ArrayList<ShipmentDocTransCenterVO> shiptransarr;
	private ArrayList<ArrivalDocBusinessHallVO> arrivalbusarr;
	private ArrayList<DeliverDocVO> deliverdocarr;
	private ArrayList<ReceiveDocVO> receivearr;
	private ArrayList<PaymentDocVO> paymentarr;
	private String changeunder = "<HTML><U>修改</U></HTML>";
	// private String confirmunder = "<HTML><U>确认</U></HTML>";
	// private String yesunder = "<HTML><U>已审批</U></HTML>";
	// private String nounder = "<HTML><U>未审批</U></HTML>";
	private Object[][] data1, data2, data3, data4, data5, data6, data7, data8,
			data9, data10, data11;
	private String[] header = { "选择", "单据类型", "单据名称", "修改" };

	public managerExamDocUI() {
		setLayout(null);
		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(50, 60, 750, 600);

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBounds(50, 60, 750, 600);
		this.add(scrollPane);

		Font font = new Font("楷体", Font.PLAIN, 18);
		Font f = new Font("仿宋", Font.PLAIN, 16);
		Listener listener = new Listener();
		examdoc = new ExamDocument();
		table = new JTable[11];
		tableModel = new MyTableModel[11];
		headerren = new MyCellRenderer[11];
		JScrollPane[] sp = new JScrollPane[11];

		Class[] typeArray = { Boolean.class, Object.class, Object.class,
				Object.class, };
		// table数据初始化
		orderarr = examdoc.getUEOrderlist();
		if (orderarr != null) {
			data1 = new Object[orderarr.size()][4];
			for (int i = 0; i < orderarr.size(); i++) {
				data1[i][0] = false;
				data1[i][1] = "订单";
				data1[i][2] = orderarr.get(i).getOrderID();
				data1[i][3] = changeunder;
			}
			tableModel[0] = new MyTableModel(data1, header, typeArray);
		}

		arrivalbusarr = examdoc.getUEBusinessHallArrivalDoclist();
		if (arrivalbusarr != null) {
			data2 = new Object[arrivalbusarr.size()][4];
			for (int i = 0; i < arrivalbusarr.size(); i++) {
				data2[i][0] = false;
				data2[i][1] = "营业厅到达单";
				data2[i][2] = arrivalbusarr.get(i).getOrderID();
				data2[i][3] = changeunder;
			}
			tableModel[1] = new MyTableModel(data2, header, typeArray);
		}

		arrivaltransarr = examdoc.getUETransCenterArrivalDoclist();
		if (arrivaltransarr != null) {
			data3 = new Object[arrivaltransarr.size()][4];
			for (int i = 0; i < arrivaltransarr.size(); i++) {
				data3[i][0] = false;
				data3[i][1] = "中转中心到达单";
				data3[i][2] = arrivaltransarr.get(i).getOrderID();
				data3[i][3] = changeunder;
			}
			tableModel[2] = new MyTableModel(data3, header, typeArray);
		}

		deliverdocarr = examdoc.getUEDeliverDoclist();
		if (deliverdocarr != null) {
			data4 = new Object[deliverdocarr.size()][4];
			for (int i = 0; i < deliverdocarr.size(); i++) {
				data4[i][0] = false;
				data4[i][1] = "派件单";
				data4[i][2] = deliverdocarr.get(i).getOrderID();
				data4[i][3] = changeunder;
			}
			tableModel[3] = new MyTableModel(data4, header, typeArray);
		}

		indocarr = examdoc.getUEInDoclist();
		if (indocarr != null) {
			data5 = new Object[indocarr.size()][4];
			for (int i = 0; i < indocarr.size(); i++) {
				data5[i][0] = false;
				data5[i][1] = "入库单";
				data5[i][2] = indocarr.get(i).getarrival() + "  "
						+ indocarr.get(i).getdeliveryNumber();
				data5[i][3] = changeunder;
			}
			tableModel[4] = new MyTableModel(data5, header, typeArray);
		}

		outdocarr = examdoc.getUEOutDoclist();
		if (outdocarr != null) {
			data6 = new Object[outdocarr.size()][4];
			for (int i = 0; i < outdocarr.size(); i++) {
				data6[i][0] = false;
				data6[i][1] = "出库单";
				data6[i][2] = outdocarr.get(i).getOrderID();
				data6[i][3] = changeunder;
			}
			tableModel[5] = new MyTableModel(data6, header, typeArray);
		}

		paymentarr = examdoc.getUEPaymentDoclist();
		if (paymentarr != null) {
			data7 = new Object[paymentarr.size()][4];
			for (int i = 0; i < paymentarr.size(); i++) {
				data7[i][0] = false;
				data7[i][1] = "付款单";
				data7[i][2] = paymentarr.get(i).getPaymentID();
				data7[i][3] = changeunder;
			}
			tableModel[6] = new MyTableModel(data7, header, typeArray);
		}

		receivearr = examdoc.getUEReceiveDoclist();
		if (receivearr != null) {
			data8 = new Object[receivearr.size()][4];
			for (int i = 0; i < receivearr.size(); i++) {
				data8[i][0] = false;
				data8[i][1] = "收款单";
				data8[i][2] = receivearr.get(i).getOrgID() + "  "
						+ receivearr.get(i).getReceiveDate();
				data8[i][3] = changeunder;
			}
			tableModel[7] = new MyTableModel(data8, header, typeArray);
		}

		shipbusarr = examdoc.getUEBusinessHallShipmentDoclist();
		if (shipbusarr != null) {
			data9 = new Object[shipbusarr.size()][4];
			for (int i = 0; i < shipbusarr.size(); i++) {
				data9[i][0] = false;
				data9[i][1] = "营业厅装车单";
				data9[i][2] = shipbusarr.get(i).getShipmentID();
				data9[i][3] = changeunder;
			}
			tableModel[8] = new MyTableModel(data9, header, typeArray);
		}

		shiptransarr = examdoc.getUETransCenterShipmentDoclist();
		if (shiptransarr != null) {
			data10 = new Object[shiptransarr.size()][4];
			for (int i = 0; i < shiptransarr.size(); i++) {
				data10[i][0] = false;
				data10[i][1] = "中转中心装车单";
				data10[i][2] = shiptransarr.get(i).getShipmentID();
				data10[i][3] = changeunder;
			}
			tableModel[9] = new MyTableModel(data10, header, typeArray);
		}

		transdocarr = examdoc.getUETransferDoclist();
		if (transdocarr != null) {
			data11 = new Object[transdocarr.size()][4];
			for (int i = 0; i < transdocarr.size(); i++) {
				data11[i][0] = false;
				data11[i][1] = "中转单";
				data11[i][2] = transdocarr.get(i).gettranscenterNumber() + "  "
						+ transdocarr.get(i).getdate();
				data11[i][3] = changeunder;
			}
			tableModel[10] = new MyTableModel(data11, header, typeArray);
		}

		for (int i = 0; i < 11; i++) {
			table[i] = new JTable(tableModel[i]);
			table[i].setRowHeight(40);
			table[i].getTableHeader().setReorderingAllowed(false);
			table[i].setBounds(0, 0, 750, 600);
			table[i].setFont(f);
			table[i].addMouseListener(listener);
			headerren[i] = new MyCellRenderer(table[i],tableModel[i]);
			table[i].getTableHeader().setDefaultRenderer(headerren[i]);
			sp[i] = new JScrollPane(table[i]);
			sp[i].setBounds(0, 0, 750, 600);
			panel.add(sp[i]);
		}

		exam = new JButton("审批");
		exam.setBounds(340, 10, 100, 30);
		exam.setFont(font);
		exam.addMouseListener(listener);
		this.add(exam);

	}

	private class Listener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == exam) {
				// 通过审批，设置单据审批状态
				for (int i = 0; i < tableModel[0].getRowCount(); i++) {
					if ((boolean) tableModel[0].getValueAt(i, 0)) {
						OrderVO vo = orderarr.get(i);
						vo.setState(true);
						examdoc.changeOrder(vo);
						orderarr.remove(i);
						tableModel[0].removeRow(i);
					}
				}
				
				for (int i = 0; i < tableModel[1].getRowCount(); i++) {
					if ((boolean) tableModel[1].getValueAt(i, 0)) {
						ArrivalDocBusinessHallVO vo = arrivalbusarr.get(i);
						vo.setState(true);
						examdoc.changeBusinessHallArrivalDoc(vo);
						arrivalbusarr.remove(i);
						tableModel[1].removeRow(i);
					}
				}
				
				for (int i = 0; i < tableModel[2].getRowCount(); i++) {
					if ((boolean) tableModel[2].getValueAt(i, 0)) {
						ArrivalDocTransCenterVO vo = arrivaltransarr.get(i);
						vo.setState(true);
						examdoc.changeTransCenterArrivalDoc(vo);
						arrivaltransarr.remove(i);
						tableModel[2].removeRow(i);
					}
				}
				
				for (int i = 0; i < tableModel[3].getRowCount(); i++) {
					if ((boolean) tableModel[3].getValueAt(i, 0)) {
						DeliverDocVO vo = deliverdocarr.get(i);
						vo.setState(true);
						examdoc.changeDeliverDoc(vo);
						deliverdocarr.remove(i);
						tableModel[3].removeRow(i);
					}
				}
				
				for (int i = 0; i < tableModel[4].getRowCount(); i++) {
					if ((boolean) tableModel[4].getValueAt(i, 0)) {
						InDocVO vo = indocarr.get(i);
						vo.setState(true);
						examdoc.changeInDoc(vo);
						indocarr.remove(i);
						tableModel[4].removeRow(i);
					}
				}
				
				for (int i = 0; i < tableModel[5].getRowCount(); i++) {
					if ((boolean) tableModel[5].getValueAt(i, 0)) {
						OutDocVO vo = outdocarr.get(i);
						vo.setState(true);
						examdoc.changeOutDoc(vo);
						outdocarr.remove(i);
						tableModel[5].removeRow(i);
					}
				}
				
				for (int i = 0; i < tableModel[6].getRowCount(); i++) {
					if ((boolean) tableModel[6].getValueAt(i, 0)) {
						PaymentDocVO vo = paymentarr.get(i);
						vo.setState(true);
						examdoc.changePaymentDoc(vo);
						paymentarr.remove(i);
						tableModel[6].removeRow(i);
					}
				}
				
				for (int i = 0; i < tableModel[7].getRowCount(); i++) {
					if ((boolean) tableModel[7].getValueAt(i, 0)) {
						ReceiveDocVO vo = receivearr.get(i);
						vo.setState(true);
						examdoc.changeReceiveDoc(vo);
						receivearr.remove(i);
						tableModel[7].removeRow(i);
					}
				}
				
				for (int i = 0; i < tableModel[8].getRowCount(); i++) {
					if ((boolean) tableModel[8].getValueAt(i, 0)) {
						ShipmentDocBusinessHallVO vo = shipbusarr.get(i);
						vo.setState(true);
						examdoc.changeBusinessHallShipmentDoc(vo);
						shipbusarr.remove(i);
						tableModel[8].removeRow(i);
					}
				}
				
				for (int i = 0; i < tableModel[9].getRowCount(); i++) {
					if ((boolean) tableModel[9].getValueAt(i, 0)) {
						ShipmentDocTransCenterVO vo = shiptransarr.get(i);
						vo.setState(true);
						examdoc.changeTransCenterShipmentDoc(vo);
						shiptransarr.remove(i);
						tableModel[9].removeRow(i);
					}
				}
				
				for (int i = 0; i < tableModel[10].getRowCount(); i++) {
					if ((boolean) tableModel[10].getValueAt(i, 0)) {
						TransferDocVO vo = transdocarr.get(i);
						vo.setState(true);
						examdoc.changeTransferDoc(vo);
						transdocarr.remove(i);
						tableModel[10].removeRow(i);
					}
				}
			}
			
			for (int i = 0; i < 11; i++) {
				if (e.getSource() == table[i]) {
					int row = table[i].getSelectedRow();
					int col = table[i].getSelectedColumn();

					if (col == 5) {
						if (tableModel[i].getValueAt(row, col).equals(
								changeunder)) {
							if(i==1){
								OrderChangeUI orui = new OrderChangeUI(tableModel[i],orderarr.get(row));
								orui.setVisible(true);
							}
						}
					}
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
