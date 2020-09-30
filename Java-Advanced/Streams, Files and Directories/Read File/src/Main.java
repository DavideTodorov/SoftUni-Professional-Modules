import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {
            FileInputStream textStream = new FileInputStream("input.txt");
            int oneByte = textStream.read();
            while (oneByte != -1){
                System.out.printf("%s ", Integer.toBinaryString(oneByte));
                oneByte = textStream.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
