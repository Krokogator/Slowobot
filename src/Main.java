import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        long startTime,elapsedTime;

        Dictionary dictionary = new Dictionary();
        Tree tree = dictionary.load("C:\\Users\\micha\\Desktop\\Dictionary\\slowa.txt");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Podaj ciąg znaków (bez spacji): ");
        String textinput = br.readLine();

        while(!textinput.equals("exit")) {
            startTime = System.currentTimeMillis();

            //task start
            char[] chars = textinput.toCharArray();
            Character[] input = new Character[16];
            for(int i=0;i<16;i++){
                input[i]=chars[i];
            }
            Grid grid = new Grid(input);
            grid.findPaths(tree);
            //task end

            elapsedTime = System.currentTimeMillis() - startTime;
            if(elapsedTime<1) {
                System.out.println("Search Time: < 1ms" + "\n");
            }
            else
            {
                System.out.println("Search Time: " + elapsedTime + "ms" + "\n");
            }
            System.out.println("Podaj ciąg znaków (bez spacji): ");
            textinput = br.readLine();
        }

    }
}
