package calGLCM;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class singleNormalFrame extends JFrame implements ActionListener, ItemListener{
	
	JPanel pmemu;
	JPanel pbutton;
	JPanel checkBox;
	
	JButton run;
	JButton back;
	
	JCheckBox symetric;
	JCheckBox normal;
	
	JComboBox degree;
	
	boolean isSymetric = false;
	boolean isNormalization = false;
	
	String degreeAngle = "";
	
	
	
	public singleNormalFrame () {
		
		pmemu = new JPanel ();
		pbutton = new JPanel ();
		checkBox = new JPanel();
		
		this.setLayout(new GridLayout(3, 1));
		pmemu.setLayout(new GridLayout(2, 1));
		checkBox.setLayout(new GridLayout(1, 2));
		
		
		JLabel title = new JLabel ("Single Angle process");
		title.setFont(new Font("Serif", Font.ITALIC, 30));
		
		this.add(title);
		this.add(pmemu);
		this.add(pbutton);
		
		run = new JButton ("Run");
		back = new JButton ("Back");
		
		symetric = new JCheckBox ("Symmetric");
		normal = new JCheckBox ("Normalization");
		
		String[] degreeScale = {"0", "45", "90", "135"}; 
		
		degree = new JComboBox (degreeScale);
		degree.setSize(10, 5);
		
		pmemu.add(degree);
		pmemu.add(checkBox);
		checkBox.add(symetric);
		checkBox.add(normal);
		
		pbutton.add(back);
		pbutton.add(run);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		this.pack();
		//this.setSize(300, 400);
		
		run.addActionListener(this);
		back.addActionListener(this);
		
		symetric.addItemListener(this);
		normal.addItemListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == run){
			
		}
		
		if (e.getSource() == back){
			userInterface mf = new userInterface();
			this.dispose();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent i) {
		
		if (i.getItemSelectable() == symetric){
			isSymetric = true;
		}
		
		if (i.getItemSelectable() == normal){
			isNormalization = true;
		}
		
	}

}
