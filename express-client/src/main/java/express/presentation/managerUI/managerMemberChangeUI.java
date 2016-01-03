package express.presentation.managerUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import express.businessLogic.infoManageBL.StaffForManager;
import express.businesslogicService.managerBLService.StaffManageBLService;
import express.po.UserRole;
import express.presentation.mainUI.DateChooser;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherGreenLabel;
import express.presentation.mainUI.MyOtherRedLabel;
import express.presentation.mainUI.TipBlock;
import express.vo.UserInfoVO;

public class managerMemberChangeUI extends JDialog {

	
	
	private JPanel tippane;
	private JTextField nametf, idtf, phonetf, datetf;
	private JLabel tipphone;
	private JComboBox gendercb, positioncb, citycb;
	private String name, id, city, phone, date, position, gender;
	private UserRole posit;
	private boolean sex;
	private DateChooser datechooser;
	private MyOtherBlueLabel ok;
	private MyOtherGreenLabel exit;
	private MyOtherRedLabel detele;
	private DefaultTableModel tmodel;
	private StaffManageBLService smb;
	private UserInfoVO vo;
	private boolean b;

	public managerMemberChangeUI(DefaultTableModel tablemodel, String id) {
		this.setTitle("修改用户信息");
		this.setLayout(null);
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.white);
		
		
		this.id = id;
		tmodel = tablemodel;
		int leftside1 = 10;
		int leftside2 = 100;
		Font font = new Font("幼圆", Font.PLAIN, 20);
		Font f = new Font("方正隶变简体", Font.PLAIN, 18);
		Font buttonfont = new Font("隶书", Font.PLAIN, 18);

		smb = new StaffForManager();
		vo = smb.getUser(id);
		JListener lis = new JListener();

		JLabel namel = new JLabel("姓名");
		namel.setFont(font);
		namel.setBounds(leftside1, 5, 50, 30);
		this.add(namel);

		nametf = new JTextField(vo.getName());
		nametf.setBounds(leftside2, 5, 100, 30);
		nametf.setFont(f);
		this.add(nametf);

		JLabel genderl = new JLabel("性别");
		genderl.setFont(font);
		genderl.setBounds(leftside1, 45, 50, 30);
		this.add(genderl);

		String[] genders = { "男", "女" };
		gendercb = new JComboBox(genders);
		gendercb.setBounds(leftside2, 45, 60, 30);
		gendercb.setFont(f);
		if(vo.getGender())
			gendercb.setSelectedIndex(0);
		else
			gendercb.setSelectedIndex(1);
		this.add(gendercb);

		JLabel idl = new JLabel("工号");
		idl.setBounds(leftside1, 85, 100, 30);
		idl.setFont(font);
		this.add(idl);

		idtf = new JTextField(id);
		idtf.setBounds(leftside2, 85, 100, 30);
		idtf.setFont(f);
		idtf.setEditable(false);
		this.add(idtf);

		JLabel positionl = new JLabel("职位");
		positionl.setBounds(leftside1, 125, 50, 30);
		positionl.setFont(font);
		this.add(positionl);

		String[] pos = { "快递员", "管理员", "总经理", "普通财务人员", "最高权限财务人员",
				"中转中心仓库管理人员", "中转中心业务员", "营业厅业务员" };
		posit = vo.getPosition();
		position = posit.toString();
		positioncb = new JComboBox(pos);
		positioncb.setBounds(leftside2, 125, 120, 30);
		positioncb.setFont(f);
		positioncb.setSelectedItem(position);
		this.add(positioncb);

		JLabel cityl = new JLabel("所在城市");
		cityl.setBounds(leftside1, 165, 80, 30);
		cityl.setFont(font);
		this.add(cityl);

		city = vo.getCity();
		citycb = new JComboBox();
		addcity();
		citycb.setBounds(leftside2, 165, 100, 30);
		citycb.setFont(f);
		citycb.setSelectedItem(city);
		this.add(citycb);

