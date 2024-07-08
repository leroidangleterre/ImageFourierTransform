package imagefouriertransform;

/**
 *
 * @author arthu
 */
public class ImageFourierTransform {

    public static void main(String[] args) {

//        String folder = "C:\\Users\\arthu\\Documents\\Programmation\\Java\\ImageFourierTransform\\src\\imagefouriertransform\\";
////        String filename = "a.bmp";
////        String filename = "a_small.bmp";
//        String filename = "test.bmp";
//        String completeFilename = folder + filename;
//        MyImage myImage = new MyImage(completeFilename);
        boolean makeSymmetrical = false;
        MyImage imageA = new MyImage(20, 20, makeSymmetrical);
        MyImage imageB = new MyImage(20, 20, makeSymmetrical);
        ImageWindow viewer = new ImageWindow(imageA, imageB);

    }

}
