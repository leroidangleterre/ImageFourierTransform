package imagefouriertransform;

/**
 *
 * @author arthu
 */
public class ImageFourierTransform {

    public static void main(String[] args) {

        String folder = "C:\\Users\\arthu\\Documents\\Programmation\\Java\\ImageFourierTransform\\src\\imagefouriertransform\\";
        String filenameA = "a_small.bmp";
        String filenameB = "b_small.bmp";
        String completeFilenameA = folder + filenameA;
        MyImage imageA = new MyImage(completeFilenameA);
        String completeFilenameB = folder + filenameB;
        MyImage imageB = new MyImage(completeFilenameB);

        ImageWindow viewer = new ImageWindow(imageA, imageB);

    }

}
