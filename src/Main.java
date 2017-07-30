import Dictionary.DictionaryController;
import ImageProcessing.ImageController;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //Creates dictionary and run 4x4 grid solver
        DictionaryController dictionary = new DictionaryController();
        dictionary.runGridSolver();
    }
}
