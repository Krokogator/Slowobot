package ImageProcessing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

/**
 * Created by Michał(Krokogator) on 26.03.2017.
 *
 * ImageController takes control of other classes to obtain an image and convert it into string of chars
 */
public class ImageController {
    private ImageLoader imageLoader;
    private ImageAnalyzer imageAnalyzer;
    private final String slicesPath = "C:\\Users\\micha\\Desktop\\Dictionary\\ImageProcessing\\BigSlices\\";
    private String fileName;

    public ImageController(String fileName){
        imageLoader = new ImageLoader();
        imageAnalyzer = new ImageAnalyzer();
        this.fileName = fileName;

    }

    //analyzes next image and return String.length() == 16
    public String getString(){
        String output="null";
        //loads png file
        BufferedImage screenShot = imageLoader.getImage(fileName+".png");

        //analyzes screenShot and returns String
        output=imageAnalyzer.analyzeImage(screenShot);

        //saves array of images in that path, naming from given int
        //imageLoader.saveImages(slices,slicesPath,1);


        //saves image in lower res
        //imageLoader.saveImg(screenShot);


        //analyze image and return String
        //imageAnalyzer.analyzeImage(screenShot);

        return output;
    }
}
