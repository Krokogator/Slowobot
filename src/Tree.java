

import java.util.ArrayList;
import java.util.List;

/**
 * Created by micha on 21.03.2017.
 */
public class Tree {
    private List<Node> children = new ArrayList<>();
    private Character[] letters;
    public Tree(){}

    public void convertToLetters(String word){
        char[] temp = word.toCharArray();
        letters = new Character[temp.length];
        for (int i=0;i<temp.length;i++) {
            letters[i]=new Character(temp[i]);
        }
    }

    public Character[] getLetters(){
        return letters;
    }
}
