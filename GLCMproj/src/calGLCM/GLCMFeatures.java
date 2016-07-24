package calGLCM;

public class GLCMFeatures {
	
	double[][] matrix;
	
	double[] result;
/*	
	double asm = 0.0; ---- 0
	
	double contrast=0.0; ---- 1
	
	double correlation=0.0; ---- 2
	
	double IDM=0.0; ---- 3
	
	double entropy=0.0; ---- 4
	double suma = 0.0; ---- 5
*/	
	
 
	public GLCMFeatures (double[][] glcm){
		
		this.matrix = glcm;
		result = new double [6];
		
/*					
		for (int a = 0; a < 256 ;a ++ ) {
			for (int b= 0; b< 256 ; b ++ ) {
				System.out.print(matrix[a][b] + " ");
			}
			System.out.println();
		}*/
		
		doIcalculateASM ();
		doIcalculateContrast ();
		doIcalculateCorrelation ();
		doIcalculateIDM ();
		doIcalculateEntropy ();
		
	}

	private void doIcalculateASM() {
		
		for (int a = 0; a < 256; a ++){
			
			for (int b = 0; b<256; b ++){
				result[0] += (matrix[a][b]*matrix[a][b]);
			}
		}
		
	}
	
	private void doIcalculateContrast() {
		
		for (int a = 0; a < 256; a ++){
			
			for (int b = 0; b<256; b ++){
				result[1] +=(a-b)*(a-b)*(matrix[a][b]);
			}
		}
		
	}

	private void doIcalculateCorrelation() {
		
		double px=0;
		double py=0;
		double stdevx=0.0;
		double stdevy=0.0;
		
		for (int a = 0; a < 256; a ++){
			
			for (int b = 0; b<256; b ++){
				px=px+a*matrix [a][b];  
                py=py+b*matrix [a][b];
			}
		}
		
		for (int c = 0; c < 256; c ++){
			
			for (int d = 0; d<256; d ++){
				stdevx=stdevx+(c-px)*(c-px)*matrix [c][d];
				stdevy=stdevy+(d-py)*(d-py)*matrix [c][d];
			}
		}
		
		for (int e = 0; e < 256; e ++){
			for (int f = 0; f < 256; f ++){
				result[2] +=( (e-px)*(f-py)*matrix [e][f]/(stdevx*stdevy)) ;
			}
		}
		
	}



	private void doIcalculateIDM() {
		
		for (int a = 0; a < 256; a ++){
			
			for (int b = 0; b<256; b ++){
				result[3] += (matrix[a][b]/(1+(a-b)*(a-b)));
			}
		}
	}



	private void doIcalculateEntropy() {
		
		for (int a = 0; a < 256; a ++){
			
			for (int b = 0; b < 256; b ++){
				if (matrix[a][b] != 0){
					result[4] += -(matrix[a][b]*(Math.log(matrix[a][b])));
					result[5] +=  matrix[a][b];
				}
			}
		}
	}
	
	public double[] getResult(){
		return result;
	}
}
