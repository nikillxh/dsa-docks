public class Tree {
    public class Node {
        int value;
        Node parent, left, right;

        public Node(int value) {
            this.value = value;
            parent = left = right = null;
        }
    }

    public static int depth(Node tree) {
        if (tree.parent == null) {
            return 0;
        } else {
            return 1 + depth(tree);
        }
    }

    public static int depthPre(int[] tree) {
        if (tree.length == 1) {return 1;}
        int depth = 0;
        for (int i = 1; i < tree.length; i = i * 2) {depth ++;}
        return depth;
    }

    public static void preOrder(Node tree) {
    }

    // public static Node traversalBT(int[] preorder, int[] inorder) {

    // }

    public static void printArr(int[] arr) {
        System.out.print("[" + arr[0]);
        for (int i = 1; i < arr.length; i++) {
            System.out.print(", " + arr[i]);
        }
        System.out.print("]");
    }

    public static int[] rightSide(int[] tree) {
        int[] right = new int[depthPre(tree)];
        right[0] = tree[0];
        int d = 1, t = 1, p = 2;
        while(t < tree.length) {
            for (int i = 0; i < p; i++) {
                System.out.println(t+i);
                if (tree[t + i] != -1) {right[d] = tree[t + i];}
            }
            t += p;
            p *= 2;
            d += 1;
        } 
        return right;
    }

    public static void main(String[] args) {
        int[] binaryRight = {1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7, 8};
        printArr(rightSide(binaryRight));
    }
}