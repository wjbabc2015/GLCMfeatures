package calGLCM;

import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class mainCal {	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File loadingFile = new File ("C:/Users/jiabin/Desktop/GLCM_Cal/Image/45deg/camera0/PicA1009.bmp");

		loadImage img = new loadImage (loadingFile);
		int[][] matrix = img.getGrayLevelMatrix();
		
		calculationGLCM glcm = new calculationGLCM (matrix, 1, 0, "0");
		
		int[][] result = glcm.getGLCM();
		
		for (int a = 0; a < 256 ;a ++ ) {
			for (int b= 0; b< 256 ; b ++ ) {
				System.out.print(result[a][b] + " ");
			}
			System.out.println();
		}

		
		
	}

}