public class ArrayOperations {
    public static void printArray(int[] arr) {
        for (int i=0; i< arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.print("\n");
    }
    
    public static int FindMax(int[] arr) {
        int i = arr[0];
        for (int n = 0; n < arr.length; n++) {
            if (arr[n] > i) {
                i = arr[n];
            }
        }

        return i;
    }

    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            if (arr[i+1] < arr[i]) {
                return false;
            }
        }
        return true;
    }

    public static void threeSort(int[] arr) {
        int countA = 0, countB = 0, countC = 0;
        int a = -1, ai = 0, b = -1, bi = 0, c = -1, ci = 0;
        for(int i = 0; i < arr.length; i++) {
            if (ai == 0) {
                a = arr[i];
                countA += 1;
                ai = 1;
            } else if (ai == 1 && a == arr[i]) {
                countA += 1;
            } else if (bi == 0) {
                b = arr[i];
                countB += 1;
                bi = 1;
            } else if (bi == 1 && b == arr[i]) {
                countB += 1;
            } else if (ci == 0) {
                c = arr[i];
                countC += 1;
                ci = 1;
            } else if (ci == 1 && c == arr[i]) {
                countC += 1;
            }
        }

        if (a > c) {
            a = a - c;
            c = a + c;
            a = c - a;
        } else if (a > b) {
            a = a - b;
            b = a + b;
            a = b - a;
        }
        if (b > c) {
            b = b - c;
            c = b + c;
            b = c - b;
        }

        for(int i = 0; i < arr.length; i++) {
            if (countA > 0) {
                arr[i] = a;
                countA -= 1;
            } else if (countB > 0) {
                arr[i] = b;
                countB -= 1;
            } else if (countC > 0) {
                arr[i] = c;
                countC -= 1;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 6, 31, 2};
        int[] sort = {1, 2, 3};
        int[] sortThree = {2, 1, 3, 2, 1, 3, 2};
        System.out.println(FindMax(arr));
        System.out.println(isSorted(sort));
        printArray(sortThree);
        threeSort(sortThree);
        printArray(sortThree);
    }
}