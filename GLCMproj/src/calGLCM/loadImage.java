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
	
	loadImage (){}
	
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
				
				int rgbValue = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
						
				//result[h][w] = (int) (c.getRed() * 0.2989) + (int)(c.getGreen() * 0.5870) + (int)(c.getBlue() * 0.114);
				result[h][w] = (int) (0.000004 * rgbValue * rgbValue * rgbValue + 0.003 * rgbValue * rgbValue - 0.033 * rgbValue + 1.8897);
			}
		}
		
		return result;
	}
	
	private int[] testPixel (BufferedImage image){
		
		int[] result = new int[3];
		
		Color c = new Color (image.getRGB(248, 134));    //x , y 
		
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
	
	public Image loadbitmap (String sdir, String sfile)
    {
		Image image;
		System.out.println("loading:" + sdir + sfile);
		try {
			FileInputStream fs = new FileInputStream (sdir + sfile);
			
			int bflen = 14;
			byte bf[] = new byte[bflen];
			fs.read(bf, 0, bflen);
			int bilen = 40;
			byte bi[] = new byte[bilen];
			fs.read(bi, 0, bilen);
			
			int nsize = (((int) bf[5] & 0xff)<<24)
					| (((int) bf[4] & 0xff)<<16)
					| (((int) bf[3] & 0xff)<<8)
					| (int) bf[2] & 0xff;
			
			System.out.println("File type is :"+(char)bf[0]+(char)bf[1]);
	        System.out.println("Size of file is :"+nsize);
System.out.println(bi[14]);

			int nwidth = (((int)bi[7]&0xff)<<24)
			| (((int)bi[6]&0xff)<<16)
			| (((int)bi[5]&0xff)<<8)
			| (int)bi[4]&0xff;
			System.out.println("Width is :"+nwidth);
			int nheight = (((int)bi[11]&0xff)<<24)
			| (((int)bi[10]&0xff)<<16)
			| (((int)bi[9]&0xff)<<8)
			| (int)bi[8]&0xff;
			System.out.println("Height is :"+nheight);

	        int nplanes = (((int)bi[13]&0xff)<<8) | (int)bi[12]&0xff;
	        System.out.println("Planes is :"+nplanes);
	        int nbitcount = (((int)bi[15]&0xff)<<8) | (int)bi[14]&0xff;
	        System.out.println("BitCount is :"+nbitcount);
	        
	        int nxpm = (((int)bi[27]&0xff)<<24)
            | (((int)bi[26]&0xff)<<16)
            | (((int)bi[25]&0xff)<<8)
            | (int)bi[24]&0xff;
            System.out.println("X-Pixels per meter is :"+nxpm);
            int nypm = (((int)bi[31]&0xff)<<24)
            | (((int)bi[30]&0xff)<<16)
            | (((int)bi[29]&0xff)<<8)
            | (int)bi[28]&0xff;
            System.out.println("Y-Pixels per meter is :"+nypm);
	                
            int nclrused = (((int)bi[35]&0xff)<<24)
            | (((int)bi[34]&0xff)<<16)
            | (((int)bi[33]&0xff)<<8)
            | (int)bi[32]&0xff;
            System.out.println("Colors used are :"+nclrused);
            int nclrimp = (((int)bi[39]&0xff)<<24)
            | (((int)bi[38]&0xff)<<16)
            | (((int)bi[37]&0xff)<<8)
            | (int)bi[36]&0xff;
            System.out.println("Colors important are :"+nclrimp);
            
            int nsizeimage = (((int)bi[23]&0xff)<<24)
            | (((int)bi[22]&0xff)<<16)
            | (((int)bi[21]&0xff)<<8)
            | (int)bi[20]&0xff;
            System.out.println("SizeImage is :"+nsizeimage);
            
            int nNumColors = 0;
            if (nclrused > 0)
                {
                nNumColors = nclrused;
                }
            else
                {
                nNumColors = (1&0xff)<<nbitcount;
                }
            System.out.println("The number of Colors is"+nNumColors);
            
            int  npalette[] = new int [nNumColors];
            byte bpalette[] = new byte [nNumColors*4];
            fs.read (bpalette, 0, nNumColors*4);
System.out.println(bpalette[14]);

			int nindex8 = 0;
			for (int n = 0; n < nNumColors; n++)
			    {
			    npalette[n] = (255&0xff)<<24
			    | (((int)bpalette[nindex8+2]&0xff)<<16)
			    | (((int)bpalette[nindex8+1]&0xff)<<8)
			    | (int)bpalette[nindex8]&0xff;
			     System.out.println ("Palette Color "+n
			    +" is:"+npalette[n]+" (res,R,G,B)= ("
			    +((int)(bpalette[nindex8+3]) & 0xff)+","
			    +((int)(bpalette[nindex8+2]) & 0xff)+","
			    +((int)bpalette[nindex8+1]&0xff)+","
			    +((int)bpalette[nindex8]&0xff)+")");
			    nindex8 += 4;
			    }
			
			int npad8 = (nsizeimage / nheight) - nwidth;
	        System.out.println("nPad is:"+npad8);
	        int  ndata8[] = new int [nwidth*nheight];
	        byte bdata[] = new byte [(nwidth+npad8)*nheight];
	        fs.read (bdata, 0, (nwidth+npad8)*nheight);
	        nindex8 = 0;
	        for (int j8 = 0; j8 < nheight; j8++)
	            {
	            for (int i8 = 0; i8 < nwidth; i8++)
	            {
	            ndata8 [nwidth*(nheight-j8-1)+i8] =
	                npalette [((int)bdata[nindex8]&0xff)];
	            nindex8++;
	            }
	            nindex8 += npad8;
	            }
	        System.out.println(ndata8[14]);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    return (Image) null;
    }
	
	public static void main(String[] args) {
		
		File loadingFile = new File ("C:/Users/jiabin/Desktop/GLCM_Cal/Image/45deg/camera0/PicA1009.bmp");
		
		loadImage lI = new loadImage (loadingFile);
		
		int[][] tempResult = lI.getGrayLevelMatrix();
		int[] testResult = lI.getRGBvalue();
				
		for (int row = 250; row < 260; row ++){
			for (int column = 230; column < 240; column ++){
				System.out.print(tempResult[row][column] + " ");
			}
			System.out.println();
		}
		
		//System.out.println(tempResult.length + " " + tempResult[1].length);
		
		System.out.println(tempResult[32][242]);
		
		System.out.println(testResult[0] + " " + testResult[1] + " " + testResult[2]);
		/*		
		loadImage lI = new loadImage();
		Image i = lI.loadbitmap("C:/Users/jiabin/Desktop/GLCM_Cal/Image/45deg/camera0/", "PicA1009.bmp");*/
	}
}
