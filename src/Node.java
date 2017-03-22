import java.util.ArrayList;
import java.util.List;

/**
 * Created by micha on 21.03.2017.
 * Single Node represents single letter
 * If Node has null child it means it creates a word (letter is the end of the word)
 * Still Node can have other children than 'null' for example in word "kite", we will find that letter 't' will have two children
 * one of those will be 'null' (because "kit" is a word) and the other one will be 'e', which will contain only one child 'null' as there is no word starting with "kite" (maybe there are but that's just an example)
 */

public class Node {
    private Character letter = null;
    private List<Node> children = new ArrayList<>();
    private final Node parent;

    public Node(Node parent){
        this.parent=parent;
    }
    public Node(Node parent, Character letter, boolean isWord){
        this(parent);
        this.letter = letter;
        if(isWord){ addNullChild(); }
    }

    public Character getLetter(){
        return letter;
    }

    private void addNullChild(){
        children.add(null);
    }

    public void addChild(Node child){
        children.add(child);
    }

    public Node getParent(){ return parent; }

    public List<Node> getChildren(){  return children;  }
}
