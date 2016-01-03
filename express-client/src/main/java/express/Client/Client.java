package express.Client;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;


import express.presentation.userUI.LoginUI;
import express.rmi.RMIClient;


public class Client{
	
	public static void main(String[] args){
		
		try {
			 
//			UIManager.setLookAndFeel(new SubstanceLookAndFeel());
////			JFrame.setDefaultLookAndFeelDecorated(true);
////			JDialog.setDefaultLookAndFeelDecorated(true);
//			SubstanceLookAndFeel.setCurrentTheme(new SubstanceCremeTheme());
//			// SubstanceLookAndFeel.setSkin(new EmeraldDuskSkin());
//			SubstanceLookAndFeel
//					.setCurrentButtonShaper(new ClassicButtonShaper());
//			SubstanceLookAndFeel
//					.setCurrentWatermark(new SubstanceBubblesWatermark());
			// SubstanceLookAndFeel.setCurrentBorderPainter(new
			// StandardBorderPainter());
			// SubstanceLookAndFeel.setCurrentGradientPainter(new
			// StandardGradientPainter());
			// SubstanceLookAndFeel.setCurrentTitlePainter(new
			// FlatTitePainter());
		} catch (Exception e) {
			System.err.println("Something went wrong!");
		}
		
		try {
	        RMIClient.init();       
	        new LoginUI();
	    } catch (Exception e) {
	        e.printStackTrace(); 
	    }
	}
}
