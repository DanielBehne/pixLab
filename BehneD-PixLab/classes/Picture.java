package classes;

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
    ///////////////////// constructors //////////////////////////////////

    /**
     * Constructor that takes no arguments 
     */
    public Picture ()
    {
        /* not needed but use it to show students the implicit call to super()
         * child constructors always call a parent constructor 
         */
        super();  
    }

    /**
     * Constructor that takes a file name and creates the picture 
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName)
    {
        // let the parent class handle this fileName
        super(fileName);
    }

    /**
     * Constructor that takes the width and height
     * @param height the height of the desired picture
     * @param width the width of the desired picture
     */
    public Picture(int height, int width)
    {
        // let the parent class handle this width and height
        super(width,height);
    }

    /**
     * Constructor that takes a picture and creates a 
     * copy of that picture
     * @param copyPicture the picture to copy
     */
    public Picture(Picture copyPicture)
    {
        // let the parent class do the copy
        super(copyPicture);
    }

    /**
     * Constructor that takes a buffered image
     * @param image the buffered image to use
     */
    public Picture(BufferedImage image)
    {
        super(image);
    }

    ////////////////////// methods ///////////////////////////////////////

    /**
     * Method to return a string with information about this picture.
     * @return a string with information about the picture such as fileName,
     * height and width.
     */
    public String toString()
    {
        String output = "Picture, filename " + getFileName() + 
            " height " + getHeight() 
            + " width " + getWidth();
        return output;

    }

    /** Method to set the blue to 0 */
    public void zeroBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                //pixelObj.setBlue(0);
                pixelObj.setGreen(0);
            }
        }
    }

    public void keepOnlyBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                //pixelObj.setBlue(0);
                pixelObj.setGreen(0);
                pixelObj.setRed(0);
            }
        }
    }

    public void negate()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setBlue(255-pixelObj.getBlue());
                pixelObj.setGreen(255-pixelObj.getGreen());
                pixelObj.setRed(255-pixelObj.getRed());
            }
        }
    }

    public void grayscale()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                int avg = (pixelObj.getBlue()+pixelObj.getGreen()+pixelObj.getRed())/3;
                pixelObj.setRed(avg);
                pixelObj.setGreen(avg);
                pixelObj.setBlue(avg);
            }
        }
    }

    public void fixUnderwater()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                if (pixelObj.getBlue() < 150) {
                    pixelObj.setBlue(125);
                }
            }
        }
    }
    
    /** Method that mirrors the picture around a 
     * vertical mirror in the center of the picture
     * from left to right */
    public void mirrorVertical()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < width / 2; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                rightPixel.setColor(leftPixel.getColor());
            }
        } 
    }

    public void mirrorVerticalRightToLeft()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = width/2; col < width; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                rightPixel.setColor(leftPixel.getColor());
            }
        } 
    }
    
    public void mirrorHorizontal()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        int height = pixels.length;
        for (int row = 0; row < height/2; row++) {
            for (int col = 0; col < pixels[0].length; col++) {
                topPixel = pixels[row][col];
                bottomPixel = pixels[height - row - 1][col];
                bottomPixel.setColor(topPixel.getColor());
            }
        }
    }
    
    public void mirrorHorizontalBotToTop()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        int height = pixels.length;
        for (int row = height/2; row < height; row++) {
            for (int col = 0; col < pixels[0].length; col++) {
                topPixel = pixels[row][col];
                bottomPixel = pixels[height - row - 1][col];
                bottomPixel.setColor(topPixel.getColor());
            }
        }
    }
    
    public void mirrorDiagonal()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel botPixel = null;
        int height = pixels.length;
        int width = pixels[0].length;
        for (int row = 0; row < width && row < height; row++) {
            for (int col = row + 1; col < width && col < height; col++) {
                topPixel = pixels[row][col];
                botPixel = pixels[col][row];
                topPixel.setColor(botPixel.getColor());
            }
        }
    }
    
    /** Mirror just part of a picture of a temple */
    public void mirrorTemple()
    {
        int mirrorPoint = 276;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        Pixel[][] pixels = this.getPixels2D();

        // loop through the rows
        for (int row = 27; row < 97; row++)
        {
            // loop from 13 to just before the mirror point
            for (int col = 13; col < mirrorPoint; col++)
            {
                //count++;
                leftPixel = pixels[row][col];      
                rightPixel = pixels[row]                       
                [mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
        //System.out.println(count);
    }

    public void mirrorArms()
    {
        int mirrorPoint = 192;
        Pixel topPixel = null;
        Pixel botPixel = null;
        Pixel[][] pixels = this.getPixels2D();

        for (int row = 168; row < mirrorPoint; row++)
        {
            for (int col = 94; col < 170; col++)
            {
                topPixel = pixels[row][col];      
                botPixel = pixels[mirrorPoint - row + mirrorPoint][col];
                botPixel.setColor(topPixel.getColor());
            }
        }
        
        int mirrorPoint2 = 192;
        Pixel topPixel2 = null;
        Pixel botPixel2 = null;

        for (int row = 168; row < mirrorPoint; row++)
        {
            for (int col = 239; col < 294; col++)
            {
                topPixel2 = pixels[row][col];      
                botPixel2 = pixels[mirrorPoint2 - row + mirrorPoint2][col];
                botPixel2.setColor(topPixel2.getColor());
            }
        }
    }
    
    public void mirrorGull()
    {
        int mirrorPoint = 345;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        Pixel[][] pixels = this.getPixels2D();

        for (int row = 227; row < 326; row++)
        {
            for (int col = 232; col < mirrorPoint; col++)
            {
                leftPixel = pixels[row][col];      
                rightPixel = pixels[row]                       
                [mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }
    
    /** copy from the passed fromPic to the
     * specified startRow and startCol in the
     * current picture
     * @param fromPic the picture to copy from
     * @param startRow the start row to copy to
     * @param startCol the start col to copy to
     */
    public void copy(Picture fromPic, int startRow, int startCol)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = 50, toRow = startRow; 
        fromRow < fromPixels.length &&
        toRow < 330; 
        fromRow++, toRow++)
        {
            for (int fromCol = 240, toCol = startCol; 
            fromCol < 450 &&
            toCol < 410;  
            fromCol++, toCol++)
            {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }   
    }

    /** Method to create a collage of several pictures */
    public void createCollage()
    {
        Picture flower1 = new Picture("flower1.jpg");
        Picture flower2 = new Picture("flower2.jpg");
        this.copy(flower1,0,0);
        this.copy(flower2,100,0);
        this.copy(flower1,200,0);
        Picture flowerNoBlue = new Picture(flower2);
        flowerNoBlue.zeroBlue();
        this.copy(flowerNoBlue,300,0);
        this.copy(flower1,400,0);
        this.copy(flower2,500,0);
        this.mirrorVertical();
        this.write("collage.jpg");
    }
    
    public void setGreen()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setBlue(0);
                //pixelObj.setGreen(0);
                pixelObj.setRed(0);
            }
        }
    }
    
    public void makePink()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                //pixelObj.setBlue(0);
                pixelObj.setGreen(0);
                //pixelObj.setRed(0);
            }
        }
    }
    
    public void myCollage()
    {
        Picture reese = new Picture("images/reese.jpg");
        Picture reeseGray = new Picture(reese);
        reeseGray.grayscale();
        Picture reeseBlue = new Picture(reese);
        reeseBlue.keepOnlyBlue();
        Picture reesePink = new Picture(reese);
        reesePink.makePink();
        
        this.copy(reeseBlue,100,185);
        this.copy(reeseGray,100,0);
        this.mirrorVertical();
        this.copy(reesePink,100,227);
        
        this.write("images/myCollage.jpg");
    }
    
    /** Method to show large changes in color 
     * @param edgeDist the distance for finding edges
     */
    public void edgeDetection(int edgeDist)
    {
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        Color rightColor = null;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; 
            col < pixels[0].length-1; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][col+1];
                rightColor = rightPixel.getColor();
                if (leftPixel.colorDistance(rightColor) > 
                edgeDist)
                    leftPixel.setColor(Color.BLACK);
                else
                    leftPixel.setColor(Color.WHITE);
            }
        }
    }
    
    public void edgeDetectionVert(int edgeDist)
    {
        Pixel topPixel = null;
        Pixel botPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        Color rightColor = null;
        for (int col = 0; col < pixels[0].length; col++) {
            for (int row = 0; row < pixels.length-1; row++) {
                topPixel = pixels[row][col];
                botPixel = pixels[row+1][col];
                rightColor = botPixel.getColor();
                if (topPixel.colorDistance(rightColor) > 
                edgeDist)
                    topPixel.setColor(Color.BLACK);
                else
                    topPixel.setColor(Color.WHITE);
            }
        }
    }
    
    
    /* Main method for testing - each class in Java can have a main 
     * method 
     */
    public static void main(String[] args) 
    {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.zeroBlue();
        beach.explore();
    }

} // this } is the end of class Picture, put all new methods before this


