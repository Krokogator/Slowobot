import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by micha on 22.03.2017.
 */
public class Box {
    private int id;
    private Character letter;
    private List<Box> neighbours = new ArrayList<>();
    private List<Box> knowledge = new ArrayList<>();

    private List<Integer> paths = new ArrayList<>();

    public Box(int id, Character letter){
        this.id = id;
        this.letter = letter;
    }

    public void addNeighbours(Box neighbour){
        this.neighbours.add(neighbour);
    }

    public Character getLetter(){ return letter; }
    public int getId(){ return id; }
    public List<Box> getNeighbours(){ return neighbours; }

    //return List of paths (List<Integer>) from desired box

}
