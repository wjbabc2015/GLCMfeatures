package calGLCM;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class mainCal {	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		loadImage image = new loadImage();
	}

}

class loadImage{
	BufferedImage img;
		
	public loadImage() {
		try {
			img = ImageIO.read(new File("C:/Users/GAI-WIN10/Desktop/GLCM_Cal/Image/45deg/camera0/PicA1009.bmp"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
