import java.io.IOException;

/**
 * Created by micha on 21.03.2017.
 * Main dictionary class
 * Operates on trees
 * Access to simple functions/methods like .exist, .add, etc.
 */
public class Dictionary {
    private Tree tree;

    public Dictionary(){
        tree = new Tree();
    }

    public void load(String path){
        LoadDict loader = new LoadDict();
        try {
            loader.loadToTree(tree, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Dictionary Load Successful!");

        //tree.printAll();
    }

    public void testPacket(String testword){
        if(tree.checkWord(testword)){
            System.out.println("found= "+"\""+testword+"\"");
        }
        else{
            System.out.println("not found= "+"\""+testword+"\"");
        }
    }
}
