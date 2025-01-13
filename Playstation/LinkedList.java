
public class LinkedList {

    Node head;

    static class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    public static LinkedList insert(LinkedList list, int data) {
        Node new_data = new Node(data);

        if (list.head == null) {
            list.head = new_data;
        } else {
            Node last = list.head;

            while (last.next != null) {
                last = last.next;
            }

            last.next = new_data;
        }

        return list;
    }

    public static void print(LinkedList list) {
        Node curr_node = list.head;
        System.out.print("List: ");

        while (curr_node != null) {
            System.out.print(curr_node.data + " ");
            curr_node = curr_node.next;
        }
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        list = list.insert(list, 1);
        list = list.insert(list, 2);
        list = list.insert(list, 3);
        list = list.insert(list, 4);
        list = list.insert(list, 5);
        list = list.insert(list, 6);
        list = list.insert(list, 7);

        list.print(list);
    }
}