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
import express.po.UserRole;
import express.vo.UserInfoAdminVO;

public class AdminAddUI extends JDialog {

	private JTextField nametf, idtf, keytf;
	private JButton ok, exit;
	private JComboBox positioncb;
	private String name, position, id, key;
	private UserRole posit;
	private Object[] values;
	private DefaultTableModel tmodel;

	public AdminAddUI(DefaultTableModel tablemodel) {
		this.setTitle("添加用户信息");
		this.setLayout(null);
		this.setSize(300, 250);
		this.setLocationRelativeTo(null);

		tmodel = tablemodel;
		Font font = new Font("楷体", Font.PLAIN, 18);
		Font f = new Font("仿宋", Font.PLAIN, 16);

		String[] pos = { "快递员", "管理员", "总经理", "普通财务人员", "最高权限财务人员",
				"中转中心仓库管理人员", "中转中心业务员", "营业厅业务员" };
		JListener lis = new JListener();

		JLabel namel = new JLabel("姓名");
		namel.setBounds(10, 5, 50, 30);
		namel.setFont(font);
		this.add(namel);

		nametf = new JTextField();
		nametf.setBounds(70, 5, 80, 30);
		nametf.setFont(f);
		this.add(nametf);

		JLabel positionl = new JLabel("职位");
		positionl.setBounds(10, 45, 50, 30);
		positionl.setFont(font);
		this.add(positionl);

		positioncb = new JComboBox(pos);
		positioncb.setBounds(70, 45, 120, 30);
		positioncb.setFont(f);
		this.add(positioncb);

		JLabel idl = new JLabel("工号");
		idl.setBounds(10, 85, 50, 30);
		idl.setFont(font);
		this.add(idl);

		idtf = new JTextField();
		idtf.setBounds(70, 85, 80, 30);
		idtf.setFont(f);
		this.add(idtf);

		JLabel keyl = new JLabel("密码");
		keyl.setBounds(10, 125, 50, 30);
		keyl.setFont(font);
		this.add(keyl);

		keytf = new JTextField();
		keytf.setBounds(70, 125, 80, 30);
		keytf.setFont(f);
		this.add(keytf);

		ok = new JButton("确认");
		ok.setBounds(30, 170, 100, 30);
		ok.setFont(font);
		ok.addMouseListener(lis);
		this.add(ok);

		exit = new JButton("取消");
		exit.setBounds(150, 170, 100, 30);
		exit.setFont(font);
		exit.addMouseListener(lis);
		this.add(exit);
	}

	private class JListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == ok) {
				
				name = nametf.getText();
				position = positioncb.getSelectedItem().toString();
				id = idtf.getText();
				key = keytf.getText();
				Object[] temp = { false, name, position, id, key };
				values = temp;
				
				if (name.isEmpty() || id.isEmpty() || key.isEmpty()) {
					JOptionPane.showMessageDialog(null, "信息未填写完整", "提示",
							JOptionPane.ERROR_MESSAGE);
				} else {	
					
					posit = UserRole.values()[positioncb.getSelectedIndex()];
					UserInfoAdminVO vo = new UserInfoAdminVO(name, id, posit,
							key);
					AdminBLService abs = new Admin();
					
					if (abs.addUser(vo)) {
						tmodel.addRow(values);
						JOptionPane.showMessageDialog(null, "添加成功", "提示",
								JOptionPane.INFORMATION_MESSAGE);
						abs.endManage();
						
					}else{
						JOptionPane.showMessageDialog(null, "添加失败", "提示",
								JOptionPane.WARNING_MESSAGE);
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
