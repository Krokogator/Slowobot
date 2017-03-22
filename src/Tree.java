

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by micha on 21.03.2017.
 * One instance class that operates on nodes.
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
        Stack<Character> stack = convertToStack(word);      //convert String to letters stack
        for(Node child : children){                         //find first letter, pop and do recursive on the rest
            if(child.getLetter().equals(stack.peek())){
                stack.pop();
                addRecurent(child,stack);
                break;
            }
        }
    }

    private boolean addRecurent(Node node, Stack<Character> letters){
        boolean valid=false;

        if(letters.isEmpty()){  return false;   }

        Character letter = letters.pop();
        if(letters.isEmpty()){  valid=true; }


        if(node.getChildren().isEmpty()){
            addRecurent(node.addChild(letter,valid),letters);
            return true;
        }

        if(node.getChildren().size()==1){
            for(Node child : node.getChildren()){
                if(child==null){
                    addRecurent(node.addChild(letter,valid),letters);
                    return true;
                }
            }
        }

        for(Node child : node.getChildren()) {
            if(child==null){ continue;}
            if (child.getLetter().equals(letter)) {
                if (valid) {
                    return false;
                } else {
                    addRecurent(child, letters);
                    return true;
                }
            }
        }


        //node.addChild(letter,valid);
        addRecurent(node.addChild(letter,valid),letters);
        return true;
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
            printTree(child,".");
        }
    }

    public void printTree(Node node, String appender){
        System.out.println(appender+node.getLetter());
        for(Node child : node.getChildren()){
            if(child==null){continue;}
            printTree(child,"."+appender);
        }
    }
}
