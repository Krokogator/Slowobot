package ImageProcessing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Optional;

/**
 * Created by Michał(Krokogator) on 26.03.2017.
 *
 * analyzes given square image containing a letter and outputs corresponding char
 */


public class ImageAnalyzer {
    private double[][] z = new double[32][2500];
    private double[][] w = new double[2500][2500];
    private double n=900;
    public ImageAnalyzer(){
        //init z -> sample images of letters
        initSamples(ImageLoader.getSamples("C:/Users/micha/Desktop/Dictionary/ImageProcessing/Samples/"));
        //init w -> knowledge
        initW();
    }


    private void initW(){
        for(int i=0;i<2500;i++){
            for(int j=0;j<2500;j++){
                for(double[] letter : z){
                    w[i][j]=w[i][j]+((1.0/n) * letter[i] * letter[j]);
                }
            }
        }
    }


    private double[][] gridImageToRGB(BufferedImage img){
        double[][] zPrim = new double[16][2500];
        //each row
        int i=0,j;
        for(int yStart = 170; yStart < 430; yStart = yStart + 79) {
            //each column
            for (int xStart = 12; xStart < 220; xStart = xStart + 65) {
                j=0;
                //each letter pixel row
                for(int yLetter=yStart; yLetter<yStart+50; yLetter++){
                    //each letter pixel column
                    for(int xLetter=xStart; xLetter<xStart+50; xLetter++){
                        //return true if pixel belongs to letter (isWhite)
                        if(whiteOrCyan(img.getRGB(xLetter,yLetter))){
                            zPrim[i][j]=-1.0;
                        }
                        else{
                            zPrim[i][j]=1.0;
                        }
                        j++;

                    }
                }
                i++;
            }
            i++;
        }

        for(double[] test:zPrim){
            display(test);
        }

        return zPrim;
    }

    private boolean whiteOrCyan(int RGBmodel){
        Color color = new Color(RGBmodel);
        if(color.getRed()<20) return true;

        return false;
    }

    //input is an arrays of sample images of each letter from Polish alphabet
    private void initSamples(BufferedImage[] images){
        int i=0,j;

        //each img
        for(BufferedImage letterImg : images) {
            j=0;
            //each letter pixel row
            for (int yLetter = 0; yLetter < 50; yLetter++) {
                //each letter pixel column
                for (int xLetter = 0; xLetter < 50; xLetter++) {

                    //return true if pixel belongs to letter (isWhite)
                    if (whiteOrCyan(letterImg.getRGB(xLetter, yLetter))) {
                        z[i][j] = -1.0;
                    } else {
                        z[i][j] = 1.0;
                    }
                    j++;

                }
            }
            i++;
        }
    }


    public String analyzeImage(BufferedImage screenShot) {
        String output;
        double[][] converted;
        output = "null";
        //converted = gridImageToRGB(screenShot);



        for(double[] sample : z){
            display(SGN(F(sample)));
            System.out.print("\n\n");
        }





        return output;
    }


    private double[] SGN(double x[]) {
        double[] result = new double[2500];
        for (int i = 0; i < 2500; i++) {
            if (x[i] >= 0) {
                result[i] = 1;
            } else {
                result[i] = -1;
            }
        }
        return result;
    }

    private double[] F(double[] u){
        double[] temp = new double[2500];
        for(int i=0;i<2500;i++){
            for(int j=0;j<2500;j++){
                temp[i]=temp[i]+u[j]*w[i][j];
            }
        }
        return temp;
    }

    //temporary solution for testing
    public void display(double[] interfejs){
        for(int i=0;i<2500;i++){
            if(i%50==0){
                System.out.print("\n");
            }
            if(interfejs[i]==1.0) {
                System.out.print("*");
            }
            else{
                System.out.print(" ");
            }
        }
    }


}
