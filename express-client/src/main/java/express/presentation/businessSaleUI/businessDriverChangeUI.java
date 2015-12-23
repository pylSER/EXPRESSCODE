package express.presentation.businessSaleUI;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import express.businessLogic.infoManageBL.Driver;
import express.businesslogicService.businessSaleBLService.DriverBusinessSaleblService;
import express.vo.DriverInfoVO;
import express.vo.VehicleInfoVO;


public class businessDriverChangeUI extends JDialog{
	private DriverBusinessSaleblService dbs;
	private DriverInfoVO vo;
	private String id;
	
	
	private JTextField driverIDtf,orgIDtf,nametf,datetf,peopleIDtf,cellphonetf,ddltf;
	private JComboBox gendercb;
	private JButton ok,delete,exit;
	
	private String driverID,orgID,name,date,peopleID,cellphone,gender,ddl;
	private boolean sex;
	private int deadline;
	
	
	
	private DefaultTableModel model;
	
	
	public businessDriverChangeUI(DefaultTableModel tablemodel,String ID){
		this.setTitle("修改司机信息");
		this.setLayout(null);
		this.setSize(350, 400);
		this.setLocationRelativeTo(null);
		
		int leftside1 = 10;
		int leftside2 = 100;
		Font font = new Font("楷体",Font.PLAIN,18);
		Font f = new Font("仿宋",Font.PLAIN,16);
		this.model = tablemodel;
		this.id=ID;
		
		JListener listener=new JListener();
		dbs=new Driver();
		vo=dbs.getDriverInfo(id);
		
		
		
		 
		
		
		JLabel driverIDlabel = new JLabel("司机编号");
		driverIDlabel.setFont(font);
		driverIDlabel.setBounds(leftside1, 5, 80, 30);
		this.add(driverIDlabel);
		
		driverIDtf = new JTextField(vo.getdriverNumber());
		driverIDtf.setBounds(leftside2, 5, 100, 30);
		driverIDtf.setFont(f);
		this.add(driverIDtf);
		
		JLabel orgIDlabel = new JLabel("营业厅编号");
		orgIDlabel.setFont(font);
		orgIDlabel.setBounds(leftside1, 45, 90, 30);
		this.add(orgIDlabel);
		
		orgIDtf = new JTextField(vo.getbusinesshallNumber());
		orgIDtf.setBounds(leftside2, 45, 100, 30);
		orgIDtf.setFont(f);
		this.add(orgIDtf);
		
		
		JLabel namelabel = new JLabel("姓名");
		namelabel.setFont(font);
		namelabel.setBounds(leftside1, 85, 80, 30);
		this.add(namelabel);
		
		nametf = new JTextField(vo.getname());
		nametf.setBounds(leftside2, 85, 100, 30);
		nametf.setFont(f);
		this.add(nametf);
		
		JLabel datelabel = new JLabel("出生日期");
		datelabel.setFont(font);
		datelabel.setBounds(leftside1, 125, 80, 30);
		this.add(datelabel);
		
		datetf = new JTextField(vo.getdate());
		datetf.setBounds(leftside2, 125, 100, 30);
		datetf.setFont(f);
		this.add(datetf);

		JLabel peopleIDlabel = new JLabel("身份证号");
		peopleIDlabel.setFont(font);
		peopleIDlabel.setBounds(leftside1, 165, 90, 30);
		this.add(peopleIDlabel);
		
		peopleIDtf =new JTextField(vo.getID());
		peopleIDtf.setBounds(leftside2, 165, 100, 30);
		peopleIDtf.setFont(f);
		this.add(peopleIDtf);
		
		JLabel cellphoneLabel=new JLabel("联系方式");
		cellphoneLabel.setFont(font);
		cellphoneLabel.setBounds(leftside1, 205, 90, 30);
		this.add(cellphoneLabel);
		
		cellphonetf=new JTextField(vo.getcellPhone());
		cellphonetf.setFont(font);
		cellphonetf.setBounds(leftside2, 205, 100, 30);
		this.add(cellphonetf);
		
		
		JLabel genderlabel = new JLabel("性别");
		genderlabel.setFont(font);
		genderlabel.setBounds(leftside1, 245, 90, 30);
		this.add(genderlabel);
		
	    gendercb = new JComboBox(new String[]{"男","女"});
	    gendercb.setBounds(leftside2, 245, 100, 30);
	    gendercb.setFont(f);
		if (vo.getsex()){
			gendercb.setSelectedIndex(0);;
		}
		else {
			gendercb.setSelectedIndex(1);
		}  
	    this.add(gendercb);
		
		
		JLabel ddllabel = new JLabel("行驶证期限");
		ddllabel.setFont(font);
		ddllabel.setBounds(leftside1, 285, 90, 30);
		this.add(ddllabel);
		
		ddltf=new JTextField(String.valueOf(vo.getdeadline()));
		ddltf.setBounds(leftside2, 285, 100, 30);
		ddltf.setFont(f);
		this.add(ddltf);
		
		
		ok = new JButton("确认");
		ok.setBounds(30, 325, 80, 30);
		ok.addMouseListener(listener);
		ok.setFont(font);
		this.add(ok);
		
		delete = new JButton("删除");
		delete.setBounds(120, 325, 80, 30);
		delete.addMouseListener(listener);
		delete.setFont(font);
		this.add(delete);
		
		exit = new JButton("取消");
		exit.setBounds(200, 325, 80, 30);
		exit.addMouseListener(listener);
		exit.setFont(font);
		this.add(exit);
		
		
	}
	 private class JListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			 if (e.getSource()==delete){
				 dbs.removeDriverInfo(id);
				 dbs.endManage();
					for (int i = model.getRowCount() - 1; i >= 0; i--) {
						if(model.getValueAt(i, 1).equals(id)){
							model.removeRow(i);
							JOptionPane.showMessageDialog(null, "删除成功", "提示",
									JOptionPane.INFORMATION_MESSAGE);
							break;
						}
					}
			 }
			 else if (e.getSource()==ok){
//				 driverID,orgID,name,date,peopleID,cellphone,gender,ddl
				 driverID=driverIDtf.getText();
				 orgID=orgIDtf.getText();
				 name=nametf.getText();
				 date=datetf.getText();
				 peopleID=peopleIDtf.getText();
				 cellphone=cellphonetf.getText();
				 gender=gendercb.getSelectedItem().toString();
				 ddl=ddltf.getText();
				 
				 if (gender=="男"){
					 sex=true;
				 }
				 else {
					 sex=false;
				 }
				 
				 deadline=Integer.parseInt(ddl);
				 
				 vo=new DriverInfoVO(driverID,orgID,name,date,peopleID,cellphone,sex,deadline);
				 dbs.changeDriverInfo(vo, id);
				 dbs.endManage();
				 for (int i=model.getRowCount()-1;i>=0;i--){
					 if (model.getValueAt(i, 1).equals(id)){
						 model.setValueAt(driverID, i, 1);
						 model.setValueAt(orgID,    i, 2);
						 model.setValueAt(name,     i, 3);
						 model.setValueAt(date,     i, 4);
						 model.setValueAt(peopleID, i, 5);
						 model.setValueAt(cellphone,i, 6);
						 model.setValueAt(gender,   i, 7);
						 model.setValueAt(ddl,      i, 8);
						 
						 
						 JOptionPane.showMessageDialog(null, "信息修改成功", "提示",
									JOptionPane.INFORMATION_MESSAGE);
							break;
					 }
				 }
			 }
			 else if (e.getSource()==exit){
				 
			 }
			 
			 dispose();
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		 
	 }
	
}
