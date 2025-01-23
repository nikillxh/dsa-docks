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

        while (num != 0 && num > 0) {
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

    public static int outputAsInteger(Number num) {
        Digits list = num.num;
        int integer = 0;
        int factor = 1;

        while (list.next != null && factor < 1000000000) {
            integer += list.value * factor;
            factor *= 10;
            list = list.next;
        }
        if (factor < 1000000000) {integer += list.value * factor;}

        return integer;
    }

    public static String outputAsString(Number num) {
        Digits list = num.num;
        String integer = "";
        int length = 0;

        while (list.next != null && length < 10) {
            integer = list.value + integer;
            length += 1;
            list = list.next;
        }
        if (length < 10) {integer = list.value + integer;}

        return integer;
    }

    // Addition. F in the terminal
    public static Number addition(Number x, Number y) {
        Number sum = new Number();
        sum.num = new Digits(0);
        Digits pointer_sum = sum.num, pointer_x = x.num, pointer_y = y.num;
        int carry = 0;

        while (pointer_x.next != null || pointer_y.next != null) {
            pointer_sum.value = (pointer_x.value * x.sign) + (pointer_y.value * y.sign) + carry;
            if (pointer_sum.value > 9) {
                pointer_sum.value -= 10;
                carry += 1;
            } else if (pointer_sum.value < 0) {
                pointer_sum.value += 10;
                carry -= 1;
            } else {
                carry = 0;
            }

            if (pointer_x.next != null && pointer_y.next != null) {
                pointer_sum.next = new Digits(0);
                pointer_sum = pointer_sum.next;
            }

            if (pointer_x.next == null || pointer_y.next == null) {break;}
            else {pointer_x = pointer_x.next;}
            if (pointer_y.next == null) {break;}
            else {pointer_y = pointer_y.next;}

        }

        if (pointer_x.next == null && pointer_y.next == null) {
            // pointer_sum.next = new Digits(0);
            // pointer_sum = pointer_sum.next;
            pointer_sum.value = (pointer_x.value * x.sign) + (pointer_y.value * y.sign) + carry;
            if (pointer_sum.value > 9) {
                pointer_sum.value -= 10;
                carry += 1;
            } else if (pointer_sum.value < 0) {
                pointer_sum.value += 10;
                carry -= 1;
            } else {
                carry = 0;
            }
        }

        while (pointer_x.next != null) {
            pointer_sum.next = new Digits(0);
            pointer_sum = pointer_sum.next;
            pointer_x = pointer_x.next;

            pointer_sum.value = pointer_x.value * x.sign + carry;
            if (pointer_sum.value > 9) {
                pointer_sum.value -= 10;
                carry += 1;
            } else if (pointer_sum.value < 0) {
                pointer_sum.value += 10;
                carry -= 1;
            } else {
                carry = 0;
            }
            if (pointer_x.next == null) {break;}
            else {
                pointer_x = pointer_x.next;
                if (pointer_x.next == null) {
                pointer_sum.next = new Digits(0);
                pointer_sum = pointer_sum.next;
                pointer_sum.value = pointer_x.value * x.sign + carry;
                } 
            }
        }

        while (pointer_y.next != null) {
            pointer_sum.next = new Digits(0);
            pointer_sum = pointer_sum.next;
            pointer_y = pointer_y.next;

            pointer_sum.value = pointer_y.value * y.sign + carry;
            if (pointer_sum.value > 9) {
                pointer_sum.value -= 10;
                carry += 1;
            } else if (pointer_sum.value < 0) {
                pointer_sum.value += 10;
                carry -= 1;
            } else {
                carry = 0;
            }
            if (pointer_y.next == null) {break;}
            else {
                pointer_y = pointer_y.next;
                if (pointer_y.next == null) {
                pointer_sum.next = new Digits(0);
                pointer_sum = pointer_sum.next;
                pointer_sum.value = pointer_y.value * y.sign + carry;
                } 
            }
        }

        if (pointer_sum.value < 0) {
            pointer_sum.value *= -1;
            sum.sign = -1;
        } else if (pointer_sum.value > 0) {
            sum.sign = 1;
        } else {sum.sign = 1;}

        if (carry != 0) {pointer_sum.next = new Digits(1);}
        if(pointer_sum.value == 0) {
            pointer_sum = null;
        }

        return sum;
    }

    public static void main(String[] args) {
        Number x = convertToL(2323);
        Number y = convertToL(-3134);
        System.out.println(compareNumber(x, y));
        printNumber(addition(x, y));

        Number asType = convertToL(23342);
        System.out.println(outputAsInteger(asType));
        System.out.println(outputAsString(asType));
    }
}