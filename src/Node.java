import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

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

    public Node(Character letter){
        this.letter=letter;
        this.parent=null;
    }

    public Node(Node parent, Character letter, boolean isWord){
        this.parent=parent;
        this.letter = letter;
        if(isWord){ addNullChild(); }
    }

    public Character getLetter(){
        return letter;
    }

    private void addNullChild(){
        children.add(null);
    }

    public Node addChild(Character letter, boolean isWord){
        Node child = new Node(this,letter,isWord);
        children.add(child);
        System.out.println("ADDCHILD current letter= "+this.getLetter());
        System.out.println("ADDCHILD child letter=   "+child.getLetter());
        return child;
    }

    public void reccurentAdd(Node node, Stack<Character> letters){
        System.out.println("current node= "+node.getLetter());
        System.out.println("letter to add= "+letters.peek());

        if(letters.isEmpty()){
            addNullChild();System.out.println("wołacz1");
        }

        if(node.getChildren().isEmpty()){
            System.out.println(letters.peek());
            Node child = node.addChild(letters.peek(), false);
            System.out.println("Child char:"+child.getLetter());
            System.out.println("Child children:"+child.getChildren());
            letters.pop();
            if(letters.isEmpty()==false){
                reccurentAdd(child, letters);}
        }

        else {

            for (Node child : node.getChildren()) {
                System.out.println("wołacz2");
                if (child.getLetter().equals(letters.peek())) {
                    System.out.println("wołacz3");
                    letters.pop();
                    reccurentAdd(child, letters);
                } else {
                    System.out.println("wołacz4");
                    //child.addChild(letters.pop(), false);
                    child = node.addChild(letters.pop(), false);
                    reccurentAdd(child, letters);
                }
                break;
            }
        }
    }

    public Node getParent(){ return parent; }

    public List<Node> getChildren(){  return children;  }
}
