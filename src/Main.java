public class Main {

    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        Character[][] input = {{'a','b','e','f'},{'a','b','c','d'},{'a','b','c','d'},{'a','b','c','d'}};
        Grid grid = new Grid(input);

        dictionary.loadDictionary();
    }
}
