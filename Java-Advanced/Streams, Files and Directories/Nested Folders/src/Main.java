import java.io.File;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    public static void main(String[] args) {

        File file = new File("D:\\old\\Users\\user\\Desktop\\SoftUni-Professional-Modules\\Java-Advanced\\Streams, Files and Directories\\Nested Folders\\Files-and-Streams");

        Deque<File> deque = new ArrayDeque<>();
        deque.offer(file);

        int folderCount = 0;
        while (!deque.isEmpty()) {
            File f = deque.poll();

            folderCount++;
            System.out.println(f.getName());

            File[] files = f.listFiles();
            for (File innerFile : files) {
                if (innerFile.isDirectory()) {
                    deque.offer(innerFile);
                }
            }
        }

        System.out.println(folderCount);
    }
}
