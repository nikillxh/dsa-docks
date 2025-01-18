import java.util.Arrays;

public class ArrayOperations {
    // Print 1D-Array
    public static void printArray(int[] arr) {
        System.out.print("{");
        for (int i=0; i< arr.length; i++) {
            if (i == arr.length-1) {
                System.out.print(arr[i]);
            } else {System.out.print(arr[i] + ",");}
        }
        System.out.print("}\n");
    }

    // Print 2D-Array or Matrix
    public static void printMatrix(int[][] arr) {
        System.out.print("{");
        for (int i=0; i< arr.length; i++) {
            System.out.print("{");
            for (int j = 0; j < arr[0].length; j++) {
                if (j == arr[0].length-1) {
                    System.out.print(arr[i][j]);
                } else {System.out.print(arr[i][j] + ",");}
            }
            if (i == arr.length-1) {
                System.out.print("}");
            } else {System.out.print("}\n");}
        }
        System.out.print("}\n");
    }
    
    // Maximum in an Array
    public static int FindMax(int[] arr) {
        int i = arr[0];
        for (int n = 0; n < arr.length; n++) {
            if (arr[n] > i) {
                i = arr[n];
            }
        }
        return i;
    }

    // Is the Array sorted?
    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            if (arr[i+1] < arr[i]) {
                return false;
            }
        }
        return true;
    }

    // Sort an Array with atmost Three Values Without Using Sorting
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

    // Prime factor sudoku validation
    public static boolean isValidSudoku(int[][] board) {
        int[] prime = {1, 2, 3, 5, 7, 11, 13, 17, 19, 23};
        // Row Check
        for (int i = 0; i < board.length; i++) {
            int factor = 1;
            for (int j = 0; j < board.length; j++) {
                if ((board[i][j] != 0) && (factor % prime[board[i][j]] != 0)) {
                    factor *= prime[board[i][j]];
                } else if ((board[i][j] != 0) && (factor % prime[board[i][j]] == 0)) {
                    return false;
                }
            }
        }

        // Column Check
        for (int i = 0; i < board.length; i++) {
            int factor = 1;
            for (int j = 0; j < board.length; j++) {
                if ((board[j][i] != 0) && (factor % prime[board[j][i]] != 0)) {
                    factor *= prime[board[j][i]];
                } else if ((board[j][i] != 0) && (factor % prime[board[j][i]] == 0)) {
                    return false;
                }
            }
        }

        // Block Check
        for (int pad = 0; pad < 7; pad += 3){
            int factor = 1;
            for (int i = 0 + pad; i < 3 + pad; i++) {
                for (int j = 0 + pad; j < 3 + pad; j++) {
                    if ((board[i][j] != 0) && (factor % prime[board[i][j]] != 0)) {
                        factor *= prime[board[i][j]];
                    } else if ((board[i][j] != 0) && (factor % prime[board[i][j]] == 0)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Kadane's Algorithm
    public static int maxSubArrSum(int[] arr) {
        int res = arr[0];
        int maxEnding = arr[0]; 

        for (int element: arr) {
            if (maxEnding < element) {
                maxEnding = element;
            } else {maxEnding += element;}

            if (res < maxEnding) {
                res = maxEnding;
            }
        }
        return res;
    }

    public static int[][] gameOfLife(int[][] matrix) {
        // Space complexity *_* where?
        int[] nav = {-1,0,1};
        int[][] next = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int kins = 0;
                for (int x: nav) {
                    for (int y: nav) {
                        if ((x == 0 && y == 0) || i+x < 0 || i+x == matrix.length || j+y < 0 || j+y == matrix[0].length) {}
                        else {
                            kins += matrix[i+x][j+y];
                        }
                    }
                }
                if (kins == 3) {next[i][j] = 1;}
                else if (kins < 2 || kins > 3) {next[i][j] = 0;}
            }
        }
        return next;
    }

    // Rotate Array by k-steps
    public static void rotateArray(int[] nums, int k) {
        k = nums.length - k;
        int wrap = k % nums.length, i;
        for (i = 0; wrap + i < nums.length; i++) {
            nums[i] -= nums[wrap + i];
            nums[wrap + i] += nums[i];
            nums[i] = nums[wrap + i] - nums[i];
        }

        for (i = i; i < nums.length-1; i++) {
            nums[i] -= nums[i+1];
            nums[i+1] += nums[i];
            nums[i] = nums[i+1] - nums[i]; 
        }
    }

    // Range Minimum query
    // Time Complexity *_*
    public static void rangemq(String input) {

        String[] lines = input.split("\n");

        int _ = Integer.parseInt(lines[0]);

        int[] arr = Arrays.stream(lines[1].split("\\s+"))
                          .mapToInt(Integer::parseInt)
                          .toArray();

        int q = Integer.parseInt(lines[2]);

        for (int i = 0; i < q; i++) {
            String[] queryParts = lines[3 + i].split("\\s+");
            int l = Integer.parseInt(queryParts[0])-1; // l
            int r = Integer.parseInt(queryParts[1])-1; // r
            int min = arr[l];
            for (int j = l+1; j < r-l+1 ; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                }
            }
            System.out.println(min);
        }
    }

    // Tuples with Constraints on Three Sorted Arrays
    // Broken Vessel
    public static int threeArrays(int[] A, int[] B, int[] C, int D) {
        int tuples = 0;
        int i = 0, j = 0, k = 0;

        while (i != A.length-1 || j != A.length-1 || k != A.length-1) {
            if (A[i] - B[j] <= D || B[j] - A[i] <= D) {
                if ((B[j] - C[k] <= D || C[k] - B[j] <= D) && i != A.length-1) {
                    tuples += 1;
                    if (i != A.length-1) {i++;}
                } else if (B[j] >= C[k]){
                    if (k != A.length-1) {k++;}
                } else if (C[k] > B[j]) {
                    j++;
                }
            } else if (A[i] >= B[j]){
                if (j != A.length-1) {j++;}
            } else if (B[j] > A[i]) {
                if (i != A.length-1) {i++;}
            }
        }

        return tuples;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 6, 31, 2};
        int[] sort = {1, 2, 3};
        int[] sortThree = {2, 1, 3, 2, 1, 3, 2};
        int[][] board = {{7, 0, 0, 0, 0, 0, 2, 0, 0},   
            {4, 0, 5, 0, 0, 0, 0, 0, 3},   
            {0, 4, 0, 2, 0, 1, 0, 0, 0},   
            {3, 0, 0, 1, 8, 0, 0, 9, 7},   
            {0, 0, 9, 0, 7, 0, 6, 0, 0},   
            {6, 5, 0, 0, 3, 2, 0, 0, 1},   
            {0, 0, 0, 4, 0, 9, 0, 0, 0},   
            {5, 0, 0, 0, 0, 0, 1, 0, 6},   
            {0, 0, 6, 0, 0, 0, 0, 0, 8}   
        };  
        int[] findMaxSum = {2, -1, 3, 4, -6, 7, -9, 8};
        int[][] matrixOfLife = {{1, 0, 0, 0, 0, 0, 1, 0, 0},
            {1, 0, 1, 0, 0, 0, 0, 0, 1},
            {0, 1, 0, 1, 0, 1, 0, 0, 0},
            {1, 0, 0, 1, 1, 0, 0, 1, 1},
            {0, 0, 0, 0, 1, 0, 1, 0, 0},
            {1, 1, 0, 0, 1, 1, 0, 0, 1},
            {0, 0, 0, 1, 0, 1, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 1, 0, 1},
            {0, 0, 1, 0, 0, 0, 0, 0, 1}
        };

        int[] nums = {1, 2, 3, 4, 5, 6, 7};

        System.out.println(FindMax(arr));

        System.out.println(isSorted(sort));
        printArray(sortThree);
        threeSort(sortThree);
        printArray(sortThree);

        System.out.println(isValidSudoku(board));

        System.out.println(maxSubArrSum(findMaxSum));

        printMatrix(gameOfLife(matrixOfLife));
        
        rangemq("""
5
1 3 -2 8 -7
3
1 3
2 4
1 5
""");
        int[] A = {1, 2, 3};
        int[] B = {1, 2, 4};
        int[] C = {2, 2, 5};
        System.out.println(threeArrays(A, B, C, 1));
        
        rotateArray(nums, 3);
        printArray(nums);
    }
}