import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        Dictionary dictionary = new Dictionary();
        Character[][] input = {{'a','b','e','f'},{'a','b','c','d'},{'a','b','c','d'},{'a','b','c','d'}};

        Grid grid = new Grid(input);
        long startTime = System.currentTimeMillis();
        dictionary.load("C:\\Users\\micha\\Desktop\\Dictionary\\slowa.txt");
        long elapsedTime = System.currentTimeMillis() - startTime;
        long elapsedSeconds = elapsedTime / 1000;
        System.out.println("Load Time: "+ elapsedTime + "ms" +"\n");


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String textinput="";
        System.out.println("Podaj wyraz: ");
        textinput = br.readLine();
        while(!textinput.equals("exit")) {
            startTime = System.currentTimeMillis();
            dictionary.testPacket(textinput);
            elapsedTime = System.currentTimeMillis() - startTime;
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
