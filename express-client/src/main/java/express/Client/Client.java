package express.Client;

import java.awt.Font;

import javax.swing.UIManager;

import express.presentation.userUI.LoginUI;
import express.rmi.RMIClient;


public class Client{
	
	public static void main(String[] args){
		try {
	        RMIClient.init();
	        Font f = new Font("幼圆", Font.PLAIN, 18);
			UIManager.put("OptionPane.font", f);
	        new LoginUI();
	    } catch (Exception e) {
	        e.printStackTrace(); 
	    }
	}
}
