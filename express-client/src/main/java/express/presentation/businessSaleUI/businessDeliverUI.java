package express.presentation.businessSaleUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import express.businessLogic.documentBL.DeliverDoc;
import express.businesslogicService.businessSaleBLService.BusinessSaleDeliverDocumentblService;
import express.presentation.mainUI.DateChooser;
import express.vo.DeliverDocVO;

public class businessDeliverUI extends JPanel {
	private JTextField textArea1;
	private JTextField textArea2;
	private JTextField textArea3;
	private JLabel tip1;
	private DateChooser datechooser;
	private JButton button_confirm;
	private JButton button_cancel;
	private String arriveDate, orderID, deliverManID;
	private DeliverDocVO vo;
	private Border border;
	private boolean complete = true;

	public businessDeliverUI() {
		int textlength = 150;
		int textwidth = 30;

		int labellength = 100;
		int labelwidth = 30;
		Font font = new Font("楷体", Font.PLAIN, 18);
		Font f = new Font("仿宋", Font.PLAIN, 16);
		Font buttonfont = new Font("隶书", Font.PLAIN, 18);

		setLayout(null);
		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		JListener listener = new JListener();
		Foclistener foclis = new Foclistener();

		textArea1 = new JTextField();
		textArea1.setBounds(300, 200, textlength, textwidth);
		textArea1.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		textArea1.setFont(f);
		textArea1.setEditable(false);
		this.add(textArea1);

		datechooser = new DateChooser("yyyy-MM-dd", textArea1);
		datechooser.setBounds(460, 195, 40, 40);
		this.add(datechooser);

		textArea2 = new JTextField();
		textArea2.setBounds(300, 200 + textwidth * 2, textlength, textwidth);
		textArea2.setFont(f);
		textArea2.addFocusListener(foclis);
		this.add(textArea2);
		border = textArea2.getBorder();
		
		tip1 = new JLabel(" * 填写错误");
		tip1.setBounds(460, 200 + textwidth * 2, 100, 30);
		tip1.setFont(font);
		tip1.setForeground(Color.RED);
		
		textArea3 = new JTextField();
		textArea3.setBounds(300, 200 + textwidth * 4, textlength, textwidth);
		textArea3.setFont(f);
		this.add(textArea3);
		
		JLabel label1 = new JLabel("到达日期");
		label1.setBounds(200, 200, labellength, labelwidth);
		label1.setFont(font);
		this.add(label1);

		JLabel label2 = new JLabel("派送员");
		label2.setBounds(200, 200 + labelwidth * 2, labellength, labelwidth);
		label2.setFont(font);
		this.add(label2);

		JLabel label3 = new JLabel("订单条形码号");
		label3.setBounds(200 - 30, 200 + labelwidth * 4, labellength + 30,
				labelwidth);// 字数太多，只能把label拉长一些
		label3.setFont(font);
		this.add(label3);

		button_confirm = new JButton("确定");
		button_confirm.setBounds(230, 490, 120, 30);
		button_confirm.setFont(buttonfont);
		button_confirm.addMouseListener(listener);
		this.add(button_confirm);

		button_cancel = new JButton("取消");
		button_cancel.setBounds(400, 490, 120, 30);
		button_cancel.setFont(buttonfont);
		button_cancel.addMouseListener(listener);
		this.add(button_cancel);

		this.addMouseListener(listener);
	}

	private class Foclistener implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == textArea2) {
				textArea2.setBorder(border);
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
			// TODO Auto-generated method stub
			requestFocus();
			if (e.getSource() == button_cancel) {
				
				tip1.setVisible(false);
				textArea1.setText(new SimpleDateFormat("yyyy-MM-dd")
				.format(new Date()));
				textArea2.setText("");
				textArea3.setText("");
				textArea2.setBorder(border);
				
			} else if (e.getSource() == button_confirm) {
				
				arriveDate = textArea1.getText();
				deliverManID = textArea1.getText();
				orderID = textArea3.getText();
				
				if(deliverManID.isEmpty()){
					complete = false;
					textArea2.setBorder(new LineBorder(Color.RED));
				}
				
				if(orderID.isEmpty()){
					complete = false;
					textArea3.setBorder(new LineBorder(Color.RED));
				}
				
				if (!complete) {
					JOptionPane.showMessageDialog(null, "信息未填写完整", "提示",
							JOptionPane.ERROR_MESSAGE);
				} else {
					vo = new DeliverDocVO(arriveDate, orderID, deliverManID);
					BusinessSaleDeliverDocumentblService bsd = new DeliverDoc();
					
					if(!bsd.isOrderIDavailable(orderID)){
						JOptionPane.showMessageDialog(null, "订单号错误", "错误",
								JOptionPane.ERROR_MESSAGE);
					}else{
						if (!bsd.addDeliverDoc(vo)) {
							JOptionPane.showMessageDialog(null, "派件单生成失败", "错误",
									JOptionPane.ERROR_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "生成派件单成功", "提示",
									JOptionPane.INFORMATION_MESSAGE);
							bsd.endDeliverDoc();
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
