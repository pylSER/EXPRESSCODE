package express.presentation.transRepoUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import express.presentation.mainUI.DateChooser;
import express.presentation.mainUI.MainUIService;

public class OutUI extends JPanel {

	// private JButton button_out;
	// private JButton button_return;
	private JButton button_cancel;
	private JButton button_confirm;
	private JTextField textArea1,textArea4,textArea5,textArea4_1,datetf;
	private String number, date, arrival, district, row, shelf, position;
	private String transmode;
	private MainUIService m;
	private DateChooser datechooser;
	private JComboBox tranway,combobox;
	
	private JLabel label2_1;
	
	public OutUI(MainUIService main) {

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

		textArea1 = new JTextField("快递编号");
		textArea1.setBounds(300, 100, textlength, textwidth);
		textArea1.setFont(f);
//		textArea1.setBackground(Color.BLUE);
		// textAreaOutput.setSelectedTextColor(Color.RED);
//		textArea1.setLineWrap(true); // 激活自动换行功能
//		textArea1.setWrapStyleWord(true);// 激活断行不断字功能
		this.add(textArea1);

		datetf = new JTextField();
		datetf.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		datetf.setBounds(300, 100 + textwidth * 2, textlength, textwidth);
		datetf.setFont(f);
		datetf.setEditable(false);
		this.add(datetf);
		
		datechooser = new DateChooser("yyyy-MM-dd", datetf);
		datechooser.setBounds(460, 155, 40, 40);
		this.add(datechooser);
		

		String[] combo={"北京","南京","上海"};
		combobox=new JComboBox(combo);
		combobox.setBounds(300, 100 + textwidth * 4, textlength, textwidth);
		combobox.setFont(font);
		this.add(combobox);

		
//		textArea4 = new JTextField("装运形式");
//		textArea4.setBounds(300, 100 + textwidth * 6, textlength, textwidth);
//		textArea4.setFont(f);
////		textArea4.setBackground(Color.BLUE);
////		textArea4.setLineWrap(true);
////		textArea4.setWrapStyleWord(true);
//		this.add(textArea4);
		
		FocListener foclis = new FocListener();

		
		String[] tranways = { "汽车", "火车", "飞机" };
		tranway = new JComboBox(tranways);
		tranway.setFont(f);
		tranway.setBounds(300, 100 + textwidth * 6, labellength, textwidth);
		tranway.addFocusListener(foclis);
		this.add(tranway);
		
		label2_1 = new JLabel("车次号");
		label2_1.setBounds(440, 100 + textwidth * 6, labellength,
				labelwidth);
		label2_1.setFont(font);
		this.add(label2_1);
		
		
		textArea4_1 = new JTextField();
		textArea4_1.setBounds(520, 100 + textwidth * 6, textlength, textwidth);
		textArea4_1.setFont(f);
		this.add(textArea4_1);
		
		

		textArea5 = new JTextField("中转单编号");
		textArea5.setBounds(300, 100 + textwidth * 8, textlength, textwidth);
		textArea5.setFont(f);
//		textArea5.setBackground(Color.BLUE);
//		textArea5.setLineWrap(true);
//		textArea5.setWrapStyleWord(true);
		this.add(textArea5);

		JLabel label1 = new JLabel("快递编号");
		label1.setBounds(200, 100, labellength, labelwidth);
		label1.setFont(font);
		this.add(label1);

		JLabel label2 = new JLabel("出库日期");
		label2.setBounds(200, 100 + labelwidth * 2, labellength, labelwidth);
		label2.setFont(font);
		this.add(label2);

		JLabel label3 = new JLabel("目的地");
		label3.setBounds(200, 100 + labelwidth * 4, labellength, labelwidth);
		label3.setFont(font);
		this.add(label3);
				

		JLabel label4 = new JLabel("装运形式");
		label4.setBounds(200, 100 + labelwidth * 6, labellength, labelwidth);
		label4.setFont(font);
		this.add(label4);
		
		
		
		

		JLabel label5 = new JLabel("中转单编号");
		label5.setBounds(200, 100 + labelwidth * 8, labellength, labelwidth);
		label5.setFont(font);
		this.add(label5);
	
		JListener listener = new JListener();
		
		button_confirm = new JButton("确定");
		button_confirm.setBounds(250, 520, 60, 30);
		button_confirm.addMouseListener(listener);
		this.add(button_confirm);

		button_cancel = new JButton("取消");
		button_cancel.setBounds(350, 520, 60, 30);
		button_cancel.addMouseListener(listener);
		this.add(button_cancel);
	}

	private class JListener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			if (e.getSource()==button_confirm){
				number = textArea1.getText();
				date = datetf.getText();
				arrival = combobox.getSelectedItem().toString();
				district = textArea4.getText();
				row = textArea5.getText();
			}else if (e.getSource()==button_cancel){
				textArea1.setText("");

				
				textArea5.setText("");
			}
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
	private class FocListener implements FocusListener {

		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub

		}

		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == tranway) {
				if (tranway.getSelectedIndex() == 2) {
					transmode = "航班号";
				} else {
					transmode = "车次号";
				}
			}
			label2_1.setText(transmode);
		//	add(label2_1);
		//	add(textArea4_1);
			updateUI();
		}
	}

}
