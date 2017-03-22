/**
 * Created by micha on 22.03.2017.
 */
public class Box {
    private Character letter;
    private boolean seen;

    public Box(Character letter){
        this.letter = letter;
        seen = false;
    }

    public void check(){    seen = true;    }
    public void uncheck(){  seen = false;   }
}
