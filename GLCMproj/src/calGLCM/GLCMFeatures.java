package calGLCM;

public class GLCMFeatures {
	
	double[][] matrix;
	
	double[] result;
	
	double[][] mulContrast;
	double[][] mulDissimilarity;
	
	double px=0;
	double py=0;
	double stdevx=0.0;
	double stdevy=0.0;
	
	double[] pxplusy;
	double[] pxminusy;
	
	double sum = 0;
	double mean = 0;
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
		result = new double [15];
		mulContrast = new double[256][256];
		mulDissimilarity = new double[256][256];
		
		pxplusy = new double[2 * matrix.length - 1];
		pxminusy = new double[matrix.length];
		
/*					
		for (int a = 0; a < 256 ;a ++ ) {
			for (int b= 0; b< 256 ; b ++ ) {
				System.out.print(matrix[a][b] + " ");
			}
			System.out.println();
		}*/
		
		initialization();
		doIcalculateAutocorrelation();
		doIcalculateContrast ();
		doIcalculateCorrelation ();
		doIcalculateClusterProminence();
		doIcalculateClusterShade();
		doIcalculateDissimilarity();
		doIcalculateEnergy();
		doIcalculateHomogeneity();
		doIcalculateMaximumProbability();
		doIcalculateVariance();
		doIcalculateSumAverage();
		doIcalculateIDM ();
		doIcalculateEntropy ();
		
	}
	
	private void initialization (){
		
		for (int r = 0; r < matrix.length; r ++){
			for (int w = 0; w < matrix[1].length; w ++){
				
				mulContrast[r][w] += (r - w) * (r - w);
				mulDissimilarity[r][w] += Math.abs(r - w);
				
				px += r * matrix[r][w];
				py += w * matrix[r][w];
				
				pxplusy[r + w] += matrix[r][w];
				pxminusy[Math.abs(r - w)] += matrix[r][w];
				
				stdevx=stdevx+(r-px)*(r-px)*matrix [r][w];
				stdevy=stdevy+(w-py)*(w-py)*matrix [r][w];
				
				sum += matrix[r][w];
			}
		}
		
		stdevx = Math.sqrt(stdevx);
		stdevy = Math.sqrt(stdevy);
		mean = sum / (256 * 256);
	}
	
	private void doIcalculateAutocorrelation(){
		for (int r = 0; r < matrix.length; r ++){
			for (int w = 0; w < matrix[1].length; w ++){
				
				result[0] += r * w * matrix[r][w];
			}
		}
	}
	
	private void doIcalculateContrast() {
		
		for (int a = 0; a < matrix.length; a ++){
			
			for (int b = 0; b<matrix[1].length; b ++){
				result[1] += mulContrast[a][b] * matrix[a][b];
			}
		}
		
	}

	private void doIcalculateCorrelation() {
		
		for (int e = 0; e < 256; e ++){
			for (int f = 0; f < 256; f ++){
				result[2] +=( (e-px)*(f-py)*matrix [e][f]/(stdevx*stdevy)) ;
			}
		}	
	}
	
	private void doIcalculateClusterProminence (){
		
		for (int r = 0; r < matrix.length; r ++){
			for (int w = 0; w < matrix[1].length; w ++){
				
				result[3] += (r + w - px - py) * (r + w - px - py) * (r + w - px - py) * (r + w - px - py) * matrix[r][w];
			}
		}
	}
	
	private void doIcalculateClusterShade (){
		
		for (int r = 0; r < matrix.length; r ++){
			for (int w = 0; w < matrix[1].length; w ++){
				
				result[4] += (r + w - px - py) * (r + w - px - py) * (r + w - px - py) * matrix[r][w];
			}
		}
	}
	
	private void doIcalculateDissimilarity (){
		
		for (int r = 0; r < matrix.length; r ++){
			for (int w = 0; w < matrix[1].length; w ++){
				
				result[5] += mulDissimilarity[r][w] * matrix[r][w];
			}
		}
	}
	
	private void doIcalculateEnergy (){
		
		for (int r = 0; r < matrix.length; r ++){
			for (int w = 0; w < matrix[1].length; w ++){
				
				result[6] += matrix[r][w] * matrix[r][w];
			}
		}
	}
	
	private void doIcalculateHomogeneity(){
		
		for (int r = 0; r < matrix.length; r ++){
			for (int w = 0; w < matrix[1].length; w ++){
				
				result[8] += matrix[r][w] / (1 + mulContrast[r][w]);
			}
		}
	}
	
	private void doIcalculateMaximumProbability (){
		
		double max = 0;
		
		for (int r = 0; r < matrix.length; r ++){
			for (int w = 0; w < matrix[1].length; w ++){
				
				max = Math.max(max, matrix[r][w]);
			}
		}
		
		result[9] = max;
	}
	
	private void doIcalculateVariance (){
		
		for (int r = 0; r < matrix.length; r ++){
			for (int w = 0; w < matrix[1].length; w ++){
				
				result[10] += (w - mean) * (w - mean) * matrix[r][w];
			}
		}
	}
	
	private void doIcalculateSumAverage  (){
		
		for (int index = 0; index < pxplusy.length ; index++){
			result[11] += index * pxplusy[index];
		}
	}



	private void doIcalculateIDM() {
		
		for (int a = 0; a < 256; a ++){
			
			for (int b = 0; b<256; b ++){
				result[14] += (matrix[a][b] / ( 1 + (a-b) / 256));
			}
		}
	}



	private void doIcalculateEntropy() {
		
		for (int a = 0; a < 256; a ++){
			
			for (int b = 0; b < 256; b ++){
				if (matrix[a][b] != 0){
					result[7] += -(matrix[a][b]*(Math.log(matrix[a][b] + 2.2204e-16)));
				}
			}
		}
		
		//sum entropy
		for (int index = 0; index < pxplusy.length ; index++){
			result[12] +=  -(pxplusy[index]*(Math.log(pxplusy[index] + 2.2204e-16)));
		}
		
		//diff entropy
		for (int index = 0; index < pxminusy.length ; index++){
			result[13] +=  -(pxminusy[index]*(Math.log(pxminusy[index] + 2.2204e-16)));
		}
	}
	
	public double[] getResult(){
		return result;
	}
	
	public static void main(String[] arg[]) {
		
	}
}
