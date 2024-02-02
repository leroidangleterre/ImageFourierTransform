package imagefouriertransform;

/**
 *
 * @author arthu
 */
public class ImageFourierTransform {

    public static void main(String[] args) {

        String filename = "C:\\Users\\arthu\\Documents\\Programmation\\Java\\ImageFourierTransform\\src\\imagefouriertransform\\a_small.bmp";

        MyImage myImage = new MyImage(filename);
        ImageWindow viewer = new ImageWindow(myImage);

    }

}
