import java.io.IOException;

/**
 * Created by micha on 21.03.2017.
 * Operates on trees
 */
public class Dictionary {
    private Tree tree;

    public Dictionary(){
        tree = new Tree();
    }

    public void loadDictionary(){
        LoadDict loader = new LoadDict();
        try {
            loader.loadToTree(tree);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //tree.printAll();
    }






}
