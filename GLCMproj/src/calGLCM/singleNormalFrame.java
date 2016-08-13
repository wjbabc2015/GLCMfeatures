package calGLCM;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class singleNormalFrame extends JFrame implements ActionListener, ItemListener{
	
	JPanel mainFrame;
	JPanel pmemu;
	JPanel pbutton;
	JPanel degreeBox;
	JPanel checkBox;
	JPanel distanceBox;
	
	JButton run;
	JButton back;
	
	JCheckBox symetric;
	JCheckBox normal;
	
	JComboBox degree;
	
	JTextField distance;
	
	
	boolean isSymetric = false;
	boolean isNormalization = false;
	
	String degreeAngle = "";
	
	int elementDis = 1;
	
	String path;
	
	
	
	public singleNormalFrame (String path) {
		
		this.path = path;
		
		mainFrame = new JPanel(new BorderLayout ());
		pmemu = new JPanel ();
		pbutton = new JPanel ();
		checkBox = new JPanel();
		degreeBox = new JPanel();
		distanceBox = new JPanel();
		
		pmemu.setLayout(new GridLayout(4, 1));
		checkBox.setLayout(new GridLayout(1, 2));
		degreeBox.setLayout(new GridLayout(1, 2));
		distanceBox.setLayout(new GridLayout(1, 2));
		
		
		JLabel title = new JLabel ("Single Angle process", JLabel.CENTER);
		JLabel separate = new JLabel ("------------------------------------------", JLabel.CENTER);
		title.setFont(new Font("Serif", Font.ITALIC, 30));
		
		JLabel degreeLab = new JLabel ("Angle Degree: ");
		JLabel distanceLab = new JLabel ("Distance: ");
		
		distance = new JTextField ();
		
		run = new JButton ("Run");
		back = new JButton ("Back");
		
		symetric = new JCheckBox ("Symmetric");
		normal = new JCheckBox ("Normalization");
		
		String[] degreeScale = {"0", "45", "90", "135"}; 
		
		degree = new JComboBox (degreeScale);
		degree.setSize(10, 5);
		
		pmemu.add(degreeBox);
		pmemu.add(separate);
		pmemu.add(distanceBox);
		pmemu.add(checkBox);
		
		checkBox.add(symetric);
		checkBox.add(normal);
		degreeBox.add(degreeLab);
		degreeBox.add(degree);
		distanceBox.add(distanceLab);
		distanceBox.add(distance);
		
		pbutton.add(back);
		pbutton.add(run);
		
		mainFrame.add(title, BorderLayout.PAGE_START);
		mainFrame.add(pmemu, BorderLayout.CENTER);
		mainFrame.add(pbutton, BorderLayout.PAGE_END);
		
		this.add(mainFrame);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		this.pack();
		//pmemu.setSize(200, 100);
		//this.setSize(600, 300);
		
		run.addActionListener(this);
		back.addActionListener(this);
		
		symetric.addItemListener(this);
		normal.addItemListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == run){
			
			exportFile ef = new exportFile ("singleResult");
			
			ef.initiateFile();
			
			degreeAngle = degree.getSelectedItem().toString();
			
			elementDis = Integer.parseInt(distance.getText());
			
			File loadingFile = new File (path);
			File[] allFiles = loadingFile.listFiles();
			
//System.out.println(degreeAngle + " " + elementDis + " " + isSymetric + " " + isNormalization);
			
			for (int n = 0; n < allFiles.length; n++){
				File imageFile = allFiles[n];

				loadImage img = new loadImage (imageFile);
				
				int[][] matrix = img.getGrayLevelMatrix();
				
				calculationGLCM glcm = new calculationGLCM (matrix, degreeAngle, elementDis, isSymetric, isNormalization);
				
				
				
				GLCMFeatures gf = new GLCMFeatures (glcm.getGLCM());
				
				double[] result = gf.getResult();
				
				String outFileName = imageFile.getName();
				try {
					ef.fileProcessing(result, outFileName);
					ef.outCVS(glcm.getGLCM());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
/*				
				System.out.println("Angular Second Moment: " + result[0]);
				System.out.println("Contrast: " + result[1]);
				System.out.println("Correlation: " + result[2]);
				System.out.println("Inverse Difference Moment: " + result[3]);
				System.out.println("Entropy: " + result[4]);
				System.out.println("Sum of all GLCM elements: " + result[5]);
				System.out.println();*/
			}
			
			
			
			
/*			
			for (int a = 0; a < 256 ;a ++ ) {
				for (int b= 0; b< 256 ; b ++ ) {
					System.out.print(result[a][b] + " ");
				}
				System.out.println();
			}*/
		}
		
		if (e.getSource() == back){
			userInterface mf = new userInterface();
			this.dispose();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent i) {
		
		if (i.getItemSelectable() == symetric){
			isSymetric = !isSymetric;
		}
		
		if (i.getItemSelectable() == normal){
			isNormalization = !isNormalization;
		}
		
	}

}
