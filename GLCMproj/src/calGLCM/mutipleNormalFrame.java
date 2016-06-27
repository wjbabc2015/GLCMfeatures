package calGLCM;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.*;

public class mutipleNormalFrame extends JFrame implements ActionListener, ItemListener{
	
	JPanel mainFrame;
	JPanel pmemu;
	JPanel pbutton;
	JPanel degreeBox;
	JPanel checkBox;
	JPanel distanceBox;
	
	JButton run;
	JButton back;
	
	JCheckBox right;
	JCheckBox upRight;
	JCheckBox up;
	JCheckBox upLeft;
	
	JCheckBox symetric;
	
	JTextField distance;
	
	
	boolean isSymetric = false;
	
	boolean[] angle = new boolean[4];
	
	int elementDis = 1;
	
	calculationGLCM glcm1;
	calculationGLCM glcm2;
	calculationGLCM glcm3;
	calculationGLCM glcm4;
	
	
	public mutipleNormalFrame () {
		
		mainFrame = new JPanel(new BorderLayout ());
		pmemu = new JPanel ();
		pbutton = new JPanel ();
		checkBox = new JPanel();
		degreeBox = new JPanel();
		distanceBox = new JPanel();
		
		for (int i = 0; i < angle.length; i++) {
			angle[i] = false;
		}
		
		pmemu.setLayout(new GridLayout(4, 1));
		checkBox.setLayout(new GridLayout(1, 2));
		degreeBox.setLayout(new GridLayout(1, 4));
		distanceBox.setLayout(new GridLayout(1, 2));
		
		
		JLabel title = new JLabel ("Mutiple Angle process", JLabel.CENTER);
		JLabel separate = new JLabel ("Select the angle for Normalization: ", JLabel.LEFT);
		JLabel notification = new JLabel ("Select GLCM Mode: ", JLabel.LEFT);
		title.setFont(new Font("Serif", Font.ITALIC, 30));
		
		JLabel distanceLab = new JLabel ("Distance: ");
		
		distance = new JTextField ();
		
		run = new JButton ("Run");
		back = new JButton ("Back");
		
		symetric = new JCheckBox ("Symmetric");
		
		right = new JCheckBox ("0");
		upRight = new JCheckBox ("45");
		up = new JCheckBox ("90");
		upLeft = new JCheckBox ("135");
		
		pmemu.add(distanceBox);
		pmemu.add(separate);
		pmemu.add(degreeBox);
		pmemu.add(checkBox);
		
		checkBox.add(notification);
		checkBox.add(symetric);
		
		distanceBox.add(distanceLab);
		distanceBox.add(distance);
		degreeBox.add(right);
		degreeBox.add(upRight);
		degreeBox.add(up);
		degreeBox.add(upLeft);
		
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
		right.addItemListener(this);
		upRight.addItemListener(this);
		up.addItemListener(this);
		upLeft.addItemListener(this);
		
	}
	
	private double[][] mergeMatrix (double[][] mainMatrix, double[][] addMatrix){
		
		for (int a = 0; a < 256; a++){
			for (int b = 0; b < 256; b++){
				mainMatrix[a][b] += addMatrix[a][b];
			}
		}
		return mainMatrix;
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == run){
			elementDis = Integer.parseInt(distance.getText());
			
			File loadingFile = new File ("C:/Users/jiabin/Desktop/GLCM_Cal/Image/45deg/camera0/PicA1009.bmp");

			loadImage img = new loadImage (loadingFile);
			
			int[][] matrix = img.getGrayLevelMatrix();
			
			double[][] glcm = new double[256][256];
			int count = 0;
			
			if (angle[0]){
				glcm1 = new calculationGLCM (matrix, "0", elementDis, isSymetric, false);
				
				glcm = mergeMatrix(glcm, glcm1.getGLCM());
				count ++;
			}
			
			if (angle[1]){
				glcm2 = new calculationGLCM (matrix, "45", elementDis, isSymetric, false);
				
				glcm = mergeMatrix(glcm, glcm2.getGLCM());
				count ++;
			}
			
			if (angle[2]){
				glcm3 = new calculationGLCM (matrix, "90", elementDis, isSymetric, false);
				
				glcm = mergeMatrix(glcm, glcm3.getGLCM());
				count ++;
			}
			
			if (angle[3]){
				glcm4 = new calculationGLCM (matrix, "135", elementDis, isSymetric, false);
				
				glcm = mergeMatrix(glcm, glcm4.getGLCM());
				count ++;
			}

//System.out.println(count + " " + elementDis + " " + isSymetric + " " + angle[0] + " " + angle[1] + " " + angle[2] + " " + angle[3]);
/*			
			for (int r = 0; r < 256; r ++){
				for (int c = 0; c < 256; c ++){
					glcm[r][c] /= count;
				}
			}
*/			
			
			GLCMFeatures gf = new GLCMFeatures (glcm);
			
			double[] result = gf.getResult();
			
			System.out.println("Angular Second Moment: " + result[0]);
			System.out.println("Contrast: " + result[1]);
			System.out.println("Correlation: " + result[2]);
			System.out.println("Inverse Difference Moment: " + result[3]);
			System.out.println("Entropy: " + result[4]);
			System.out.println("Sum of all GLCM elements: " + result[5]);
			
/*					
			for (int a = 0; a < 256 ;a ++ ) {
				for (int b= 0; b< 256 ; b ++ ) {
					System.out.print(glcm[a][b] + " ");
				}
				System.out.println();
			}
*/				
			
			
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
		
		if (i.getItemSelectable() == right){
			angle[0] = !angle[0];
		}
		
		if (i.getItemSelectable() == upRight){
			angle[1] = !angle[1];
		}
		
		if (i.getItemSelectable() == up){
			angle[2] = !angle[2];
		}
		
		if (i.getItemSelectable() == upLeft){
			angle[3] = !angle[3];
		}
		
	}

}