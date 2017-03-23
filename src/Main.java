import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        Dictionary dictionary = new Dictionary();
        Character[][] input = {{'a','b','e','f'},{'a','b','c','d'},{'a','b','c','d'},{'a','b','c','d'}};

        Grid grid = new Grid(input);
        Tree tree = dictionary.load("C:\\Users\\micha\\Desktop\\Dictionary\\test.txt");


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String textinput="";
        System.out.print("Podaj wyraz: ");
        textinput = br.readLine();
        while(!textinput.equals("exit")) {
            long startTime = System.currentTimeMillis();
            dictionary.testPacket(textinput);
            long elapsedTime = System.currentTimeMillis() - startTime;
            if(elapsedTime<1) {
                System.out.println("Search Time: < 1ms" + "\n");
            }
            else
            {
                System.out.println("Search Time: " + elapsedTime + "ms" + "\n");
            }
            textinput = br.readLine();
        }



    }
}
