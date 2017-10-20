package Dictionary;

import ImageProcessing.ImageController;
import TimeMeasure.Stopper;
import TimeMeasure.Timer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Michał(Krokogator) on 21.03.2017.
 *
 * Main dictionary class
 * Operates on trees
 * Access to simple functions/methods like .exist, .add, etc.
 */
public class DictionaryController {
    private Tree tree;

    public DictionaryController(){
        tree = new Tree();
    }

    public Tree load(String path){
        DictionaryLoader loader = new DictionaryLoader();
        try {
            loader.loadToTree(tree, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //tree.printAll();
        return tree;
    }

    public void runGridSolver() throws IOException {
        long startTime, elapsedTime;
        Tree tree = load("Resources\\slownik2.txt");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Press enter to start");
        String textinput = br.readLine();

        Timer timer = new Timer(130);
        Stopper stopper = new Stopper();

        while(true) {
            System.out.println("Round start!");
            timer.reset();
            stopper.start();

            String mainCommand=("cmd /B start cmd.exe /K \"adb shell screencap -p /sdcard/screencap.png && adb pull /sdcard/screencap.png & exit \"");
            try {
                Process p = Runtime.getRuntime().exec(mainCommand);

                p.waitFor();
                p.destroy();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Image loaded in: "+ stopper.getMilis()+" ms");

            textinput=checkImage("screencap");

            System.out.println("Text input: "+textinput);

            if(textinput.length()==16) {
                checkText(textinput);
            }

            isEndOfRound(timer);
        }
    }

    private boolean isEndOfRound(Timer timer){
        while(true){
            if(timer.isElapsed()){
                return true;
            }
        }
    }

    private String checkImage(String fileName) {
        ImageController imageController = new ImageController(fileName);
        return imageController.getString();

    }

    private void checkText(String textinput) throws IOException {
        if(textinput.length()==16) {

            char[] chars = textinput.toCharArray();
            Character[] input = new Character[16];
            for (int i = 0; i < 16; i++) {
                input[i] = chars[i];
            }
            Grid grid = new Grid(input);
            grid.findPaths(tree);

        }
        else{
            System.out.println("Niewłaściwy ciąg znaków");
        }
    }

    public Tree getTree(){ return tree; }

    public void testPacket(String testword){
        System.out.print("Exists: ");
        if(tree.checkPath(testword)){
            System.out.println("true");
        }
        else{
            System.out.println("false");
        }
    }

    public void testValidPacket(String testword){
        System.out.print("Valid: ");
        if(tree.checkWord(testword)){
            System.out.println("true");
        }
        else{
            System.out.println("false");
        }
    }


}
