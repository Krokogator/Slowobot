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
