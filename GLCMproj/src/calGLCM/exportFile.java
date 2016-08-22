package calGLCM;

import java.io.*;

public class exportFile {
	
	String fileName;
	String path;

	public exportFile (String name, String path) {
		this.fileName = name;
		this.path = path;
	}
	
	public exportFile(){}
	
	public void initiateFile (String title) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter (new File(path + fileName + ".csv"));
			
			StringBuilder sb = new StringBuilder ();
			
			sb.append(title);
			sb.append('\n');
			sb.append("Pic\\Features");
			sb.append(",");
			sb.append("Autocorrelation");
			sb.append(',');
			sb.append("Contrast");
			sb.append(',');
			sb.append("Correlation");
			sb.append(',');
			sb.append("Cluster Prominence");
			sb.append(',');
			sb.append("Cluster Shade");
			sb.append(",");
			sb.append("Dissimilarity");
			sb.append(',');
			sb.append("Energy");
			sb.append(',');
			sb.append("Entropy");
			sb.append(',');
			sb.append("Homogeneity");
			sb.append(',');
			sb.append("Maximum Probability");
			sb.append(",");
			sb.append("Variance");
			sb.append(',');
			sb.append("Sum Average");
			sb.append(',');
			sb.append("Sum Entropy");
			sb.append(',');
			sb.append("Difference Entropy");
			sb.append(',');
			sb.append("IDM");
			sb.append('\n');
			
			pw.write(sb.toString());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (pw != null) pw.close();
		}
		
	}
	
	public void printTitle (String title) throws IOException{
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter(path + fileName + ".csv", true));
			
			StringBuilder sb = new StringBuilder ();

			sb.append('\n');
			sb.append(title);
			sb.append('\n');
			sb.append("Pic\\Features");
			sb.append(",");
			sb.append("Autocorrelation");
			sb.append(',');
			sb.append("Contrast");
			sb.append(',');
			sb.append("Correlation");
			sb.append(',');
			sb.append("Cluster Prominence");
			sb.append(',');
			sb.append("Cluster Shade");
			sb.append(",");
			sb.append("Dissimilarity");
			sb.append(',');
			sb.append("Energy");
			sb.append(',');
			sb.append("Entropy");
			sb.append(',');
			sb.append("Homogeneity");
			sb.append(',');
			sb.append("Maximum Probability");
			sb.append(",");
			sb.append("Variance");
			sb.append(',');
			sb.append("Sum Average");
			sb.append(',');
			sb.append("Sum Entropy");
			sb.append(',');
			sb.append("Difference Entropy");
			sb.append(',');
			sb.append("IDM");
			sb.append('\n');
			
			pw.write(sb.toString());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (pw != null) pw.close();
		}
	}
	
	public void fileProcessing (double[] result, String nameTitle) throws IOException{
		
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter(path + fileName + ".csv", true));
			
			StringBuilder sb = new StringBuilder ();
			
			sb.append(nameTitle);
			sb.append(',');
			
			sb.append(result[0]);
			sb.append(',');
			
			sb.append(result[1]);
			sb.append(',');
			
			sb.append(result[2]);
			sb.append(',');
			
			sb.append(result[3]);
			sb.append(',');
			
			sb.append(result[4]);		
			sb.append(',');
			
			sb.append(result[5]);
			sb.append(',');
			
			sb.append(result[6]);
			sb.append(',');
			
			sb.append(result[7]);
			sb.append(',');
			
			sb.append(result[8]);
			sb.append(',');
			
			sb.append(result[9]);
			sb.append(',');
			
			sb.append(result[10]);
			sb.append(',');
			
			sb.append(result[11]);
			sb.append(',');
			
			sb.append(result[12]);
			sb.append(',');
			
			sb.append(result[13]);
			sb.append(',');
			
			sb.append(result[14]);
			
			sb.append('\n');
			
			pw.write(sb.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (pw != null) pw.close();
		}
	}
	
	public void outCVS (int[][] array){
		
		StringBuilder sb = new StringBuilder ();
		
		PrintWriter pw = null;
		
		try {
			pw =new PrintWriter (new FileWriter(path + "grayscaleMatrix.csv", true));
			
			for (int i = 0; i < array.length; i++){
				
				for (int j = 0; j < array[0].length; j++){
					sb.append(array[i][j]);
					sb.append(",");
				}
				
				sb.append("\n");
			}
			
			pw.write(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (pw != null) pw.close();
		}
	}
	
public void outCVS (double[][] array){
		
		StringBuilder sb = new StringBuilder ();
		
		PrintWriter pw = null;
		
		try {
			pw =new PrintWriter (new FileWriter(path + "GLCMMatrix.csv", true));
			
			for (int a = 1; a <= 256; a ++){
				sb.append(a);
				sb.append(',');
			}
			
			sb.append('\n');
			
			for (int i = 0; i < array.length; i++){
				
				for (int j = 0; j < array[0].length; j++){
					sb.append(array[i][j]);
					sb.append(",");
				}
				
				sb.append("\n");
			}
			
			pw.write(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (pw != null) pw.close();
		}
	}
}
