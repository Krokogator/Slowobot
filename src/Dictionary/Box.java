package Dictionary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michał(Krokogator) on 22.03.2017.
 */

public class Box {
    private int id;
    private Character letter;
    private List<Box> neighbours = new ArrayList<>();

    public Box(int id, Character letter){
        this.id = id;
        this.letter = letter;
    }

    public void addNeighbours(Box neighbour){
        this.neighbours.add(neighbour);
    }

    public int getId(){ return id; }
    public Character getLetter(){ return letter; }
    public List<Box> getNeighbours(){ return neighbours; }

}
