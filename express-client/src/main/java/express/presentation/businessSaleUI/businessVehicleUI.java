package express.presentation.businessSaleUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumnModel;

import express.businessLogic.IDKeeper;
import express.businessLogic.infoManageBL.Vehicle;
import express.businesslogicService.businessSaleBLService.VehicleBusinessSaleblService;
import express.data.vehicleAndDriverData.VehicleIO;
import express.dataService.vehicleAndDriverDataService.VehicleDataService;
import express.po.UserRole;
import express.po.VehicleInfoPO;
import express.presentation.mainUI.MainUIService;
import express.presentation.mainUI.MyTableModel;
import express.presentation.managerUI.managerMemberChangeUI;
import express.vo.UserInfoVO;
import express.vo.VehicleInfoVO;

public class businessVehicleUI extends JPanel {

	
	private VehicleBusinessSaleblService vbs;
	private VehicleInfoVO vo;
	private ArrayList<VehicleInfoVO> list;
	
	private JButton delete,change,add;
	private JTextField idtf;
	private MyTableModel tableModel;
	private TableColumnModel tcm;
	private JTable table;
	private String changeunder = "<HTML><U>修改</U></HTML>";
	private String confirmunder = "<HTML><U>确认</U></HTML>";
	private Object[][] data;
	private String[] header = {"选择" ,"车辆代号", "车牌号","机构名称","服役时间(年)","是否在使用","修改" };

	private String[] Using={"是","否"};
	
	private JComboBox iscb;
	private String id;
	
	
	public businessVehicleUI(){
		Font font = new Font("楷体", Font.PLAIN, 18);
		Font f = new Font("仿宋", Font.PLAIN, 16);

		setLayout(null);
		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);
		
		
		JLisenter lisenter=new JLisenter();
		vbs=new  Vehicle();
		
		
		
		Class[] typeArray = { Boolean.class,Object.class,Object.class,Object.class,Object.class,Object.class,Object.class };
//		Object[] vehicle1 = {true,"001", "京v-02009", "营业厅A","5","是",changeunder };
//		Object[] uservehicle2 = { false,"002", "苏A-66666","中转中心B","5","否" ,changeunder };
//		Object vehicle[][] = { vehicle1, uservehicle2 };
//		data = vehicle;
		list=vbs.getVehicleInfoList();
		if (list!=null){
			data=new Object[list.size()][7];
			for (int i=0;i<list.size();i++){
				VehicleInfoVO temp=list.get(i);
				data[i][0]=false;
				data[i][1]=temp.getMark();
				data[i][2]=temp.getLicense();
				data[i][3]=temp.getOrgID();
				data[i][4]=String.valueOf(temp.getUseYear());
				data[i][5]=temp.getIsUsing()?"是":"否";
				data[i][6]=changeunder;
			}
		}
	
		tableModel=new MyTableModel(data, header, typeArray);
		table=new JTable(tableModel);
		table.getTableHeader().setReorderingAllowed(false);
		table.setBounds(50, 60, 750, 600);
		
		iscb=new JComboBox(Using);
		tcm = table.getColumnModel();
		tcm.getColumn(5).setCellEditor(new DefaultCellEditor(iscb));
		
		table.setFont(f);
		table.setRowHeight(40);
		table.addMouseListener(lisenter);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(50, 60, 750, 600);
		this.add(scrollPane);

		
		delete = new JButton("删除");
		delete.setBounds(50, 10, 100, 40);
		delete.setFont(font);
 		delete.addMouseListener(lisenter);
		this.add(delete);

		add = new JButton("添加");
		add.setBounds(190, 10, 100, 40);
	 	add.addMouseListener(lisenter);
		add.setFont(font);
		this.add(add);

		change = new JButton("查找");
		change.setBounds(320, 10, 100, 40);
		change.addMouseListener(lisenter);
		change.setFont(font);
		this.add(change);

		JLabel id = new JLabel("车辆代号");
		id.setBounds(450, 10, 100, 40);
		id.setFont(font);
		this.add(id);

		idtf = new JTextField();
		idtf.setBounds(560, 10, 150, 40);
		idtf.setFont(f);
		this.add(idtf);


	}
	private class JLisenter implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource()==delete){
				for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
					if ((boolean) tableModel.getValueAt(i, 0)) {
						
					    vbs.removeVehicleInfo((String)tableModel.getValueAt(i, 1));//逻辑层删除这条记录
					    tableModel.removeRow(i);
					}
				}
				vbs.endManage();//删除之后，逻辑层会调用数据层的writeall，更新所有记录	
				JOptionPane.showMessageDialog(null, "删除成功", "提示",
						JOptionPane.INFORMATION_MESSAGE);
			}
			else if (e.getSource()==add){
				businessVehicleAddUI vehicleAddpanel=new businessVehicleAddUI(tableModel);
				vehicleAddpanel.setVisible(true);
				
			}
			else if (e.getSource()==change){
				id = idtf.getText();
				if (vbs.isCarIDAvailable(id)){
					businessVehicleChangeUI vehicleChange = new businessVehicleChangeUI(
							tableModel, id);
					vehicleChange.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "车辆代号不存在", "提示",
							JOptionPane.ERROR_MESSAGE);
				
				}
				
			}
			else if (e.getSource()==table){
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				if (col == 6) {
					if (tableModel.getValueAt(row, col).equals(changeunder)) {
						tableModel.setrowedit();
						tableModel.setValueAt(confirmunder, row, col);
					} else if (tableModel.getValueAt(row, col).equals(confirmunder)) {
						
						tableModel.setrowunedit();
						tableModel.setValueAt(changeunder, row, col);

						String name = (String) tableModel.getValueAt(row, 1);
						String number = (String) tableModel.getValueAt(row, 2);
						String orgID = (String) tableModel.getValueAt(row, 3);
						String useYear = (String) tableModel.getValueAt(row, 4);
						int realuseYear=Integer.parseInt(useYear);
						String isUsing = (String) tableModel.getValueAt(row, 5);
						boolean used=false;
						if (isUsing.equals("是")){
							used=true;
						}

					//	System.out.println("表格中的服役时间："+useYear);
					//	System.out.println("NUM："+number);
						

						
						vo = new VehicleInfoVO(name,number,orgID,realuseYear,used);
						vbs.changeVehicleInfo(vo, name);
						JOptionPane.showMessageDialog(null, "信息修改成功", "提示",
								JOptionPane.INFORMATION_MESSAGE);
						vbs.endManage();
					
					}
				}
				 
			}
			updateUI();
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
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
