package calGLCM;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;

public class loadImage {
	
	BufferedImage img;
	File imageFile;
	int[][] grayLevelMatrix;
	int[] testPixel;
	
	loadImage (File file){
		this.imageFile = file;
		imageProcessing (imageFile);
	}
	
	/**
	 * Process the image file
	 * @param file
	 */
	private void imageProcessing (File file){
		try {
			img = ImageIO.read(file);
			grayLevelMatrix = imageToGray (img);
			testPixel = testPixel(img);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Convert image to Gray-level matrix
	 * @param image
	 * @return 2-D matrix with GLs value
	 */
	private int[][] imageToGray (BufferedImage image){
		int height = image.getHeight();
		int width = image.getWidth();
		
		int[][] result = new int[height][width];
		
		for (int h =0; h < height; h++){
			for (int w = 0; w < width; w ++){
				Color c = new Color (image.getRGB(w, h));
				
				result[h][w] = (int) (c.getRed() * 0.2989) + (int)(c.getGreen() * 0.5870) + (int)(c.getBlue() * 0.114);
				//result[h][w] = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
			}
		}
		
		return result;
	}
	
	private int[] testPixel (BufferedImage image){
		
		int[] result = new int[3];
		
		Color c = new Color (image.getRGB(0, 39));
		
		result[0] = c.getRed();
		result[1] = c.getGreen();
		result[2] = c.getBlue();
		
		return result;
	}
	
	public int[][] getGrayLevelMatrix (){
		return grayLevelMatrix;
	}
	
	public int[] getRGBvalue (){
		return testPixel;		
	}
	
	public static void main(String[] args) {
		
		File loadingFile = new File ("C:/Users/jiabin/Desktop/GLCM_Cal/Image/45deg/camera0/PicA1009.bmp");
		
		loadImage lI = new loadImage (loadingFile);
		
		int[][] tempResult = lI.getGrayLevelMatrix();
		int[] testResult = lI.getRGBvalue();
		
		for (int row = 40; row < 50; row ++){
			for (int column = 0; column < tempResult[row].length; column ++){
				System.out.print(tempResult[row][column] + " ");
			}
			System.out.println();
		}
		
		System.out.println(tempResult.length + " " + tempResult[1].length);
		
		System.out.println(testResult[0] + " " + testResult[1] + " " + testResult[2]);
		
	}
}
