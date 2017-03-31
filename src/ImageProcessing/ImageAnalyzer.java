package ImageProcessing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michał(Krokogator) on 26.03.2017.
 *
 * analyzes given square image containing a letter and outputs corresponding char
 */


public class ImageAnalyzer {
    private BufferedImage[] samples = new BufferedImage[32];
    public ImageAnalyzer(){
        //init sample pack containing 32 letters (images)
        initSamples();
    }

    //input is an arrays of sample images of each letter from Polish alphabet
    private void initSamples(){
        BufferedImage[] toResize = ImageLoader.getSamples("Resources\\Samples\\",32);
        for(int i=0;i<32;i++){
            samples[i]=ImageLoader.slicerSingle(toResize[i]);
        }
    }

    //compares two img files
    public float compare(BufferedImage img1, BufferedImage img2){
        int count = 0;
            for (int x = 0; x < img1.getWidth(); x++) {
                for(int y = 0; y < img1.getHeight(); y++){
                    Color color1 = new Color(img1.getRGB(x,y));
                    Color color2 = new Color(img2.getRGB(x,y));
                    int blue1 = color1.getRed();
                    int blue2 = color2.getRed();
                    if (blue1<blue2||blue1>blue2) {
                        count = count + 1;
                    }
                }
            }
        return count;
    }

    private Character getChar(BufferedImage image){
        float lowest=compare(image,samples[0]);
        Integer position=0;
        float percentage;
        for(int i=0;i<32;i++){
            percentage=compare(image,samples[i]);
            if(percentage<lowest){
                lowest=percentage;
                position=i;
            }
        }

        Character letter;

        Map<Integer,Character> map = new HashMap<>();
        map.put(0,'a');
        map.put(1,'b');
        map.put(2,'c');
        map.put(3,'d');
        map.put(4,'e');
        map.put(5,'f');
        map.put(6,'g');
        map.put(7,'h');
        map.put(8,'i');
        map.put(9,'j');
        map.put(10,'k');
        map.put(11,'l');
        map.put(12,'m');
        map.put(13,'n');
        map.put(14,'o');
        map.put(15,'p');
        map.put(16,'r');
        map.put(17,'s');
        map.put(18,'t');
        map.put(19,'u');
        map.put(20,'w');
        map.put(21,'y');
        map.put(22,'z');
        map.put(23,'ź');
        map.put(24,'ś');
        map.put(25,'ó');
        map.put(26,'ń');
        map.put(27,'ż');
        map.put(28,'ć');
        map.put(29,'ą');
        map.put(30,'ę');
        map.put(31,'ł');

        letter=map.get(position);
        return letter;
    }


    public String analyzeImage(BufferedImage screenShot) {
        BufferedImage[] images = ImageLoader.slicer(screenShot);
        ImageLoader.saveImages(images,"Resources\\Temp\\",1);
        images=ImageLoader.getSamples("Resources\\Temp\\",16);
        String output = "";

        for(BufferedImage image : images){
            output=output+getChar(image);
        }



        return output;
    }
}
