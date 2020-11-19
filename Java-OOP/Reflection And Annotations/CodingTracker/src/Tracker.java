public class Tracker {

    @Author(name = "Peter")
    public static void printMethodsByAuthor(Class<?> clazz){
        System.out.println(clazz.getSimpleName());
    }
}
