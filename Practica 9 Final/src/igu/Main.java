package igu;

import java.awt.EventQueue;
import java.util.Properties;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import com.jtattoo.plaf.hifi.HiFiLookAndFeel;

import player.MusicPlayer;

public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				int ex = 0;
				try {
					configLookAndFeel();
					VentanaPrincipal frame = new VentanaPrincipal(new MusicPlayer());
					frame.setVisible(true);
				} catch (Exception e) {
					ex++;
					System.out.println(ex +"  ..  [" +e.getMessage()+"]");
				}
			}
		});
	}
	
	public static void configLookAndFeel() throws Exception {
		Properties props = new Properties();
		props.put("logoString", ""); // Quita el logo de la libreria
		HiFiLookAndFeel.setCurrentTheme(props);
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
	}
	
	
}