		JLabel phonel = new JLabel("联系方式");
		phonel.setBounds(leftside1, 205, 80, 30);
		phonel.setFont(font);
		this.add(phonel);

		phonetf = new JTextField(vo.getPhoneNum());
		phonetf.setBounds(leftside2, 205, 100, 30);
		phonetf.setFont(f);
		this.add(phonetf);

		tipphone = new JLabel(" * 手机号格式错误");
		tipphone.setFont(font);
		tipphone.setForeground(Color.RED);
		tipphone.setBounds(leftside2+110, 205, 100, 30);
		
		JLabel datel = new JLabel("入职日期");
		datel.setBounds(leftside1, 245, 80, 30);
		datel.setFont(font);
		this.add(datel);

		datetf = new JTextField(vo.getDate());
		datetf.setBounds(leftside2, 245, 100, 30);
		datetf.setFont(f);
		datetf.setEditable(false);
		this.add(datetf);

		datechooser = new DateChooser("yyyy-MM-dd", datetf);
		datechooser.setBounds(leftside2 + 110, 240, 40, 40);
		this.add(datechooser);

		ok = new MyOtherBlueLabel("修改");
		ok.setBounds(30, 305, 70, 30);
		ok.addMouseListener(lis);
		this.add(ok);
		
		detele = new MyOtherRedLabel("删除");
		detele.setBounds(120, 305,70, 30);
		detele.addMouseListener(lis);
		this.add(detele);

		
		exit = new MyOtherGreenLabel("取消");
		exit.setBounds(210, 305, 70, 30);
		exit.addMouseListener(lis);
		this.add(exit);
		
		tippane=new JPanel();
		 tippane.setSize(850,40);
		tippane.setLocation(0, 660);
		tippane.setBackground(Color.white);
		tippane.setLayout(null);
		this.add(tippane);
		
		

	}

	private void addcity() {
		citycb.addItem("南京");
		citycb.addItem("北京");
		citycb.addItem("上海");
	}

	private class JListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==ok){
				name = nametf.getText();
				position = positioncb.getSelectedItem().toString();
				id = idtf.getText();
				city = citycb.getSelectedItem().toString();
				phone = phonetf.getText();
				date = datetf.getText();
				gender = gendercb.getSelectedItem().toString();

				posit = UserRole.values()[positioncb.getSelectedIndex()];
				if(gender.equals("男")){
					sex = true;
				}else{
					sex = false;
				}
				
				vo = new UserInfoVO(name,sex,id,phone,posit,city,date);				
				smb.changeUser(vo, id);
				smb.endManage();
				for (int i = tmodel.getRowCount() - 1; i >= 0; i--) {
					if(tmodel.getValueAt(i, 3).equals(id)){
						tmodel.setValueAt(name, i, 1);
						tmodel.setValueAt(gender, i, 2);
						tmodel.setValueAt(id, i, 3);
						tmodel.setValueAt(position, i, 4);
						tmodel.setValueAt(city, i, 5);
						tmodel.setValueAt(phone, i, 6);
						tmodel.setValueAt(date, i, 7);
						
						TipBlock block=new TipBlock("修改成功");
						tippane.add(block);
						block.show();
						block=null;
						break;
					}
				}
			}else if(e.getSource()==detele){
				
				for (int i = tmodel.getRowCount() - 1; i >= 0; i--) {
					if(tmodel.getValueAt(i, 3).equals(id)){
						tmodel.removeRow(i);
						smb.removeUser(id);
						smb.endManage();
						TipBlock block=new TipBlock("删除成功");
						tippane.add(block);
						block.show();
						block=null;
						break;
					}
				}
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
			if(e.getSource()==ok){
				ok.whenPressed();
			}else if (e.getSource()==exit) {
				exit.whenPressed();
			}else if (e.getSource()==detele) {
				detele.whenPressed();
			}

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(e.getSource()==ok){
				ok.setMyColor();
			}else if (e.getSource()==exit) {
				exit.setMyColor();
			}else if (e.getSource()==detele) {
				detele.setMyColor();
			}

		}

	}
}
