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
			sb.append("ASM");
			sb.append(',');
			sb.append("CON");
			sb.append(',');
			sb.append("COR");
			sb.append(',');
			sb.append("VAR");
			sb.append(',');
			sb.append("IDM");
			sb.append(",");
			sb.append("SAV");
			sb.append(',');
			sb.append("SEN");
			sb.append(',');
			sb.append("SVA");
			sb.append(',');
			sb.append("ENT");
			sb.append(',');
			sb.append("DEN");
			sb.append(",");
			sb.append("DVA");
			sb.append(',');
			sb.append("DIS");
			sb.append(',');
			sb.append("CLS");
			sb.append(',');
			sb.append("CLP");
			sb.append(',');
			sb.append("MIP");
			sb.append(',');
			sb.append("MAP");
			sb.append(',');
			sb.append("MEA");
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
			sb.append("ASM");
			sb.append(',');
			sb.append("CON");
			sb.append(',');
			sb.append("COR");
			sb.append(',');
			sb.append("VAR");
			sb.append(',');
			sb.append("IDM");
			sb.append(",");
			sb.append("SAV");
			sb.append(',');
			sb.append("SEN");
			sb.append(',');
			sb.append("SVA");
			sb.append(',');
			sb.append("ENT");
			sb.append(',');
			sb.append("DEN");
			sb.append(",");
			sb.append("DVA");
			sb.append(',');
			sb.append("DIS");
			sb.append(',');
			sb.append("CLS");
			sb.append(',');
			sb.append("CLP");
			sb.append(',');
			sb.append("MIP");
			sb.append(',');
			sb.append("MAP");
			sb.append(',');
			sb.append("MEA");
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
			sb.append(',');
			
			sb.append(result[15]);
			sb.append(',');
			
			sb.append(result[16]);
			
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
