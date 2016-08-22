package calGLCM;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
	
	String path;
	
	
	boolean isSymetric = false;
	
	boolean[] angle = new boolean[4];
	
	int elementDis = 1;
	
	calculationGLCM glcm1;
	calculationGLCM glcm2;
	calculationGLCM glcm3;
	calculationGLCM glcm4;
	
	
	public mutipleNormalFrame (String path) {
		
		this.path = path;
		
		mainFrame = new JPanel(new BorderLayout ());
		pmemu = new JPanel();
		pbutton = new JPanel();
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
	
	private double[][] matrixNormalization (double[][] matrix, int cout){
		
		for (int a = 0; a < 256; a++){
			for (int b = 0; b < 256; b++){
				matrix[a][b] /= cout;
			}
		}
		return matrix;
	}
	
	private void runGLCMprocess (String folderName, String resultName, int dis, boolean sym, boolean[] subangle){
		
		File loadingFile = new File (path + folderName);
		File[] allFiles = loadingFile.listFiles();
		
		ArrayList<double[]> hfeatures = new ArrayList<double[]>();
		ArrayList<double[]> vfeatures = new ArrayList<double[]>();
		ArrayList<double[]> diafeatures = new ArrayList<double[]>();
		ArrayList<double[]> andiafeatures = new ArrayList<double[]>();
		ArrayList<double[]> averfeatures = new ArrayList<double[]>();
		ArrayList<String> imageName = new ArrayList<String>();
		
		for (int num = 0; num < allFiles.length; num++){
			
			double[] hresult = null;
			double[] vresult = null;
			double[] dresult = null;
			double[] aresult = null;
			
			File imageFile = allFiles[num];

			loadImage img = new loadImage (imageFile);
			
			String outFileName = imageFile.getName();
			
			imageName.add(outFileName);
			
			int[][] matrix = img.getGrayLevelMatrix();
			
			//ef.outCVS(matrix);
			
			double[][] glcm = new double[256][256];
			int count = 0;
			
			if (subangle[0]){
				glcm1 = new calculationGLCM (matrix, "0", dis, sym, true);
				
				//glcm = mergeMatrix(glcm, glcm1.getGLCM()); //used for Thai's matlab code
				
				GLCMFeatures hgf = new GLCMFeatures (glcm1.getGLCM());
				
				hresult = hgf.getResult();
				
				hfeatures.add(hresult);
				
				count ++;
			}
			
			if (subangle[1]){
				glcm2 = new calculationGLCM (matrix, "45", dis, sym, true);
				
				//glcm = mergeMatrix(glcm, glcm2.getGLCM());
				
				GLCMFeatures vgf = new GLCMFeatures (glcm2.getGLCM());
				
				vresult = vgf.getResult();
				
				vfeatures.add(vresult);
				count ++;
			}
			
			if (subangle[2]){
				glcm3 = new calculationGLCM (matrix, "90", dis, sym, true);
				
				//glcm = mergeMatrix(glcm, glcm3.getGLCM());
				
				GLCMFeatures dgf = new GLCMFeatures (glcm3.getGLCM());
				
				dresult = dgf.getResult();
				
				diafeatures.add(dresult);
				count ++;
			}
			
			if (subangle[3]){
				glcm4 = new calculationGLCM (matrix, "135", dis, sym, true);
				
				//glcm = mergeMatrix(glcm, glcm4.getGLCM());
				
				GLCMFeatures agf = new GLCMFeatures (glcm4.getGLCM());
				
				aresult = agf.getResult();
				
				andiafeatures.add(aresult);
				count ++;
			}
			
			//glcm = matrixNormalization (glcm, count);
			
			//ef.outCVS(glcm);

//System.out.println(count + " " + elementDis + " " + isSymetric + " " + angle[0] + " " + angle[1] + " " + angle[2] + " " + angle[3]);
/*			
			for (int r = 0; r < 256; r ++){
				for (int c = 0; c < 256; c ++){
					glcm[r][c] /= count;
				}
			}
*/			double[] result = new double[aresult.length];

			for (int r = 0; r < result.length; r ++) {
				result[r] = (hresult[r] + vresult[r] + dresult[r] + aresult[r]) / count;
			}
			
			averfeatures.add(result);

			/*				
			System.out.println("Autocorrelation: " + result[0]);
			System.out.println("Contrast: " + result[1]);
			System.out.println("Correlation: " + result[2]);
			System.out.println("Cluster Prominence: " + result[3]);
			System.out.println("Cluster Shade: " + result[4]);
			System.out.println("Dissimilarity: " + result[5]);
			System.out.println("Energy: " + result[6]);
			System.out.println("Entropy: " + result[7]);
			System.out.println("Homogeneity: " + result[8]);
			System.out.println("Maximum Probability: " + result[9]);
			System.out.println("Variance: " + result[10]);
			System.out.println("Sum Average: " + result[11]);
			System.out.println("Sum Entropy: " + result[12]);
			System.out.println("Difference Entropy: " + result[13]);
			System.out.println("IDM: " + result[14]);
			
				
			for (int a = 0; a < 256 ;a ++ ) {
				for (int b= 0; b< 256 ; b ++ ) {
					System.out.print(glcm[a][b] + " ");
				}
				System.out.println();
			}
*/		
		}
		
		exportFile ef = new exportFile (resultName, path);
		int pos = 0;
		
		ef.initiateFile("Horizontal Result");

		try {
			for(double[] array: hfeatures){
				ef.fileProcessing(array, imageName.get(pos));
			}
			
			ef.printTitle("Vertical Result");
			pos = 0;
			
			for(double[] array: vfeatures){
				ef.fileProcessing(array, imageName.get(pos));
			}
			
			ef.printTitle("Diagram Result");
			pos = 0;
			
			for(double[] array: diafeatures){
				ef.fileProcessing(array, imageName.get(pos));
			}
			
			ef.printTitle("AntiDiagram Result");
			pos = 0;
			
			for(double[] array: vfeatures){
				ef.fileProcessing(array, imageName.get(pos));
			}
			
			ef.printTitle("Average Result");
			pos = 0;
			
			for(double[] array: averfeatures){
				ef.fileProcessing(array, imageName.get(pos));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == run){
			
			elementDis = Integer.parseInt(distance.getText());
			
			runGLCMprocess("p-direction", "P-Direction", elementDis, isSymetric, angle);
			
			runGLCMprocess("s-direction", "S-Direction", elementDis, isSymetric, angle);
					
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