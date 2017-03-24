import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by micha on 22.03.2017.
 * Represents 4x4 grid from game and finds paths between letters
 */

public class Grid {
    private Character[] grid = new Character[16];
    private List<Box> boxes= new ArrayList<>();
    private List<List<Pair>> paths = new ArrayList<>();

    public Grid(Character[] grid){
        this.grid=grid;
        List<Integer> noDeadBoxes = new ArrayList<>();
        this.boxes = initBoxes(noDeadBoxes);
    }

    /**
     * Musimy zwracać:
     *
     * 1.Zwracanie pojedynczej ścieżki prawdop od razu w postaci listy jednoelementowej (w momencie gdy nie możemy nigdzie przejść, bo
     *      a) nie ma sąsiadów %% exists -> return one element array of paths
     *      b) nasz wyraz + litera sąsiada != exists
     * 2.W każdym przejściu dany obiekt musi zbudować liste ścieżek (POPRAWNYCH!), która będzie zawierać
     *      a) aktualną ścieżkę w której jesteśmy ( CHYBA ŻE i tak nie możemy udać się w żadne miejsce wobec tego pkt 1. powinien mieć pierwszeństwo i być wywoływany zawsze gdy zachodzi)
     *      b) nadścieżki, czyli ścieżka aktualna + wszystkie ścieżki dłuższe od naszej
     *
     *      rozważenie par (Character,Id) wówczas każdy zbudowany z takiej pary wyraz jednoznacznie określałby ścieżkę która go buduje
     */


    public void findPaths(Tree tree){
        for(int i=0;i<16;i++) {
            List<Pair> currentPath = new ArrayList<>();
            Box box = boxes.get(i);
            List<Integer> indexDead = new ArrayList<>();
            indexDead.add(i+1);
            List<Box> newboxes = initBoxes(indexDead);
            customDFS(box, tree, newboxes, paths, currentPath);
        }
    }

    public String pairToWord(List<Pair> path){
        String result="";
        for(Pair pair : path){
            result=result+pair.getLetter();
        }
        return result;
    }

    private List<Integer>getDead(List<Box> Alive){
        List<Integer> alive = new ArrayList<>();
        List<Integer> dead = new ArrayList<>();
        for(Box box : Alive){
            alive.add(box.getId());
        }
        for(int i=1;i<17;i++){
            if(!alive.contains(i)){dead.add(i);
                //System.out.println(i);
                }

        }

        return dead;
    }

    private void customDFS(Box box, Tree tree, List<Box> aliveBoxes, List<List<Pair>> validPaths, List<Pair> possiblePath){

        List<Integer> deadBoxes = getDead(aliveBoxes);
        deadBoxes.add(box.getId());
        List<Box> nextAliveBoxes = initBoxes(deadBoxes);

        List<Pair> currentPath = new ArrayList<>();
        currentPath.addAll(possiblePath);
        currentPath.add(new Pair(box.getId(),box.getLetter()));
        if(currentPath.size()>2&&tree.checkWord(pairToWord(currentPath))){
            validPaths.add(currentPath);
            System.out.println("Valid words= "+pairToWord(currentPath));
        }


        if(nextAliveBoxes.isEmpty()){//return paths;
            }


        for(Box alive : nextAliveBoxes){
            if(contains(box.getNeighbours(),alive.getId())){

                List<Pair> tempPath = new ArrayList<>();
                tempPath.addAll(currentPath);
                tempPath.add(new Pair(alive.getId(),alive.getLetter()));

                if(tree.checkPath(pairToWord(tempPath))){
                    customDFS(alive, tree, nextAliveBoxes, paths, currentPath);
                }
            }
        }
        //return null;
    }

    private boolean contains(List<Box> boxes, int id){
        for(Box box : boxes){
            if(box.getId()==id){
                return true;
            }
        }
        return false;
    }

    private Box getBox(List<Box> boxes, int id){
        for(Box box:boxes){
            if(box.getId()==id){
                return box;
            }
        }
        return null;
    }

    //just for efficiency and code cleanup
    private void addNeighbours(int index, int[] neighbours, List<Box> boxes){
        index=index - 1;
        for(int neighbour : neighbours) {
            neighbour=neighbour-1;
            boxes.get(index).addNeighbours(boxes.get(neighbour));
        }
    }

    private List<Box> removeBox(List<Box> boxes, int kill){
            boxes.remove(kill-1);
        return boxes;
    }

    private List<Box> removeBoxes(List<Box> boxes, List<Integer> toKill){
        for(int kill : toKill){
            if(contains(boxes,kill)){
                boxes.remove(getBox(boxes,kill));
            }
        }
        return boxes;
    }


    private List<Box> initBoxes(List<Integer> deadBoxes){
        List<Box> boxes = new ArrayList<>();

        for(int x=0;x<16;x++){
                boxes.add(new Box(x + 1, grid[x]));
        }


        /**
         *   1  2  3  4
         *   5  6  7  8
         *   9  10 11 12
         *   13 14 15 16
         */

        //first row
        addNeighbours(1, new int[]{2,5,6}, boxes);
        addNeighbours(2,new int[]{1,3,5,6,7}, boxes);
        addNeighbours(3,new int[]{2,4,6,7,8}, boxes);
        addNeighbours(4,new int[]{3,7,8}, boxes);
        addNeighbours(5,new int[]{1,2,6,9,10}, boxes);
        addNeighbours(6,new int[]{1,2,3,5,7,9,10,11}, boxes);
        addNeighbours(7,new int[]{2,3,4,6,8,10,11,12}, boxes);
        addNeighbours(8,new int[]{3,4,7,11,12}, boxes);
        addNeighbours(9,new int[]{5,6,10,13,14}, boxes);
        addNeighbours(10,new int[]{5,6,7,9,11,13,14,15}, boxes);
        addNeighbours(11,new int[]{6,7,8,10,12,14,15,16}, boxes);
        addNeighbours(12,new int[]{7,8,11,15,16}, boxes);
        addNeighbours(13,new int[]{9,10,14}, boxes);
        addNeighbours(14,new int[]{9,10,11,13,15}, boxes);
        addNeighbours(15,new int[]{10,11,12,14,16}, boxes);
        addNeighbours(16,new int[]{11,12,15}, boxes);

        return removeBoxes(boxes,deadBoxes);
    }

    public void writeBoxes() {
        int counter = 0;
        for (Box box : boxes) {
            if (counter == 4) {
                System.out.print("\n");
                counter=0;
            }
            System.out.print(box.getLetter());
            counter++;
        }
        System.out.print("\n");
    }
}

/**
 * Pairs (letter,id)
 * Each path made of those pairs
 */

class Pair{
    Character letter;
    int id;
    public Pair(int id, Character letter){
        this.letter=letter;
        this.id=id;
    }

    public Character getLetter(){
        return letter;
    }
}
