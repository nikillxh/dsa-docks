public class MathOpAlgo {

    // Euclidean GCD
    public static int findGCD (int a, int b) {
        int z, x = a, y = b;
        while(x > y && y > 0) {
            z = x % y;
            x = y;
            y = z;
        }
        return x;
    }

    // Extended Euclidean GCD
    public static void main(String[] args) {
        System.out.println(findGCD(8, 4));
    }
}