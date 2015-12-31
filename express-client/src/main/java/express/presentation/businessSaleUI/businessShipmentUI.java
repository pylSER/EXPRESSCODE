package express.presentation.businessSaleUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import express.businessLogic.IDKeeper;
import express.businesslogicService.businessSaleBLService.BusinessSaleShipmentDocumentblService;
import express.businessLogic.documentBL.ShipmentDocBusinessHall;
import express.presentation.mainUI.DateChooser;
import express.presentation.mainUI.MainUIService;
import express.vo.ShipmentDocBusinessHallVO;

public class businessShipmentUI extends JPanel {

	private JTextField textArea1, textArea2, textArea8;
	private JTextField[] tf;
	private JTextArea textArea7;
	private JButton button_confirm;
	private JButton button_cancel;
	private DateChooser datechooser;
	private String date, businessHallNumber, transID, arrivalplace, vanID,
			checkMan, transMan, shipmentID, startPlace;
	private ArrayList<String> orderID;
	private double money;
	private Border border, border1;
	private boolean complete = true;
	private BusinessSaleShipmentDocumentblService bssd;

	public businessShipmentUI() {
		int textlength = 200;
		int textwidth = 35;

		int labellength = 100;
		int labelwidth = 30;

		int leftside = 360;
		int base = 140;

		Font font = new Font("楷体", Font.PLAIN, 18);
		Font f = new Font("仿宋", Font.PLAIN, 16);
		bssd = new ShipmentDocBusinessHall();
		
		setLayout(null);
		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		JListener listener = new JListener();
		Foclistener foclis = new Foclistener();
		tf = new JTextField[7];

		shipmentID = bssd.getShipmentDocID();
		
		for (int i = 0; i < 7; i++) {
			tf[i] = new JTextField();
			if (i == 0) {
				tf[i].setBounds(leftside, base - labelwidth * 4, textlength,
						textwidth);
				border = tf[i].getBorder();
			}
			if (i == 1){
				tf[i].setBounds(leftside, base + labelwidth * 2, textlength,
						textwidth);
				tf[i].setText(shipmentID);
				tf[i].setEditable(false);
			}
			if (i == 2)
				tf[i].setBounds(190, base + labelwidth * 4, textlength,
						textwidth);
			if (i == 3)
				tf[i].setBounds(520, base + labelwidth * 4, textlength,
						textwidth);
			if (i > 3)
				tf[i].setBounds(leftside, base + labelwidth * (2 * i - 2),
						textlength, textwidth);
			tf[i].setFont(f);
			tf[i].addFocusListener(foclis);
			this.add(tf[i]);
		}

		textArea1 = new JTextField();
		textArea1.setBounds(leftside, base - 2 * labelwidth, textlength,
				textwidth);
		textArea1.setFont(f);
		textArea1
				.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		textArea1.setEditable(false);
		this.add(textArea1);

		datechooser = new DateChooser("yyyy-MM-dd", textArea1);
		datechooser.setBounds(leftside + textlength + 10, base - 2 * labelwidth
				- 5, 40, 40);
		this.add(datechooser);

		businessHallNumber = IDKeeper.getOrgID();
		textArea2 = new JTextField();
		textArea2.setBounds(leftside, base, textlength, textwidth);
		textArea2.setFont(f);
		textArea2.setText(businessHallNumber);
		textArea2.setEditable(false);
		this.add(textArea2);

		textArea7 = new JTextArea();
		textArea7.setBounds(leftside, base + labelwidth * 12, textlength,
				textwidth * 2);
		textArea7.setFont(f);
		textArea7.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		textArea7.setLineWrap(true);
		textArea7.setWrapStyleWord(true);
		textArea7.addFocusListener(foclis);
		border1 = textArea7.getBorder();

		JScrollPane scrollPane = new JScrollPane(textArea7);
		scrollPane.setFont(font);
		scrollPane.setBounds(leftside, base + labelwidth * 12, textlength,
				textwidth * 2);
		this.add(scrollPane);

		textArea8 = new JTextField();
		textArea8.setBounds(leftside, base + labelwidth * 15, textlength,
				textwidth);
		textArea8.setFont(f);
		textArea8.setEditable(false);
		this.add(textArea8);

		JLabel label0 = new JLabel("装车单编号");
		label0.setBounds(200, base - labelwidth * 4, labellength, labelwidth);
		label0.setFont(font);
		this.add(label0);

		JLabel label1 = new JLabel("装车日期");
		label1.setBounds(200, base - labelwidth * 2, labellength, labelwidth);
		label1.setFont(font);
		this.add(label1);

		JLabel label2 = new JLabel("本营业厅编号");
		label2.setBounds(200 - 20, base, labellength + 30, labelwidth);
		label2.setFont(font);
		this.add(label2);

		JLabel label3 = new JLabel("汽运编号");
		label3.setBounds(200, base + labelwidth * 2, labellength, labelwidth);
		label3.setFont(font);
		this.add(label3);

		JLabel label9 = new JLabel("出发地");
		label9.setBounds(100, base + labelwidth * 4, labellength, labelwidth);
		label9.setFont(font);
		this.add(label9);

		JLabel label10 = new JLabel("到达地");
		label10.setBounds(440, base + labelwidth * 4, labellength, labelwidth);
		label10.setFont(font);
		this.add(label10);

		JLabel label4 = new JLabel("车辆代号");
		label4.setBounds(200, base + labelwidth * 6, labellength, labelwidth);
		label4.setFont(font);
		this.add(label4);

		JLabel label5 = new JLabel("监装员");
		label5.setBounds(200, base + labelwidth * 8, labellength, labelwidth);
		label5.setFont(font);
		this.add(label5);

		JLabel label6 = new JLabel("押运员");
		label6.setBounds(200, base + labelwidth * 10, labellength, labelwidth);
		label6.setFont(font);
		this.add(label6);

		JLabel label7 = new JLabel("本次装箱所有订单条形号码");
		label7.setBounds(200 - 100, base + labelwidth * 12, labellength + 130,
				labelwidth);
		label7.setFont(font);
		this.add(label7);

		JLabel label8 = new JLabel("运费");
		label8.setBounds(200, base + labelwidth * 15, labellength, labelwidth);
		label8.setFont(font);
		this.add(label8);

		button_confirm = new JButton("确定");
		button_confirm.setBounds(280, 640, 100, 30);
		button_confirm.setFont(font);
		button_confirm.addMouseListener(listener);
		this.add(button_confirm);

		button_cancel = new JButton("取消");
		button_cancel.setBounds(480, 640, 100, 30);
		button_cancel.setFont(font);
		button_cancel.addMouseListener(listener);
		this.add(button_cancel);

	}

