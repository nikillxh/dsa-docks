

public class Number {
    int sign;
    Digits num;

    public static class Digits {
        int value;
        Digits next;

        Digits(int v) {
            value = v;
            next = null;
        }
    }

    // Print the Number in Linked List Format
    public static void printNumber(Number number) {
        Digits pointer = number.num;
        if (number.sign == 1) {System.out.print("Number(+): ");}
        else {System.out.print("Number(-): ");}
        while (pointer.next != null) {
            System.out.print(pointer.value + "->");
            pointer = pointer.next;
        }
        System.out.print(pointer.value + "\n");
    }

    // Convert int to Linked List
    public static Number convertToL(int num){
        Number number = new Number();
        if(num < 0){
            number.sign = -1;
            num *= -1;}
        else {number.sign = 1;}

        Digits pointer = new Digits(num % 10);
        number.num = pointer;
        num /= 10;

        while (num != 0 && num > 1) {
            pointer.next = new Digits(num % 10);
            num /= 10;
            pointer = pointer.next;
        }

        return number;
    }

    // Comparison: x > y: 1, x == y: 0, x < y: -1
    public static int compareNumber(Number x, Number y) {
        if (x.sign > y.sign) {return 1;}
        else if (x.sign < y.sign) {return -1;}

        Digits pointer_x = x.num, pointer_y = y.num;
        while (pointer_x.next != null || pointer_y.next != null) {
            if (pointer_x.next == null) {return -1;}
            else {pointer_x = pointer_x.next;}
            if (pointer_y.next == null) {return 1;}
            else {pointer_y = pointer_y.next;}
        }
        if (pointer_x.value == pointer_y.value) {return 0;}
        else if (pointer_x.value > pointer_y.value) {return 1;}
        else {return -1;}
    }

    public static void main(String[] args) {
        Number x = convertToL(2332);
        Number y = convertToL(-113);
        System.out.println(compareNumber(x, y));
    }
}