/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagefouriertransform;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author arthu
 */
public class ImagePanel extends JPanel {

    private MyImage imageLeft;
    private MyImage imageRight;
    private int margin = 10;

    // Apparent size of a single pixel
    private int zoom;

    public ImagePanel(MyImage iLeft, MyImage iRight) {
        imageLeft = iLeft;
        imageRight = iRight;
        zoom = 8;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);
        int offsetX = imageLeft.getWidth() * zoom + margin;
        for (int row = 0; row < imageLeft.getHeight(); row++) {
            for (int col = 0; col < imageLeft.getWidth(); col++) {
                // Paint pixel for image left
                int pixelValue = imageLeft.get(row, col);
                g.setColor(new Color(pixelValue, pixelValue, pixelValue));
                g.fillRect(col * zoom, row * zoom, zoom, zoom);
                // Paint pixel for image right
                pixelValue = imageRight.get(row, col);
                g.setColor(new Color(pixelValue, pixelValue, pixelValue));
                g.fillRect(col * zoom * offsetX, row * zoom, zoom, zoom);
            }
        }

        // Draw a rectangle around each image
        g.setColor(Color.red);
        g.drawRect(0, 0, imageLeft.getWidth() * zoom, imageLeft.getHeight() * zoom);
        g.drawRect(offsetX, 0, imageRight.getWidth() * zoom, imageRight.getHeight() * zoom);
    }

    public void zoomIn() {
        zoom *= 2;
        repaint();
    }

    public void zoomOut() {
        zoom /= 2;
        repaint();
    }
}
