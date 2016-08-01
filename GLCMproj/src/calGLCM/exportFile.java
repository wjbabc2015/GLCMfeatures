package calGLCM;

import java.io.*;

public class exportFile {
	
	String fileName;

	public exportFile (String name) {
		this.fileName = name;
	}
	
	public void initiateFile () {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter (new File("C:/Users/jiabin/Desktop/GLCM_Cal/" + fileName + ".csv"));
			
			StringBuilder sb = new StringBuilder ();
			
			sb.append("Pic\\Features");
			sb.append(",");
			sb.append("ASM");
			sb.append(',');
			sb.append("CON");
			sb.append(',');
			sb.append("COR");
			sb.append(',');
			sb.append("IDM");
			sb.append(',');
			sb.append("ENT");
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
			pw = new PrintWriter(new FileWriter("C:/Users/jiabin/Desktop/GLCM_Cal/" + fileName + ".csv", true));
			
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
			
			sb.append('\n');
			
			pw.write(sb.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (pw != null) pw.close();
		}
	}
}
