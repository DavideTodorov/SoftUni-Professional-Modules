import java.util.Comparator;

public class BookComparator implements Comparator<Book> {


    @Override
    public int compare(Book first, Book second) {
        int result = first.getTitle().compareTo(second.getTitle());

        if (result == 0) {
            if (first.getYear() > second.getYear()) {
                result = 1;
            } else if (first.getYear() < second.getYear()){
                result = -1;
            }
        }

        return result;
    }
}
