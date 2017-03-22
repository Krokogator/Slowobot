import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Created by micha on 22.03.2017.
 */
public class LoadDict {
    private final String path = "C:\\Users\\micha\\Downloads\\sjp-20170314\\slowa.txt";;

    public LoadDict(){

    }

    public void loadToTree(Tree tree) throws IOException {
        FileReader fr = null;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                tree.addWord(line);
            }
        }
    }
}
