package Dictionary;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Michał(Krokogator) on 21.03.2017.
 *
 * Single DictionaryController.Node represents single letter
 * If DictionaryController.Node has null child it means it creates a word (letter is the end of the word)
 * Still DictionaryController.Node can have other children than 'null' for example in word "kite", we will find that letter 't' will have two children
 * one of those will be 'null' (because "kit" is a word) and the other one will be 'e', which will contain only one child 'null' as there is no word starting with "kite" (maybe there are but that's just an example)
 */

public class Node {
    private char letter;
    private List<Node> children = new ArrayList<>();

    public Node(char letter){
        this.letter=letter;
    }

    public Node(char letter, boolean isWord){
        this.letter = letter;
        if(isWord){ this.addNullChild(); }
    }

    public Character getLetter(){
        return Character.valueOf(letter);
    }

    public void addNullChild(){
        children.add(null);
    }

    public boolean isValid(){
        for(Node child : getChildren()){
            if(child==null){
                return true;
            }
        }
        return false;
    }

    public Node addChild(char letter, boolean isWord){
        Node child = new Node(letter,isWord);
        children.add(child);
        return child;
    }

    public List<Node> getChildren(){  return children;  }
}
