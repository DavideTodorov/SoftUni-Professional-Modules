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

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public int getSize() {
        return size;
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

    public void forEach(Consumer<Integer> consumer) {
        Node curr = this.head;

        while (curr != null) {
            consumer.accept(curr.value);
            curr = curr.next;
        }
    }
}
