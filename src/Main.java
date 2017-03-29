import Dictionary.DictionaryController;
import ImageProcessing.ImageController;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //Test test = new Test();
        //test.run();
        //Model_Asocjacji aso = new Model_Asocjacji();
        //aso.ad1();
        //aso.ad2();



//        ImageController imageController = new ImageController();
//        System.out.println(imageController.getString());





        //Creates dictionary and run 4x4 grid solver
        DictionaryController dictionary = new DictionaryController();
        dictionary.runGridSolver();
    }
}
