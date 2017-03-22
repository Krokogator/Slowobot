

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by micha on 21.03.2017.
 */
public class Tree {
    private List<Node> children = new ArrayList<>();
    private final Character[] polishRoots = new Character[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','r','s','t','u','w','y','z','ć','ł','ó','ś','ż','ź'};

    public Tree(){
        for (Character root : polishRoots){
            Node node = new Node(root);
            children.add(node);        }
    }

    public void addWord(String word){
        Stack<Character> stack = convertToStack(word);
        for(Node child : children){
            //System.out.println("stack= " +stack.peek());
            //System.out.println("parent= " +parent.getLetter());
            if(child.getLetter().equals(stack.peek())){
                stack.pop();
                child.reccurentAdd(child,stack);
                break;
            }
        }
    }

    private Stack<Character> convertToStack(String word){
        Stack<Character> stack = new Stack<>();
        Character[] letters = convertToLetters(word);

        for(int i=letters.length-1;i>=0;i--){
            stack.add(letters[i]);
        }

        return stack;
    }

    private Character[] convertToLetters(String word){
        char[] temp = word.toCharArray();
        Character[] letters = new Character[temp.length];
        for (int i=0;i<temp.length;i++) {
            letters[i]=new Character(temp[i]);
        }
        return letters;
    }
    public void printAll(){
        for(Node child : children){
            printTree(child," ");
        }
    }

    public void printTree(Node parent, String appender){
        System.out.println(appender+parent.getLetter());
        for(Node child : parent.getChildren()){
            printTree(child," "+appender);
        }
    }
}
