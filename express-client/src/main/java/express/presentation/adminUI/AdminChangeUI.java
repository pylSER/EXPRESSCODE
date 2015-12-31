package express.presentation.adminUI;

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

import express.businessLogic.infoManageBL.Admin;
import express.businesslogicService.adminBLService.AdminBLService;
import express.businesslogicService.adminBLService.RemoveUserBLService;
import express.po.UserRole;
import express.vo.UserInfoAdminVO;
import express.vo.UserInfoVO;

public class AdminChangeUI extends JDialog {

	private JTextField nametf, idtf, keytf,positiontf;
	private JButton ok, exit,detele;
	private String name, position, id, key;
	private UserRole posit;
	private DefaultTableModel tmodel;
	private AdminBLService abs;
	private UserInfoAdminVO vo;

	public AdminChangeUI(DefaultTableModel tablemodel,String id) {
		this.setTitle("修改用户信息");
		this.setLayout(null);
		this.setSize(300, 250);
		this.setLocationRelativeTo(null);

		this.id = id;
		abs = new Admin();
		vo = abs.getUser(id);
		tmodel = tablemodel;
		Font font = new Font("楷体", Font.PLAIN, 18);
		Font f = new Font("仿宋", Font.PLAIN, 16);

		JListener lis = new JListener();

		JLabel namel = new JLabel("姓名");
		namel.setBounds(10, 5, 50, 30);
		namel.setFont(font);
		this.add(namel);

		name = vo.getName();
		nametf = new JTextField(name);
		nametf.setBounds(70, 5, 80, 30);
		nametf.setFont(f);
		nametf.setEditable(false);
		this.add(nametf);

		JLabel positionl = new JLabel("职位");
		positionl.setBounds(10, 45, 50, 30);
		positionl.setFont(font);
		this.add(positionl);

//		String[] pos = { "快递员", "管理员", "总经理", "普通财务人员", "最高权限财务人员",
//		"中转中心仓库管理人员", "中转中心业务员", "营业厅业务员" };
    	posit = vo.getPosition();
    	position = new UserInfoVO().transposition(posit);
//    	if(posit.equals(UserRole.Admin))
//    		position = "管理员";
//    	else if(posit.equals(UserRole.BusinessSale))
//    		position = "营业厅业务员";
//    	else if(posit.equals(UserRole.DeliverMan))
//    		position = "快递员";
//    	else if(posit.equals(UserRole.Financial))
//    		position = "普通财务人员";
//    	else if(posit.equals(UserRole.Financial_highest))
//    		position = "最高权限财务人员";
//    	else if(posit.equals(UserRole.Manager))
//    		position = "总经理";
//    	else if(posit.equals(UserRole.TransCenterRepo))
//    		position = "中转中心仓库管理人员";
//    	else if(posit.equals(UserRole.TransCenterSale))
//    		position = "中转中心业务员";
    	
		positiontf = new JTextField(position);
		positiontf.setBounds(70, 45, 120, 30);
		positiontf.setFont(f);
		positiontf.setEditable(false);
		this.add(positiontf);

		JLabel idl = new JLabel("工号");
		idl.setBounds(10, 85, 50, 30);
		idl.setFont(font);
		this.add(idl);

		idtf = new JTextField(id);
		idtf.setBounds(70, 85, 80, 30);
		idtf.setFont(f);
		idtf.setEditable(false);
		this.add(idtf);

		JLabel keyl = new JLabel("密码");
		keyl.setBounds(10, 125, 50, 30);
		keyl.setFont(font);
		this.add(keyl);

		key = vo.getPassword();
		keytf = new JTextField(key);
		keytf.setBounds(70, 125, 80, 30);
		keytf.setFont(f);
		this.add(keytf);

		ok = new JButton("修改");
		ok.setBounds(10, 170,70, 30);
		ok.setFont(font);
		ok.addMouseListener(lis);
		this.add(ok);
		
		detele = new JButton("删除");
		detele.setBounds(100, 170,70, 30);
		detele.setFont(font);
		detele.addMouseListener(lis);
		this.add(detele);

		exit = new JButton("取消");
		exit.setBounds(190, 170, 70, 30);
		exit.setFont(font);
		exit.addMouseListener(lis);
		this.add(exit);
		
	}
	
	private class JListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==ok){

				key = keytf.getText();
				for (int i = tmodel.getRowCount() - 1; i >= 0; i--) {
					if(tmodel.getValueAt(i, 3).equals(id)){
						tmodel.setValueAt(key, i, 4);
						abs.changeUserPassword(id, key);
						abs.endManage();
						JOptionPane.showMessageDialog(null, "修改密码成功", "提示",
								JOptionPane.INFORMATION_MESSAGE);
						break;
					}
				}
			}else if(e.getSource()==detele){
				RemoveUserBLService rub = new Admin();
				for (int i = tmodel.getRowCount() - 1; i >= 0; i--) {
					if(tmodel.getValueAt(i, 3).equals(id)){
						tmodel.removeRow(i);
						rub.removeUser(id);
						abs.endManage();
						JOptionPane.showMessageDialog(null, "删除成功", "提示",
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
