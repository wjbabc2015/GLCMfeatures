package calGLCM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class userInterface extends JFrame implements ActionListener {
	
	 JButton choice1;
	 JButton choice2;
	 
	 JLabel title;
	 
	 JPanel mainUI;
	 
	
	public userInterface (){
		
		mainUI = new JPanel ();
		
		this.add(mainUI);
		
		this.setSize(300, 200);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		title =new JLabel ("GLCM Normalization Options: ");
		title.setFont(new Font("Serif", Font.ITALIC, 20));
		choice1 = new JButton ("Single Angle Normalization");
		choice2 = new JButton ("Mutiple Angle Normalization");
		
		mainUI.add(title);
		mainUI.add(choice1);
		mainUI.add(choice2);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//this.pack();
		this.setVisible(true);
		
		choice1.addActionListener(this);
		choice2.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == choice1){
			singleNormalFrame sf = new singleNormalFrame();
			this.dispose();
		}
		
		if (e.getSource() == choice2){
			mutipleNormalFrame mf = new mutipleNormalFrame();
			this.dispose();
		}
	}
	
	public static void main(String[] args) {
		
		userInterface ui = new userInterface();
	}

}
