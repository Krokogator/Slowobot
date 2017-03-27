package ImageProcessing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Michał(Krokogator) on 26.03.2017.
 *
 * simple image loader class
 */
public class ImageLoader {
    public ImageLoader(){}

    public BufferedImage getImage(String path){
        BufferedImage img = null;

        //tries to load an image from given path
        try {
            img = ImageIO.read(new File(path));
        }
        catch (IOException e) {
        }
        img = resize(img, 270, 480);

        return img;
    }

    public static BufferedImage[] getSamples(String folderPath) {
        BufferedImage[] img = new BufferedImage[32];

        //tries to load an image from given path
        for (int i = 0; i < 32; i++) {
            try {
                img[i] = ImageIO.read(new File(folderPath+i+".png"));
            } catch (IOException e) {
                System.out.println("Cannot load: " + folderPath + i + ".png");
            }
        }
        return img;
    }


    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    public void saveImg(BufferedImage img) {
        File outputfile = new File("C:\\Users\\micha\\Desktop\\Dictionary\\ImageProcessing\\saved.png");
        try {
            ImageIO.write(img, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
