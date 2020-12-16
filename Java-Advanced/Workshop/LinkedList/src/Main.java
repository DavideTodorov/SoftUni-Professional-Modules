public class Main {

    public static void main(String[] args) {

        DoublyLinkedList linkedList = new DoublyLinkedList();


        linkedList.addLast(1);
        linkedList.addLast(2);


        try {
            System.out.println(linkedList.removeFirst());
            System.out.println(linkedList.removeFirst());
            System.out.println(linkedList.removeLast());
        }catch (IllegalStateException e){
            System.out.println(e.getMessage());
        }

    }
}
