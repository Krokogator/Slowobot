package Dictionary;

import sun.tools.jar.CommandLine;

import java.io.IOException;
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
    public int getId() { return  id; }
}
public class Grid {

    private Character[] grid = new Character[16];
    private List<Box> boxes= new ArrayList<>();
    private List<List<Pair>> paths = new ArrayList<>();

    private CmdController cmdController;

    public Grid(Character[] grid){
        this.grid=grid;
        List<Integer> noDeadBoxes = new ArrayList<>();
        this.boxes = initBoxes(noDeadBoxes);

        cmdController = new CmdController();
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
        displaySorted(deleteDuplicates(paths));
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
    private String pairsToWord(List<Pair> pairs){
        String result="";
        for(Pair pair : pairs){
            result=result+pair.getLetter();
        }
        return result;
    }

    private List<Integer> pairsToPaths(List<Pair> pairs){
        List<Integer> paths = new ArrayList<>();
        for(Pair pair : pairs){
            paths.add(pair.getId());
        }
        return paths;
    }

    private List<List<Pair>> deleteDuplicates(List<List<Pair>> paths){
        List<List<Pair>> noDups = new ArrayList<>();
        Set<List<Pair>> toDelete = new HashSet<>();
        System.out.println(paths.size());
        for(List<Pair> path : paths){
            for(List<Pair> path2 : paths){
                if(!pairsToPaths(path).equals(pairsToPaths(path2))&&!(toDelete.contains(path)||toDelete.contains(path2))){
                    if(pairsToWord(path).equals(pairsToWord(path2))){
                        toDelete.add(path);
                        System.out.println("usuwam: = "+pairsToWord(path));
                    }
                }
            }
        }

        paths.removeAll(toDelete);
        System.out.println(paths.size());
        return paths;
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

    private void displaySorted(List<List<Pair>> listOfLists) {   //List<List<DictionaryController.Pair>> listOfLists
        List<String> listOfStrings = new ArrayList<>();
        List<List<Integer>> listOfInts = new ArrayList<>();
        for (List<Pair> list : listOfLists) {
            listOfStrings.add(pairsToWord(list));
            listOfInts.add(pairsToPaths(list));
        }


        List<String> sorted = sortList(listOfStrings);

        for (String word : sorted) {
            System.out.println(word);
        }

        List<List<Integer>> ordered = new ArrayList<>();
        int length=16;
        List<List<Integer>> toRemove = new ArrayList<>();
        while(!listOfInts.isEmpty()) {
            for (List<Integer> path : listOfInts) {
                if (path.size() == length) {
                    ordered.add(path);
                    toRemove.add(path);
                }
                listOfInts.remove(toRemove);
                toRemove.clear();

            }
            length--;
            if(length<3){ break;}
        }

        for (List<Integer> path : ordered) {
            System.out.println(path.toString());

            runPath(path);


            //timer(path.size());

        }
        //cmdController.printCmd();
        List<Integer> testPath = new ArrayList<>();
        testPath.add(1);
        testPath.add(2);
        testPath.add(3);
        testPath.add(4);
        testPath.add(5);
        testPath.add(6);
        testPath.add(7);
        testPath.add(8);
        testPath.add(9);
        testPath.add(10);
        testPath.add(11);
        testPath.add(12);
        testPath.add(13);
        testPath.add(14);
        testPath.add(15);
        testPath.add(16);
        //runPath(testPath);
    }

    private String xInput(int x){
        return "adb shell sendevent /dev/input/event1 3 53 "+x;
    }

    private String yInput(int y){
        return "adb shell sendevent /dev/input/event1 3 54 "+y;
    }

    private String createTouchSequence(List<Integer> path){
        String sequence="";
        int x1=200,x2=550,x3=900,x4=1250;
        int y1=1050,y2=1470,y3=1890,y4=2310;

        for(Integer id : path){
            if(id==1){sequence=sequence+xInput(x1)+" & "+yInput(y1)+" & ";}
            else if(id==2){sequence=sequence+xInput(x2)+" & "+yInput(y1)+" & ";}
            else if(id==3){sequence=sequence+xInput(x3)+" & "+yInput(y1)+" & ";}
            else if(id==4){sequence=sequence+xInput(x4)+" & "+yInput(y1)+" & ";}
            else if(id==5){sequence=sequence+xInput(x1)+" & "+yInput(y2)+" & ";}
            else if(id==6){sequence=sequence+xInput(x2)+" & "+yInput(y2)+" & ";}
            else if(id==7){sequence=sequence+xInput(x3)+" & "+yInput(y2)+" & ";}
            else if(id==8){sequence=sequence+xInput(x4)+" & "+yInput(y2)+" & ";}
            else if(id==9){sequence=sequence+xInput(x1)+" & "+yInput(y3)+" & ";}
            else if(id==10){sequence=sequence+xInput(x2)+" & "+yInput(y3)+" & ";}
            else if(id==11){sequence=sequence+xInput(x3)+" & "+yInput(y3)+" & ";}
            else if(id==12){sequence=sequence+xInput(x4)+" & "+yInput(y3)+" & ";}
            else if(id==13){sequence=sequence+xInput(x1)+" & "+yInput(y4)+" & ";}
            else if(id==14){sequence=sequence+xInput(x2)+" & "+yInput(y4)+" & ";}
            else if(id==15){sequence=sequence+xInput(x3)+" & "+yInput(y4)+" & ";}
            else if(id==16){sequence=sequence+xInput(x4)+" & "+yInput(y4)+" & ";}
            sequence=sequence+"adb shell sendevent /dev/input/event1 0 0 0 & ";
        }
        return sequence;
    }

    public static long timer(int length){
        long timer=0;
        //*250 +1050 - ac
        timer=(length-2) *190 +700; //1100
        long start = System.currentTimeMillis();
        long elapsed = System.currentTimeMillis() - start;
        while(elapsed < timer){
            elapsed = System.currentTimeMillis() - start;
        }
        return elapsed;
    }

    private void runPath(List<Integer> path) {
        String pathCommand="";
        String initTouch = "adb shell sendevent /dev/input/event1 3 57 14 & adb shell sendevent /dev/input/event1 1 330 1 & adb shell sendevent /dev/input/event1 1 325 1 & ";
        String endTouch = "adb shell sendevent /dev/input/event1 3 57 4294967295 & adb shell sendevent /dev/input/event1 1 330 0 & adb shell sendevent /dev/input/event1 1 325 0 & adb shell sendevent /dev/input/event1 0 0 0 & exit";
        pathCommand=pathCommand+initTouch;
        pathCommand=pathCommand+createTouchSequence(path);
        pathCommand=pathCommand+endTouch;


        String mainCommand=("cmd /B cmd.exe /K \""+pathCommand+"\"");

        System.out.println(mainCommand);

        try {
            Process p = Runtime.getRuntime().exec(mainCommand);
            try {
                p.waitFor();
                p.destroyForcibly();
            }
            catch (InterruptedException e) {
                //e.printStackTrace();
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }




/*
        CommandLine cmdLine = CommandLine.parse(mainCommand);
        DefaultExecutor executor = new DefaultExecutor();
        int exitValue = executor.execute(cmdLine);*/

        //cmdController.runCommand(mainCommand);
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




