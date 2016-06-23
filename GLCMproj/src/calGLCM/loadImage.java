package calGLCM;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;

public class loadImage {
	
	BufferedImage img;
	File imageFile;
	int[][] grayLevelMatrix;
	
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
				
				result[h][w] = (int) (c.getRed() * 0.299) + (int)(c.getGreen() * 0.587) + (int)(c.getBlue() * 0.114);
				//result[h][w] = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
			}
		}
		
		return result;
	}
	
	public int[][] getGrayLevelMatrix (){
		return grayLevelMatrix;
	}
	
	
}
