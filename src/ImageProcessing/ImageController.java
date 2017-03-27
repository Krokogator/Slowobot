package ImageProcessing;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Michał(Krokogator) on 26.03.2017.
 *
 * ImageController takes control of other classes to obtain an image and convert it into string of chars
 */
public class ImageController {
    private ImageLoader imageLoader;
    private ImageAnalyzer imageAnalyzer;

    public ImageController(){
        imageLoader = new ImageLoader();
        imageAnalyzer = new ImageAnalyzer();
    }

    //analyzes next image and return String.length() == 16
    public String getString(){
        String output="null";
        //loads png file in lower res
        BufferedImage screenShot = imageLoader.getImage("C:/Users/micha/Desktop/Dictionary/ImageProcessing/screenshot.png");
        //saves image in lower res
        imageLoader.saveImg(screenShot);


        //analyze image and return String
        imageAnalyzer.analyzeImage(screenShot);

        return output;
    }
}
