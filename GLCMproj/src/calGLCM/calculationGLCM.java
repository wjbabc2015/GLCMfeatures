package calGLCM;

public class calculationGLCM {
	
	int[][] matrix;
	int dx, dy;
	String degree;
	
	int[][] GLCM = new int[256][256];

	public calculationGLCM(int[][] graylevelMatrix, int dx, int dy, String degree){
		this.matrix = graylevelMatrix;
		this.dx = dx;
		this.dy = dy;
		this.degree = degree;
		
		computeGLCM ();
		
	}
	
	private void computeGLCM (){
		
		switch (degree) {
		case "0": {
			if (dx > 0){
				for (int h = 0; h < matrix.length; h++){
					for (int w = 0; w < matrix[h].length - dx; w++){
						int a = matrix[h][w];
						int b = matrix[h][w + dx];
						
						GLCM[a][b] ++;
						GLCM[b][a] ++;
					}
				}
			}
			break;
		}
		
		case "45":{
			if (dx > 0 && dy > 0){
				for (int h = dy; h < matrix.length; h++){
					for (int w = 0; w < matrix[h].length - dx; w++){
						int a = matrix[h][w];
						int b = matrix[h - dy][w + dx];
						
						GLCM[a][b] ++;
						GLCM[b][a] ++;
					}
				}
			}
			break;
		}
		
		case "90":{
			if (dy > 0){
				for (int h = 0; h < matrix.length; h++){
					for (int w = dx; w < matrix[h].length; w++){
						int a = matrix[h][w];
						int b = matrix[h][w - dx];
						
						GLCM[a][b] ++;
						GLCM[b][a] ++;
					}
				}
			}
			break;
		}
		
		case "135":{
			if (dx > 0){
				for (int h = dy; h < matrix.length; h++){
					for (int w = dx; w < matrix[h].length; w++){
						int a = matrix[h][w];
						int b = matrix[h - dy][w - dx];
						
						GLCM[a][b] ++;
						GLCM[b][a] ++;
					}
				}
			}
			break;
		}
		
		default: break;
		}
	}
	
	public int[][] getGLCM (){
		return GLCM;
	}
	
	
}
