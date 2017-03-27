package Dictionary;

import java.util.*;

/**
 * Created by Michał(Krokogator) on 22.03.2017.
 *
 * Represents 4x4 grid from game and finds all possible words
 */

class Pair{

    /**
     * Pairs (letter,id)
     * Each path made of those pairs
     */

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
public class Grid {

    private Character[] grid = new Character[16];
    private List<Box> boxes= new ArrayList<>();
    private List<List<Pair>> paths = new ArrayList<>();

    public Grid(Character[] grid){
        this.grid=grid;
        List<Integer> noDeadBoxes = new ArrayList<>();
        this.boxes = initBoxes(noDeadBoxes);
    }

    //runs my custom DFS algorithm on each letter/box
    public void findPaths(Tree tree){
        paths.clear();
        for(int i=0;i<16;i++) {
            List<Pair> currentPath = new ArrayList<>();
            Box box = boxes.get(i);
            List<Integer> indexDead = new ArrayList<>();
            indexDead.add(i+1);
            List<Box> newboxes = initBoxes(indexDead);
            customDFS(box, tree, newboxes, paths, currentPath);
        }
        //displayListOfListsOfPairs(paths);
        displaySorted(paths);
    }

    /**
     * custom DFS algorithm that looks for words > 3 && < 17 in a 4x4 letter grid
     *
     * - each call checks if current path creates a valid (meaningful) word
     * - each call checks if current path(word) + box(letter) that can be visited can create a subword of a valid word
     */
    private List<List<Pair>> customDFS(Box box, Tree tree, List<Box> aliveBoxes, List<List<Pair>> validPaths, List<Pair> possiblePath){

        List<Integer> deadBoxes = getDead(aliveBoxes);
        deadBoxes.add(box.getId());
        List<Box> nextAliveBoxes = initBoxes(deadBoxes);

        List<Pair> currentPath = new ArrayList<>();
        currentPath.addAll(possiblePath);
        currentPath.add(new Pair(box.getId(),box.getLetter()));
        if(currentPath.size()>2&&tree.checkWord(pairsToWord(currentPath))){
            validPaths.add(currentPath);
        }

        for(Box alive : nextAliveBoxes){
            if(contains(box.getNeighbours(),alive.getId())){

                List<Pair> tempPath = new ArrayList<>();
                tempPath.addAll(currentPath);
                tempPath.add(new Pair(alive.getId(),alive.getLetter()));

                if(tree.checkPath(pairsToWord(tempPath))){
                    customDFS(alive, tree, nextAliveBoxes, paths, currentPath);
                }
            }
        }
        return validPaths;
    }

    //Translates list of pairs into a word
    public String pairsToWord(List<Pair> pairs){
        String result="";
        for(Pair pair : pairs){
            result=result+pair.getLetter();
        }
        return result;
    }

    //Returns "dead" (used/seen) letters in each call of customDFS
    private List<Integer>getDead(List<Box> Alive){
        List<Integer> alive = new ArrayList<>();
        List<Integer> dead = new ArrayList<>();

        for(Box box : Alive){
            alive.add(box.getId());
        }

        for(int i=1;i<17;i++){
            if(!alive.contains(i)){
                dead.add(i);
            }
        }

        return dead;
    }

    private void displaySorted(List<List<Pair>> listOfLists){   //List<List<DictionaryController.Pair>> listOfLists
        List<String> listOfStrings = new ArrayList<>();
        for(List<Pair> list : listOfLists) {
            listOfStrings.add(pairsToWord(list));
        }


        List<String> sorted = sortList(listOfStrings);

        for(String word : sorted){
            System.out.println(word);
        }
    }

    public List<String> sortList(List<String> mylist){

        Comparator<String> x = new Comparator<String>()
        {
            @Override
            public int compare(String o1, String o2)
            {
                if(o1.length() > o2.length())
                    return 1;

                if(o2.length() > o1.length())
                    return -1;

                return 0;
            }
        };

        Collections.sort(mylist,  x);

        List<String> noDups = new ArrayList<>();
        for(String w1 : mylist){
            if(!noDups.contains(w1)){
                noDups.add(w1);
            }
        }

        return noDups;
    }

    //Displays on standard output list of lists of pairs
    private void displayListOfListsOfPairs(List<List<Pair>> listOfLists){
        for(List<Pair> list : listOfLists){
            System.out.println(pairsToWord(list));
        }
    }

    //Checks if list of boxes contains given id
    private boolean contains(List<Box> boxes, int id){
        for(Box box : boxes){
            if(box.getId()==id){
                return true;
            }
        }
        return false;
    }

    //Returns box of given id
    private Box getBox(List<Box> boxes, int id){
        for(Box box:boxes){
            if(box.getId()==id){
                return box;
            }
        }
        return null;
    }

    //initialize given 4x4 grid. In each call of customDFS it creates smaller grid (without "dead" boxes)
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

    //gives each box (letter) information about its neighbourhood (surrounding letters)
    private void addNeighbours(int index, int[] neighbours, List<Box> boxes){
        index=index - 1;
        for(int neighbour : neighbours) {
            neighbour=neighbour-1;
            boxes.get(index).addNeighbours(boxes.get(neighbour));
        }
    }

    //removes elements from list of boxes
    private List<Box> removeBoxes(List<Box> boxes, List<Integer> toKillList){
        for(int toKill : toKillList){
            if(contains(boxes,toKill)){
                boxes.remove(getBox(boxes,toKill));
            }
        }
        return boxes;
    }
}




