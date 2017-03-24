import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws IOException {
        Dictionary dictionary = new Dictionary();
        Character[] input = {'w','t','h','a','c','a','k','f','h','r','n','t','m','o','s','i'};

        Tree tree = dictionary.load("C:\\Users\\micha\\Desktop\\Dictionary\\slowa.txt");

        Grid grid = new Grid(input);
        grid.writeBoxes();
        //System.out.println(grid.test());
        grid.findPaths(tree);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String textinput="";
        System.out.print("Podaj wyraz: ");
        textinput = br.readLine();
        while(!textinput.equals("exit")) {
            long startTime = System.nanoTime();
            dictionary.testPacket(textinput);
            dictionary.testValidPacket(textinput);
            long elapsedTime = System.nanoTime() - startTime;
            if(elapsedTime<1) {
                System.out.println("Search Time: < 1ms" + "\n");
            }
            else
            {
                System.out.println("Search Time: " + elapsedTime + "ns" + "\n");
            }
            textinput = br.readLine();
        }



    }
}
