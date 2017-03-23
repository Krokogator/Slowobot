import java.util.Stack;

/**
 * Created by micha on 22.03.2017.
 */
public class Box {
    private int id;
    private Stack<Integer> path;
    private Stack<Box> neighbour;
    private boolean seen;

    public Box(int id){
        this.id = id;
    }

    public Stack<Integer> getPath(Stack<Integer> path, Stack<Box> neighbour){
        path.push(id);
        this.neighbour=neighbour;
        //getWhite(path);



        return path;
    }

   /* private Stack<Box> getWhite(Stack<Integer> Path){
        Stack<Box> boxes;
        return boxes;
    }*/

    public void check(){    seen = true;    }
    public void uncheck(){  seen = false;   }

}
