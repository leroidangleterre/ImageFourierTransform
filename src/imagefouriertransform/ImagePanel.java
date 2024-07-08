package imagefouriertransform;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author arthu
 */
public class ImagePanel extends JPanel {

    private MyImage image;
    private int margin = 10;

    private int minOutOfBoundsValue = Integer.MAX_VALUE;
    private int maxOutOfBoundsValue = Integer.MIN_VALUE;

    // Apparent size of a single pixel
    private int zoom;

    // When this panel represents a Furier image, the values must be remapped
    boolean mustChangeScale;

    private boolean displayValues;

    public ImagePanel(MyImage imageParam) {
        image = imageParam;
        zoom = 18;
        displayValues = false;
        mustChangeScale = false;
    }

    public ImagePanel(MyImage imageParam, boolean mustChangeScaleParam) {
        this(imageParam);
        this.mustChangeScale = mustChangeScaleParam;
    }

    public MyImage getImage() {
        return image;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);

        for (int row = 0; row < image.getHeight(); row++) {
            for (int col = 0; col < image.getWidth(); col++) {

                // /////////////////////////////////////////////////////////////
                // Paint pixel for image
                // /////////////////////////////////////////////////////////////
                int imageValue = image.getRealPart(row, col);
                // [0, 255]

                if (mustChangeScale) {
                    // Increase the display value of near-zero pixels by taking a square root twice.
                    imageValue = (int) (Math.sqrt((double) imageValue / 255) * 255);
                    imageValue = (int) (Math.sqrt((double) imageValue / 255) * 255);
                }

                g.setColor(new Color(imageValue, imageValue, imageValue));
                g.fillRect(col * zoom, row * zoom, zoom, zoom);

                if (displayValues) {
                    g.setColor(Color.CYAN.darker());
                    g.drawString("" + imageValue, (int) ((col + 0.5) * zoom), (int) ((row + 0.5) * zoom));
                }
            }
        }

        // Draw a rectangle around the image
        g.setColor(Color.red);
        g.drawRect(0, 0, image.getWidth() * zoom, image.getHeight() * zoom);
    }

    public void zoomIn() {
        zoom *= 2;
        computePreferredSize();
        repaint();
    }

    public void zoomOut() {
        zoom /= 2;
        computePreferredSize();
        repaint();
    }

    protected void computePreferredSize() {
        int prefWidth = zoom * image.getWidth();
        int prefHeight = zoom * image.getHeight();
        setPreferredSize(new Dimension(prefWidth, prefHeight));
        setSize(new Dimension(prefWidth, prefHeight));
    }

    protected void toggleDisplayValues() {
        displayValues = !displayValues;
    }

}
