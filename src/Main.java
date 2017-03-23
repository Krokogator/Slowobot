public class Main {

    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        Character[][] input = {{'a','b','e','f'},{'a','b','c','d'},{'a','b','c','d'},{'a','b','c','d'}};

        Grid grid = new Grid(input);
        long startTime = System.currentTimeMillis();
        dictionary.load("C:\\Users\\micha\\Desktop\\Dictionary\\slowa.txt");
        long elapsedTime = System.currentTimeMillis() - startTime;
        long elapsedSeconds = elapsedTime / 1000;
        System.out.println("Load Time: "+ elapsedTime + "ms" +"\n");
        dictionary.testPacket("stęchlizna");
    }
}
