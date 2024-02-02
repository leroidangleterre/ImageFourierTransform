package imagefouriertransform;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author arthu
 */
public class MyImage {

    BufferedImage image;

    int[][] array2D;

    /**
     * Create an image from file.
     *
     * @param filename
     */
    public MyImage(String filename) {
        try {
            this.image = ImageIO.read(new File(filename));

            array2D = new int[image.getHeight()][image.getWidth()];

            for (int row = 0; row < image.getHeight(); row++) {
                for (int col = 0; col < image.getWidth(); col++) {
                    int colorInt = image.getRGB(col, row);
                    Color c = new Color(colorInt);
                    int grayLevel = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                    array2D[row][col] = grayLevel;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(MyImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Create empty image with given dimensions
     *
     * @param height
     * @param width
     */
    public MyImage(int height, int width) {
        this.image = null;
        array2D = new int[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                array2D[row][col] = 0;
            }
        }
    }

    int getHeight() {
        return array2D.length;
    }

    int getWidth() {
        return array2D[0].length;
    }

    int get(int row, int col) {
        return array2D[row][col];
    }

    MyImage createEmptyClone() {
        return new MyImage(this.getHeight(), this.getWidth());
    }
}
