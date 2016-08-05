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

		doNormalization ();
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
	
	private void doNormalization () {

		for (double[] eleArray : matrix) {
			for (double ele : eleArray) {
				sum += ele;
			}
		}

//System.out.println(sum);	

		for (int r = 0; r < matrix.length; r ++){
			for (int w = 0; w < matrix[1].length; w ++){
				matrix[r][w] = matrix[r][w] / sum;
			}
		}
	}
	
	private void initialization (){
		
		for (int r = 0; r < matrix.length; r ++){
			for (int w = 0; w < matrix[1].length; w ++){
				
				mulContrast[r][w] += (r - w) * (r - w);
				mulDissimilarity[r][w] += Math.abs(r - w);
				
				px += (r + 1) * matrix[r][w];
				py += (w + 1) * matrix[r][w];
				
				pxplusy[r + w] += matrix[r][w];
				pxminusy[Math.abs(r - w)] += matrix[r][w];
				
			}
		}
		
		for (int r = 0; r < matrix.length; r ++){
			for (int w = 0; w < matrix[r].length; w++){
				
//System.out.println(stdevx);
				stdevx=stdevx+(r-px + 1)*(r-px + 1)*matrix [r][w];
				stdevy=stdevy+(w-py + 1)*(w-py + 1)*matrix [r][w];
			}
			//System.out.println(stdevx + ":" + stdevy);
		}
		
		stdevx = Math.sqrt(stdevx);
		stdevy = Math.sqrt(stdevy);
		mean = 1 / (256 * 256);
	}
	
	private void doIcalculateAutocorrelation(){
//System.out.println(matrix[20][115] * (-px + 21)*(-px + 21));	
		for (int r = 0; r < matrix.length; r ++){
			for (int w = 0; w < matrix[1].length; w ++){
			
				result[0] += (r + 1) * (w + 1) * matrix[r][w];
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
//System.out.println(px + ":" + py);
//System.out.println(stdevx + ":" + stdevy);
		
		for (int e = 0; e < 256; e ++){
			for (int f = 0; f < 256; f ++){
				result[2] +=((e - px + 1) * (f - py + 1) * matrix[e][f]) / (stdevx * stdevy) ;
			}
		}
		//result[2] +=(result[0] - px * py) / (stdevx * stdevy) ;
//System.out.println(result[2]);
	}
	
	private void doIcalculateClusterProminence (){
//System.out.println(Math.pow((- px - py + 2), 4.0) * matrix[0][0]);
		for (int r = 0; r < matrix.length; r ++){
			for (int w = 0; w < matrix[1].length; w ++){
				
				result[3] += Math.pow((r + w - px - py + 2), 4.0) * matrix[r][w];
				
			}
			//System.out.println(result[3]);
		}
	}
	
	private void doIcalculateClusterShade (){
		
		for (int r = 0; r < matrix.length; r ++){
			for (int w = 0; w < matrix[1].length; w ++){
				
				result[4] += Math.pow((r + w - px - py + 2), 3.0) * matrix[r][w];;
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
				
				result[10] += (r - mean + 1) * (r - mean + 1) * matrix[r][w];
			}
		}
		//System.out.println(result[10]);
	}
	
	private void doIcalculateSumAverage  (){
		
		for (int index = 0; index < pxplusy.length ; index++){
			result[11] += (index + 2) * pxplusy[index];
			//System.out.println(pxplusy[index]);
		}
		
		//System.out.println(result[11]);
	}



	private void doIcalculateIDM() {
		
		for (int a = 0; a < 256; a ++){
			
			for (int b = 0; b<256; b ++){
				result[14] += (matrix[a][b] / ( 1 + Math.abs(a - b) / 256));
			}
		}
		
		//System.out.println(result[14]);
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
