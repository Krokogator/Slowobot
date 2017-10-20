package ImageProcessing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

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


        return img;
    }

    public static BufferedImage[] slicer(BufferedImage image){
        BufferedImage[] slices = new BufferedImage[16];
        int x=115;
        int y=936;
        int counter=0;
        for(int i=1;i<5;i++) {
            for(int j=1;j<5;j++){
                slices[counter]=image.getSubimage(x,y,166,224);
                //ImageLoader.saveImg(slices[counter],999);
                x=x+348;
                counter++;
            }
            x=115;
            y=y+423;
        }


        return slices;
    }

    public static BufferedImage slicerSingle(BufferedImage img){
        return img.getSubimage(80,80,166,224);
    }

    public static BufferedImage[] getSamples(String folderPath, int count) {
        BufferedImage[] img = new BufferedImage[count];

        //tries to load an image from given path
        for (int i = 1; i < count+1; i++) {
            try {
                img[i-1] = ImageIO.read(new File(folderPath+i+".png"));
            } catch (IOException e) {
                System.out.println("Cannot load: " + folderPath + i + ".png");
            }
        }

        return img;
    }


    public BufferedImage[] toBlackNWhite(BufferedImage[] images){
        int i=0;
        for(BufferedImage img : images){

            //each row
            for (int y = 0; y < 50; y++) {
                //each column
                for (int x = 0; x < 50; x++) {

                    //return true if pixel belongs to letter (isWhite)
                    if (!isColor(img.getRGB(x, y))) {
                        img.setRGB(x, y, 0);
                    } else {
                        img.setRGB(x, y, 16777215);
                    }
                }
            }
            //saveImg(img, i);
            i++;
        }



        return images;
    }

    private boolean isColor(int rgb){
        Color color = new Color(rgb);
        if(color.getRed() < 10){return true;}
        return false;
    }


    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    public static void saveImg(BufferedImage img, int i) {
        File outputfile = new File("C:\\Users\\micha\\Desktop\\Dictionary\\ImageProcessing\\"+i+".png");
        try {
            ImageIO.write(img, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveImages(BufferedImage[] images, String path, int i){
        File outputfile;
        for(BufferedImage img : images) {
            outputfile = new File(path + i + ".png");
            try {
                ImageIO.write(img, "png", outputfile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            i++;
        }
    }




}
