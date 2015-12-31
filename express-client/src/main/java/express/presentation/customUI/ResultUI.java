package express.presentation.customUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import express.businessLogic.searchBL.Search;
import express.businesslogicService.customerBLService.SearchBLService;
import express.vo.GoodTransStatusVO;

public class ResultUI {
	private SearchBLService sbs;
	private final JLabel errortip=new JLabel("这里显示输入错误信息");
	JButton confirm=new JButton(new ImageIcon("picture/search1.png"));
	JButton exitbutton= new JButton(new ImageIcon("picture/x.png"));
	String orderID;
	JLabel id=new JLabel("");
	
	JLabel status1=new JLabel("");
	JLabel status2=new JLabel("");
	JLabel status3=new JLabel("");
	JLabel status4=new JLabel("");
	JLabel status5=new JLabel("");
	JLabel status6=new JLabel("");
	JLabel status7=new JLabel("");
	
	
	JLabel time1=new JLabel("");
	JLabel time2=new JLabel("");
	JLabel time3=new JLabel("");
	JLabel time4=new JLabel("");
	JLabel time5=new JLabel("");
	JLabel time6=new JLabel("");
	JLabel time7=new JLabel("");
	
	ArrayList<JLabel> statuslist=new ArrayList<JLabel>();
	ArrayList<JLabel> timelist=new ArrayList<JLabel>();
	
	
	public ResultUI(){
		statuslist.add(status1);
		statuslist.add(status2);
		statuslist.add(status3);
		statuslist.add(status4);
		statuslist.add(status5);
		statuslist.add(status6);
		statuslist.add(status7);
		
		
		timelist.add(time1);
		timelist.add(time2);
		timelist.add(time3);
		timelist.add(time4);
		timelist.add(time5);
		timelist.add(time6);
		timelist.add(time7);
	}
	
	
	
	
	public JPanel getResult(){
		MyDrawPanel2 pane=new MyDrawPanel2();
		pane.setLayout(null);
		pane.setBackground(Color.WHITE);
		
		
		final JPanel buttonPanel = new JPanel();  
		buttonPanel.setBackground(null);                      // 把背景设置为会  
		buttonPanel.setOpaque(false); 
		buttonPanel.setLayout(null);
		buttonPanel.setSize(820,550);
		pane.add(buttonPanel);
		confirm.setSize(36,36);
		confirm.setLocation(537, 29);
		confirm.setBorderPainted(false);
		buttonPanel.add(confirm);
		
		final JTextField orderid= new JTextField();
		orderid.setSize(570,48);
		orderid.setLocation(10,23);
		pane.add(orderid);
		

		exitbutton.setSize(30,30);
		exitbutton.setBorderPainted(false);
		exitbutton.setLocation(790,0);
		pane.add(exitbutton);
		
		status1.setSize(300,15);
		status1.setLocation(50,208);
		pane.add(status1);
		
		status2.setSize(300,15);
		status2.setLocation(50,250);
		pane.add(status2);
		
		status3.setSize(300,15);
		status3.setLocation(50,294);
		pane.add(status3);
		
		status4.setSize(300,15);
		status4.setLocation(50,336);
		pane.add(status4);
		
		status5.setSize(300,15);
		status5.setLocation(50,382);
		pane.add(status5);
		
		status6.setSize(300,15);
		status6.setLocation(50,428);
		pane.add(status6);
		
		status7.setSize(300,15);
		status7.setLocation(50,470);
		pane.add(status7);
		
		time1.setSize(150,15);
		time1.setLocation(680, 208);
		pane.add(time1);
		
		time2.setSize(150,15);
		time2.setLocation(680, 250);
		pane.add(time2);
		
		time3.setSize(150,15);
		time3.setLocation(680, 294);
		pane.add(time3);
		
		time4.setSize(150,15);
		time4.setLocation(680, 336);
		pane.add(time4);
		
		time5.setSize(150,15);
		time5.setLocation(680, 382);
		pane.add(time5);
		
		time6.setSize(150,15);
		time6.setLocation(680, 428);
		pane.add(time6);
		
		time7.setSize(150,15);
		time7.setLocation(680, 470);
		pane.add(time7);
		
		
		errortip.setSize(300,30);
		errortip.setForeground(Color.RED);
		errortip.setLocation(580, 30);
		pane.add(errortip);
		
		id.setSize(350,30);
		id.setLocation(130,97);
		pane.add(id);
		
		
		exitbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(orderid.getText().equals("")){
					errortip.setText("请输入订单条形码号");

				}
				else{
					if(checkOrder(orderid.getText())){
						
					}
					else{
						errortip.setText("订单不存在");
					}
				}
			}
		});
		
		
		
		
		
		
		return pane;
	}
	
	
	public boolean checkOrder(String orderid){
		sbs=new Search();
		
		GoodTransStatusVO vo=new GoodTransStatusVO();
		vo=sbs.getOrderIDStatus(orderid);
		if(vo==null||vo.getOrderID().equals("-1")){
			return false;
		}
		else {
			
			id.setText(orderid);
			
			ArrayList<String> stat=vo.getstatusList();
			ArrayList<String> time=vo.getTime();
			int len=stat.size();
			
			
			for(int i=0;i<7;i++){
				statuslist.get(i).setText("");
				timelist.get(i).setText("");
			}
			
			for(int i=0;i<len;i++){
				statuslist.get(i).setText(stat.get(i));
				timelist.get(i).setText(time.get(i));
			}
			errortip.setText("");
			return true;
		}
		
		
	}
	
}
class MyDrawPanel2 extends JPanel{
	public void paintComponent(Graphics g){
		Image image=new ImageIcon("picture/result.png").getImage();
		g.drawImage(image,0,0,this);
}
}