	private class Foclistener implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			for (int i = 0; i < 7; i++) {
				if (e.getSource() == tf[i])
					tf[i].setBorder(border);
			}
			if (e.getSource() == textArea7) {
				textArea7.setBorder(border1);
			}
			updateUI();
		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub

		}

	}

	private class JListener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			requestFocus();
			if (e.getSource() == button_confirm) {

				date = textArea1.getText();
				transID = tf[1].getText();
				arrivalplace = tf[2].getText();
				startPlace = tf[3].getText();
				vanID = tf[4].getText();
				checkMan = tf[5].getText();
				transMan = tf[6].getText();

				for (int i = 0; i < 7; i++) {
					if (tf[i].getText().isEmpty()) {
						tf[i].setBorder(new LineBorder(Color.RED));
						complete = false;
					}
				}

				String[] temp = textArea7.getText().split("\n");
				if (textArea7.getText().isEmpty()) {
					textArea7.setBorder(new LineBorder(Color.RED));
					complete = false;
				}
				
				orderID = new ArrayList<String>();
				for (int i = 0; i < temp.length; i++) {
					orderID.add(temp[i]);
				}

				if (!complete) {
					JOptionPane.showMessageDialog(null, "信息未填写完整", "提示",
							JOptionPane.ERROR_MESSAGE);
				} else {
					ShipmentDocBusinessHallVO sdb = new ShipmentDocBusinessHallVO(
							date, businessHallNumber, transID, arrivalplace,
							vanID, checkMan, transMan, orderID, money,
							shipmentID, startPlace);
			
					money = bssd.getShipmentfee(sdb);
					boolean b = (money>0);
					if(!b){
						JOptionPane.showMessageDialog(null, "有订单未生成或订单号填写错误", "提示",
								JOptionPane.ERROR_MESSAGE);
					}else{
					textArea8.setText(money + "");
					sdb.setMoney(money);
					if (!bssd.addShipmentDoc(sdb)) {
						JOptionPane.showMessageDialog(null, "生成装车单失败", "提示",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "生成装车单成功", "提示",
								JOptionPane.INFORMATION_MESSAGE);
						bssd.endShipmentDoc();
					}
					}
				}
				complete = true;
			} else if (e.getSource() == button_cancel) {
				for (int i = 0; i < 7; i++) {
					tf[i].setText("");
					tf[i].setBorder(border);
				}
				textArea1.setText(new SimpleDateFormat("yyyy-MM-dd")
						.format(new Date()));
				textArea7.setText("");
				textArea7.setBorder(border1);
				textArea8.setText("");
			}
			updateUI();
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
