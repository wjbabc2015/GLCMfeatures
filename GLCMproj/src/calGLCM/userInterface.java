package calGLCM;

import javax.swing.*;
import java.awt.*;

public class userInterface extends JFrame {
	
	 JButton choice1;
	 JButton choice2;
	 
	 JLabel title;
	 
	 JPanel mainUI;
	 
	
	public userInterface (){
		
		mainUI = new JPanel ();
		
		this.add(mainUI);
		
		this.setSize(300, 200);
		
		title =new JLabel ("GLCM Normalization Options: ");
		title.setSize(60, 60);
		choice1 = new JButton ("Single Angle Normalization");
		choice2 = new JButton ("Mutiple Angle Normalization");
		
		mainUI.add(title);
		mainUI.add(choice1);
		mainUI.add(choice2);
		
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		this.setLocation(600, 500);
		//this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		
		userInterface ui = new userInterface();
	}
}
