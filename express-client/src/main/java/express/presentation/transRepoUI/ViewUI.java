package express.presentation.transRepoUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import express.presentation.mainUI.DateChooser;
import express.presentation.mainUI.MainUIService;

public class ViewUI extends JPanel{
	private JButton button_view;
  
    private JTextField datetf;
    private JTextField datetf2;
    private DateChooser datechooser;     
    private DateChooser datechooser2;
    
    
    private JTable table;
    private JScrollPane scrollPane;
    
	public ViewUI(){
     	
		int textlength = 150;
		int textwidth = 30;
		int labellength = 100;
		int labelwidth = 30;
		
		Font font = new Font("楷体", Font.PLAIN, 18);
		Font f = new Font("仿宋", Font.PLAIN, 16);

		
		
		setLayout(null);	    
	    this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);
		
		
		
		JLabel label1 = new JLabel("起始日期");
		label1.setBounds(100, 100, labellength, labelwidth);
		label1.setFont(font);
		this.add(label1);
		
		
		datetf = new JTextField();
		datetf.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		datetf.setBounds(200, 100, textlength, textwidth);
		datetf.setFont(f);
		datetf.setEditable(false);
		this.add(datetf);
		
		datechooser = new DateChooser("yyyy-MM-dd", datetf);
		datechooser.setBounds(360, 95, 40, 40);
		this.add(datechooser);
		
		
		JLabel label2 = new JLabel("终止日期");
		label2.setBounds(450, 100, labellength, labelwidth);
		label2.setFont(font);
		this.add(label2);
		
		
		datetf2 = new JTextField();
		datetf2.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		datetf2.setBounds(550, 100, textlength, textwidth);
		datetf2.setFont(f);
		datetf2.setEditable(false);
		this.add(datetf2);
		
		datechooser2 = new DateChooser("yyyy-MM-dd", datetf2);
		datechooser2.setBounds(710, 95, 40, 40);
		this.add(datechooser2);
		
		
		String[] tableheader = {"快递编号","位置"};
		String[] data1 = {"110","地上"};
		String[] data2 = {"120","车上"};
		
		String[][] data ={data1,data2};
		table=new JTable(data,tableheader);
		table.setRowHeight(40);
		table.setFont(f);
		table.setBounds(100, 150, 650, 550);	
		this.add(table);
		
		scrollPane = new JScrollPane(table); 
		scrollPane.setFont(font);
		scrollPane.setBounds(100, 150, 650, 550);
		this.add(scrollPane);
		
		
		

	}

}
