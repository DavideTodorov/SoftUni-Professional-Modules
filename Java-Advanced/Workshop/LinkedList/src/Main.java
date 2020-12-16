public class Main {

    public static void main(String[] args) {

        DoublyLinkedList linkedList = new DoublyLinkedList();

        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);

        linkedList.forEach(System.out::println);
    }
}
