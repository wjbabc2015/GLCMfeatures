package calGLCM;

import java.io.*;

public class exportFile {
	
	double[] exportResult;

	public exportFile (double[] result) {
		this.exportResult = result;
		fileProcessing();
	}
	
	public void initiateFile () {
		try {
			PrintWriter pw = new PrintWriter (new File("C:/Users/jiabin/Desktop/GLCM_Cal/result.csv"));
			
			StringBuilder sb = new StringBuilder ();
			sb.
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void fileProcessing (){
		try {
			PrintWriter pw = new PrintWriter(new File("C:/Users/jiabin/Desktop/GLCM_Cal/result.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
