package imagefouriertransform;

/**
 *
 * @author arthu
 */
public class ImageFourierTransform {

    public static void main(String[] args) {

        String folder = "C:\\Users\\arthu\\Documents\\Programmation\\Java\\ImageFourierTransform\\src\\imagefouriertransform\\";
//        String filename = "a.bmp";
        String filename = "a_small.bmp";
        String completeFilename = folder + filename;
        MyImage myImage = new MyImage(completeFilename);
//        boolean makeSymmetrical = false;
//        MyImage myImage = new MyImage(5, 5, makeSymmetrical);
        ImageWindow viewer = new ImageWindow(myImage);

    }

}
