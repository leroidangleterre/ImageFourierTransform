package imagefouriertransform;

import java.awt.Color;
import java.awt.Graphics;
import static java.lang.Math.PI;
import javax.swing.JPanel;

/**
 *
 * @author arthu
 */
public class ImagePanel extends JPanel {

    private MyImage imageLeft;
    private MyImage imageCenter;
    private MyImage imageRight;
    private int margin = 10;

    private int minOutOfBoundsValue = Integer.MAX_VALUE;
    private int maxOutOfBoundsValue = Integer.MIN_VALUE;

    // Apparent size of a single pixel
    private int zoom;

    private boolean displayValues;

    public ImagePanel(MyImage iLeft) {
        imageLeft = iLeft;
        imageCenter = imageLeft.createEmptyClone();
        imageRight = imageLeft.createEmptyClone();
        zoom = 10;
        displayValues = false;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);
        int offsetX = imageLeft.getWidth() * zoom + margin;

        for (int row = 0; row < imageLeft.getHeight(); row++) {
            for (int col = 0; col < imageLeft.getWidth(); col++) {

                // /////////////////////////////////////////////////////////////
                // Paint pixel for image left
                // /////////////////////////////////////////////////////////////
                int pixelValue = imageLeft.getRealPart(row, col);
                g.setColor(new Color(pixelValue, pixelValue, pixelValue));
                g.fillRect(col * zoom, row * zoom, zoom, zoom);

                if (displayValues) {
                    g.setColor(Color.CYAN.darker());
                    g.drawString("" + pixelValue, (int) ((col + 0.5) * zoom), (int) ((row + 0.5) * zoom));
                }

                // /////////////////////////////////////////////////////////////
                // Paint pixel for image center
                // /////////////////////////////////////////////////////////////
                int imageValue = imageCenter.getRealPart(row, col);
                // [0, 255]
                int transformedPixel = 0;
                // Increase the display value of near-zero pixels by taking a square root twice.
                transformedPixel = (int) (Math.sqrt((double) imageValue / 255) * 255);
                transformedPixel = (int) (Math.sqrt((double) transformedPixel / 255) * 255);
                g.setColor(new Color(transformedPixel, transformedPixel, transformedPixel));
                g.fillRect(col * zoom + offsetX, row * zoom, zoom, zoom);

                if (displayValues) {
                    g.setColor(Color.CYAN.darker());
                    g.drawString("" + transformedPixel, (int) ((col + 0.5) * zoom + offsetX), (int) ((row + 0.5) * zoom));
                }

                // /////////////////////////////////////////////////////////////
                // Paint pixel for image right
                // /////////////////////////////////////////////////////////////
                pixelValue = imageRight.getRealPart(row, col);
                if (pixelValue > 255 || pixelValue < 0) {
                    if (pixelValue > maxOutOfBoundsValue) {
                        maxOutOfBoundsValue = pixelValue;
                    }
                    if (pixelValue < minOutOfBoundsValue) {
                        minOutOfBoundsValue = pixelValue;
                    }
                    // Color not ok
                    g.setColor(new Color(255, 0, 0));
                } else {
                    // Normal case, color OK
                    g.setColor(new Color(pixelValue, pixelValue, pixelValue));
                }
                g.fillRect(col * zoom + 2 * offsetX, row * zoom, zoom, zoom);

                if (displayValues) {
                    g.setColor(Color.CYAN.darker());
                    g.drawString("" + pixelValue, (int) ((col + 0.5) * zoom + 2 * offsetX), (int) ((row + 0.5) * zoom));
                }
            }
        }

        // Draw a rectangle around each image
        g.setColor(Color.red);
        g.drawRect(0, 0, imageLeft.getWidth() * zoom, imageLeft.getHeight() * zoom);
        g.drawRect(offsetX, 0, imageCenter.getWidth() * zoom, imageCenter.getHeight() * zoom);
        g.drawRect(2 * offsetX, 0, imageCenter.getWidth() * zoom, imageCenter.getHeight() * zoom);
    }

    public void zoomIn() {
        zoom *= 2;
        repaint();
    }

    public void zoomOut() {
        zoom /= 2;
        repaint();
    }

    private void computeDirectTransform(MyImage imageSource, MyImage imageDest) {
        System.out.println("Computing transform...");
        double M = imageSource.getHeight();
        double N = imageSource.getWidth();

        for (int v = 0; v < M; v++) {
            for (int u = 0; u < N; u++) {

                // Compute the value of pixel (u,v) of the tranform
                Complex Fuv = new Complex();
                for (int y = 0; y < M; y++) {
                    for (int x = 0; x < N; x++) {
                        double argument = -2 * PI * ((double) (u * x) / N + (double) (v * y) / M);
                        Complex exponentPart = new Complex(argument);
//                        Fuv.increment(exponentPart.multiply(imageSource.get(y, x)));
                        Fuv.increment(exponentPart.multiply(imageSource.get(y, x)));
                    }
                }
                Fuv = Fuv.divide(M * N);
                imageDest.set(v, u, Fuv);
            }
        }
        System.out.println("Computing transform done");
    }

    public void computeDirectTransform() {
        computeDirectTransform(imageLeft, imageCenter);
    }

    void computeReverseTransform(MyImage imageSource, MyImage imageDest) {
        System.out.println("Computing reverse transform...");
        double M = imageSource.getHeight();
        double N = imageSource.getWidth();

        for (int x = 0; x < N; x++) {
            System.out.println(((double) x / N) + " done.");
            for (int y = 0; y < M; y++) {

                // Compute the value of pixel (x,y) of the decoded image
                Complex fxy = new Complex();
                for (int u = 0; u < N; u++) {
                    for (int v = 0; v < M; v++) {
                        double argument = 2 * PI * ((double) (u * x) / N + (double) (v * y) / M);
                        Complex exponentPart = new Complex(argument);
                        fxy.increment(exponentPart.multiply(imageSource.get(v, u)));
                    }
                }

                // Set the pixel on the decoded image.
                imageDest.set(y, x, fxy);
            }
        }
        System.out.println("Computing reverse transform done");
    }

    public void computeReverseTransform() {
        computeReverseTransform(imageCenter, imageRight);
    }

    private void writeImageValues(MyImage image) {
        int M = image.getHeight();
        int N = image.getWidth();

        for (int x = 0; x < M; x++) {
            String line = "";
            for (int y = 0; y < N; y++) {
                line += image.get(y, x) + " ";
            }
            System.out.println(line);
        }
    }

    protected void toggleDisplayValues() {
        displayValues = !displayValues;
    }

}
