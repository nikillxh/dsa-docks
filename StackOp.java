import java.util.Stack;

public class StackOp{
    public static class MyQueue {
        Stack stack1;
        Stack stack2;

        public MyQueue() {
            stack1 = new Stack<Integer>();
            stack2 = new Stack<Integer>();
        }

        private void transferElements() {
            if (this.stack1.isEmpty() && this.stack2.isEmpty()) {return;}
            else if (this.stack1.isEmpty()) {
                while (!this.stack2.isEmpty()) {this.stack1.push(this.stack2.pop());}
            } else {while (!this.stack1.isEmpty()) {this.stack2.push(this.stack1.pop());}}
        }

        public void enqueue(int x) {
            if (this.stack2.isEmpty()) {this.transferElements();}
            this.stack2.push(x);
        }

        public void dequeue() {
            if (this.stack1.isEmpty()) {this.transferElements();}
            if (!this.stack1.isEmpty()) {this.stack1.pop();}           
        }

        public int front() {
            if (this.stack1.isEmpty()) {this.transferElements();}
            if (!this.stack1.isEmpty()) {return (int) this.stack1.peek();}
            else {return -1;}
        }

        public boolean isEmpty() {
            if (this.stack1.isEmpty() && this.stack2.isEmpty()) {return true;}
            else {return false;}
        }
    }

    public static class Minstack {
        int i, m, end;
        int[] stack;

        public Minstack() {
            stack = new int[20];
            i = 0; m = 0; end = 0;
        }

        public void push(int x) {
            i = (i+1) % this.stack.length;
            stack[i] = x;
            if (stack[m] >= stack[i]) {m = i;}
            if (i == end) {end = i+1;}
        }

        public int top() {
            return stack[i];
        }

        public void pop() {
            if ((i-1) % this.stack.length != end) {
                i = (i-1) % this.stack.length;
            }
        }

        public int getMin() {
            return stack[m];
        }
    }

    public static String ff() {
        String str = "";
        Stack count = new Stack<Integer>();
        Stack string = new Stack<Integer>();
        int current = 0;

        return str;
    }

    public static void main(String[] args) {
        MyQueue queue1 = new MyQueue();
        queue1.enqueue(3);
        queue1.enqueue(2);
        System.out.println(queue1.front());
        queue1.dequeue();
        System.out.println(queue1.front());
    }
}