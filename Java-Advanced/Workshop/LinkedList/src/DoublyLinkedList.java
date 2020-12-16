import java.util.function.Consumer;

public class DoublyLinkedList {

    private class Node {
        private int value;
        private Node next;
        private Node prev;

        public Node(int value) {
            this.value = value;
        }


    }

    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {
        this.size = 0;
    }

    public void addFirst(int element) {
        Node newNode = new Node(element);

        if (size == 0) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            newNode.next = this.head;
            this.head.prev = newNode;
            this.head = newNode;
        }

        this.size++;
    }

    public void addLast(int element) {
        Node newNode = new Node(element);

        if (size == 0) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            newNode.prev = this.tail;
            this.tail = newNode;
        }

        size++;
    }

    public int get(int index) {
        if (index < 0 || index > size - 1) {
            throw new NullPointerException(String.format("Index \"%d\" is out of bounds", index));
        }

        if (index <= size / 2) {
            Node node = this.head;

            for (int i = 0; i < index; i++) {
                node = node.next;
            }

            return node.value;
        } else {
            Node node = this.tail;

            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }

            return node.value;
        }
    }

    public void forEach(Consumer<Integer> consumer) {
        Node curr = this.head;

        while (curr != null) {
            consumer.accept(curr.value);
            curr = curr.next;
        }
    }
}
