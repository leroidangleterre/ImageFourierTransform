package imagefouriertransform;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author arthu
 */
public class ImageWindow extends JFrame {

    JPanel mainPanel;
    JPanel buttonsPanel;
    ImagePanel imagePanel;

    int width = 1600, height = 800;

    public ImageWindow(MyImage img) {
        super();

        mainPanel = new JPanel();
        buttonsPanel = new JPanel();
        MyImage transformedImage = img.createEmptyClone();
        imagePanel = new ImagePanel(img, transformedImage);

        this.setSize(new Dimension(width, height));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(imagePanel, BorderLayout.CENTER);

        JButton zoomPlusButton = new JButton("Z+");
        zoomPlusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imagePanel.zoomIn();
            }
        });
        buttonsPanel.add(zoomPlusButton);
        JButton zoomMinusButton = new JButton("Z-");
        zoomMinusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imagePanel.zoomOut();
            }
        });
        buttonsPanel.add(zoomMinusButton);

        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        this.setContentPane(mainPanel);
        setVisible(true);
    }
}
