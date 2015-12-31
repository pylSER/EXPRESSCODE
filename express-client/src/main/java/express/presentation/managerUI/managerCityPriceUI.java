package express.presentation.managerUI;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumnModel;

import express.businesslogicService.managerBLService.StaffManageBLService;
import express.presentation.mainUI.MainUIService;
import express.presentation.mainUI.MyTableModel;
import express.vo.UserInfoVO;

public class managerCityPriceUI  extends JPanel {

	private JButton detele, add, change;
	private JTextField idtf;
	private JPanel pricepane,dispane;
	private String changeunder = "<HTML><U>修改</U></HTML>";
	private String confirmunder = "<HTML><U>确认</U></HTML>";

	public managerCityPriceUI(){
		setLayout(null);
		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		Font font = new Font("楷体", Font.PLAIN, 18);
		Font f = new Font("仿宋", Font.PLAIN, 16);
		
		pricepane = new JPanel();
	}
}
