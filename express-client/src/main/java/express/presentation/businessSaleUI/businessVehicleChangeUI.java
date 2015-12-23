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

import express.businessLogic.infoManageBL.Vehicle;
import express.businesslogicService.businessSaleBLService.VehicleBusinessSaleblService;
import express.vo.VehicleInfoVO;


public class businessVehicleChangeUI extends JDialog{
	
	private VehicleBusinessSaleblService vbs;
	private VehicleInfoVO vo;
	private String id;
	
	
	private DefaultTableModel model;
	private JTextField nametf,numbertf,orgIDtf,useYeartf;
	private JComboBox isUsingcb;
	private JButton ok,delete,exit;
	
	private String name,number,orgID,useYear,isUsing;
	private int realuseYear;
	private boolean used=false;
	
	
	
	
	public businessVehicleChangeUI(DefaultTableModel tablemodel,String ID){
		this.setTitle("修改车辆信息");
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
		vbs=new Vehicle();
		vo=vbs.getVehicleInfo(id);
	//	System.out.println("License：："+vo.getLicense());
		
		
		
		JLabel namelabel = new JLabel("车辆代号");
		namelabel.setFont(font);
		namelabel.setBounds(leftside1, 5, 80, 30);
		this.add(namelabel);
		
		nametf = new JTextField(vo.getMark());
		nametf.setBounds(leftside2, 5, 100, 30);
		nametf.setFont(f);
		this.add(nametf);
		
		JLabel numberlabel = new JLabel("车牌号");
		numberlabel.setFont(font);
		numberlabel.setBounds(leftside1, 45, 80, 30);
		this.add(numberlabel);
		
		numbertf = new JTextField(vo.getLicense());
		numbertf.setBounds(leftside2, 45, 100, 30);
		numbertf.setFont(f);
		this.add(numbertf);
		
		
		JLabel orgIDlabel = new JLabel("机构名称");
		orgIDlabel.setFont(font);
		orgIDlabel.setBounds(leftside1, 85, 80, 30);
		this.add(orgIDlabel);
		
		orgIDtf = new JTextField(vo.getOrgID());
		orgIDtf.setBounds(leftside2, 85, 100, 30);
		orgIDtf.setFont(f);
		this.add(orgIDtf);
		
		JLabel useYearlabel = new JLabel("服役时间");
		useYearlabel.setFont(font);
		useYearlabel.setBounds(leftside1, 125, 80, 30);
		this.add(useYearlabel);
		
		useYeartf = new JTextField(String.valueOf(vo.getUseYear()));
		useYeartf.setBounds(leftside2, 125, 100, 30);
		useYeartf.setFont(f);
		this.add(useYeartf);
		
		
		// System.out.println("查到的服役时间"+String.valueOf(vo.getUseYear()));
		

		JLabel isUsinglabel = new JLabel("是否在使用");
		isUsinglabel.setFont(font);
		isUsinglabel.setBounds(leftside1, 165, 90, 30);
		this.add(isUsinglabel);
		
		isUsingcb = new JComboBox(new String[]{"是","否"});
		isUsingcb.setBounds(leftside2, 165, 100, 30);
		isUsingcb.setFont(f);
		if (vo.getIsUsing()){
			isUsingcb.setSelectedIndex(0);
		}else {
			isUsingcb.setSelectedIndex(1);
		}
		this.add(isUsingcb);

		
		
		
		
		ok = new JButton("确认");
		ok.setBounds(30, 305, 80, 30);
		ok.addMouseListener(listener);
		ok.setFont(font);
		this.add(ok);
		
		delete = new JButton("删除");
		delete.setBounds(120, 305, 80, 30);
		delete.addMouseListener(listener);
		delete.setFont(font);
		this.add(delete);
		
		exit = new JButton("取消");
		exit.setBounds(200, 305, 80, 30);
		exit.addMouseListener(listener);
		exit.setFont(font);
		this.add(exit);
	}
	

   private class JListener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent arg0) {
		 if (arg0.getSource()==delete){
			 vbs.removeVehicleInfo(id);
			 vbs.endManage();
				for (int i = model.getRowCount() - 1; i >= 0; i--) {
					if(model.getValueAt(i, 1).equals(id)){
						model.removeRow(i);
						JOptionPane.showMessageDialog(null, "删除成功", "提示",
								JOptionPane.INFORMATION_MESSAGE);
						break;
					}
				}
		 }
		 else if (arg0.getSource()==ok){
			 name=nametf.getText();
			 number=numbertf.getText();
			 orgID=orgIDtf.getText();
			 useYear=useYeartf.getText();
			 isUsing=isUsingcb.getSelectedItem().toString();
			 
			 
			 realuseYear=Integer.parseInt(useYear);
			 if (isUsing.equals("是")){
				 used=true;
			 }
			 
			 
			 vo=new VehicleInfoVO(name, number, orgID, realuseYear, used);
			 vbs.changeVehicleInfo(vo, id);
			 vbs.endManage();
			 for (int i=model.getRowCount()-1;i>=0;i--){
				 if (model.getValueAt(i, 1).equals(id)){
					 model.setValueAt(name, i, 1);
					 model.setValueAt(number, i, 2);
					 model.setValueAt(orgID, i, 3);
					 model.setValueAt(useYear, i, 4);
					 model.setValueAt(isUsing, i, 5);
					 JOptionPane.showMessageDialog(null, "信息修改成功", "提示",
								JOptionPane.INFORMATION_MESSAGE);
						break;
				 }
			 }
			 
			
		 }
		dispose();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
		
	}
}
