public class ArrayOperations {
    
    public static int FindMax(int[] arr) {
        int i = arr[0];
        for (int n = 0; n < arr.length; n++) {
            if (arr[n] > i) {
                i = arr[n];
            }
        }

        return i;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 6, 31, 2};
        System.out.println(FindMax(arr));
    }
}