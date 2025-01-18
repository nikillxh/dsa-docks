public class ListNode {
    int data;
    ListNode next;

    public ListNode(int v) {
        data = v;
        next = null;
    }

    // Print Linked List
    public static void printList(ListNode list) {
        System.out.print("List: {");

        while(list.next != null) {
            System.out.print(list.data + ", ");
            list = list.next;
        }
        System.out.print(list.data + "}\n");
    }

    // Append Element at the End of Linked List
    public static void appendList(ListNode list, int value) {
        ListNode new_data = new ListNode(value);

        if (list.next == null) {
            list.next = new_data;
        } else {
            while (list.next != null) {
                list = list.next;
            }

            list.next = new_data;
        }
    }

    // Array to Linked List
    public static ListNode arrayToListNode(int[] arr) {
        ListNode list = new ListNode(arr[0]);

        for (int i = 1; i < arr.length; i++) {
            appendList(list, arr[i]);
        }

        return list;
    }

    // Merge K sorted -> K/2 Linked Lists
    public static ListNode MergeKSort(ListNode[] nodeArray) {
        while(nodeArray.length > 1) {
            nodeArray = stepMergeSort(nodeArray);
        }

        return nodeArray[0];
    }

    // Single-Step for Merge K sorted -> K/2 Linked Lists
    public static ListNode[] stepMergeSort(ListNode[] nodeArray) {
        int i = 0, j = 0, parity;
        ListNode[] newArray;
        if (nodeArray.length % 2 == 0) {
            newArray = new ListNode[nodeArray.length / 2];
            parity = 0;}
        else {
            newArray = new ListNode[(nodeArray.length / 2) + 1];
            parity = 1;}

        while (i < nodeArray.length-1) {
            if (nodeArray[i].data < nodeArray[i+1].data) {newArray[j] = sort2Sorted(nodeArray[i], nodeArray[i+1]);}
            else {newArray[j+1] = sort2Sorted(nodeArray[i+1], nodeArray[i]);}
            i += 2;
            j += 1;
        }

        if (parity == 1) {newArray[j] = nodeArray[i];}

        return newArray;
    }

    // Sort 2 Sorted Linked Lists
    public static ListNode sort2Sorted(ListNode list_one, ListNode list_two) {
        int one_flag = 1, two_flag = 1;
        ListNode sorted = new ListNode(list_one.data);
        if (list_one.next != null){list_one = list_one.next;}
        else {one_flag = 0;}
        ListNode builder = sorted;

        while (one_flag + two_flag != 0) {
            if (one_flag == 1 && list_one.data < list_two.data) {
                builder.next = new ListNode(list_one.data);
                if (list_one.next != null){list_one = list_one.next;}
                else {one_flag = 0;}
            } else if (two_flag == 1 && list_two.data <= list_one.data) {
                builder.next = new ListNode(list_two.data);
                if (list_two.next != null){list_two = list_two.next;}
                else {two_flag = 0;}                
            } else if (one_flag + two_flag == 1) {
                if (two_flag == 1) {
                    builder.next = new ListNode(list_two.data);
                    if (list_two.next != null){list_two = list_two.next;}
                    else {two_flag = 0;}
                } else {
                    builder.next = new ListNode(list_one.data);
                    if (list_one.next != null){list_one = list_one.next;}
                    else {one_flag = 0;}
                }
            }
            if (builder.next != null) {builder = builder.next;}
        }

        return sorted;
    }

    // Main function
    public static void main(String[] args) {
        int[] a = {1, 2, 5}, b = {3, 6}, c = {2, 9};
        ListNode al = arrayToListNode(a), bl = arrayToListNode(b), cl = arrayToListNode(c);
        ListNode[] toSort = {al, bl, cl};
        ListNode sorted = MergeKSort(toSort);
        
        printList(sorted);
    }
}