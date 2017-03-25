package Dictionary;

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
public class Dict {
    private Tree tree;

    public Dict(){
        tree = new Tree();
    }

    public Tree load(String path){
        LoadDict loader = new LoadDict();
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
        Tree tree = load("C:\\Users\\micha\\Desktop\\Dictionary\\slowa.txt");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Podaj ciąg znaków (bez spacji): ");
        String textinput = br.readLine();

        while(!textinput.equals("exit")) {
            if(textinput.length()==16) {
                startTime = System.currentTimeMillis();

                //task start
                char[] chars = textinput.toCharArray();
                Character[] input = new Character[16];
                for (int i = 0; i < 16; i++) {
                    input[i] = chars[i];
                }
                Grid grid = new Grid(input);
                grid.findPaths(tree);
                //task end

                elapsedTime = System.currentTimeMillis() - startTime;
                if (elapsedTime < 1) {
                    System.out.println("Search Time: < 1ms" + "\n");
                } else {
                    System.out.println("Search Time: " + elapsedTime + "ms" + "\n");
                }
                System.out.print("Podaj ciąg znaków (bez spacji): ");
                textinput = br.readLine();
            }
            else{
                System.out.println("Niewłaściwy ciąg znaków");
                System.out.print("Podaj ciąg znaków (bez spacji): ");
                textinput = br.readLine();
            }
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
