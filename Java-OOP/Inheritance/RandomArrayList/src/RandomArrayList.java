import java.util.ArrayList;
import java.util.Random;

public class RandomArrayList<T> extends ArrayList<T> {

    public T getRandomElement(){
        Random random = new Random();
        int randomIndex = random.nextInt(super.size());
       return super.remove(randomIndex);
    }
}
