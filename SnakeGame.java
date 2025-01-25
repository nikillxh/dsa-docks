import java.util.Random;
import java.util.Scanner;

public class SnakeGame {
    private int n;
    private Head snake;
    private int[] apple;
    private int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int currentDirection = 0;
    
    public SnakeGame (int n) {
        Random rand1 = new Random();
        Random rand2 = new Random();
        this.n = n;
        this.apple = new int[]{rand1.nextInt(n) + 1, rand1.nextInt(n) + 1};
        this.snake = new Head();
        this.snake.posX = rand2.nextInt(n)+1; this.snake.posY = rand2.nextInt(n)+1;
        this.snake.dir = 'l';
    }

    public class Head {
        int posX, posY;
        char dir;
        Body body = null;
        int length = 0;

        public static class Body{
            int posX, posY;
            char chunkDir;
            Body meat = null;

            Body(char cDir) {
                chunkDir = cDir;
                meat = null;
            }
        }

        public int[][] parseBody() {
            Body scale;
            int[][] snakeIndex = new int[this.length+1][2];

            snakeIndex[0] = new int[]{this.posX, this.posY};
            
            if (this.body != null) {
                scale = this.body;

                for (int i = 1; i < this.length + 1; i++) {
                    snakeIndex[i] = new int[]{scale.posX, scale.posY};
                    if (this.body != null) {scale = scale.meat;}
                }
            }

            return snakeIndex;
        }

        private void stateSnake() {
            if (snake.posX == apple[0]-1 && snake.posY == apple[1]-1) {
                if (snake.length == 0) {
                    Body new_part = new Body(snake.dir);
                    new_part.posX = snake.posX; new_part.posY = snake.posY;
                    snake.body = new_part;
                    snake.length += 1;   
                } else {
                    Body pointer = snake.body;
                    for (int i = 1; i < snake.length; i++) {pointer = pointer.meat;}
                    Body new_part = new Body(pointer.chunkDir);
                    new_part.posX = pointer.posX; new_part.posY = pointer.posY;
                    pointer.meat = new_part;
                    snake.length += 1;
                    System.out.println(snake.length);
                }
            }
            int prevPosX = snake.posX; int prevPosY = snake.posY;
            Body pointer = snake.body;
            if (snake.length >= 1) {
                pointer.posX = prevPosX; pointer.posY = prevPosY;
            }
            for(int j = 1; j < snake.length; j++) {
                prevPosX = pointer.meat.posX; prevPosY = pointer.meat.posY;
                pointer.meat.posX = pointer.posX; pointer.meat.posY = pointer.posY;
                pointer.posX = prevPosX; pointer.posY = prevPosY;
                pointer = pointer.meat;
            }
        }
    }

    public void printArray(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("\n");
        }
    }

    private void generateApple() {
        Random rand = new Random();
        int x, y;
        do { 
            x = rand.nextInt(n) + 1;
            y = rand.nextInt(n) + 1;
        } while (isOccupiedBySnake(x, y));
        this.apple = new int[]{x, y};
    }

    private boolean isOccupiedBySnake(int x, int y) {
        Head.Body pointer = null;
        if (snake.body != null){pointer = snake.body;}
        for(int i = 0; i < snake.length + 1; i++) {
            if (i == 0) {if(snake.posX == x && snake.posY == y) {return true;}}
            else {
                if(pointer.posX == x && pointer.posY == y) {return true;}
                else if (pointer.meat != null) {pointer = pointer.meat;}
            }
        }
        return false;
    }

    public void changeDirection(char newDirection) {
        if (newDirection == 'l') {snake.dir = 'l';}
        else if (newDirection == 'k') {snake.dir = 'k';}
        else if (newDirection == 'h') {snake.dir = 'h';}
        else if (newDirection == 'j') {snake.dir = 'j';}
    }

    public boolean moveSnake() {
        if (snake.dir == 'h') {snake.posY -= 1;}
        else if (snake.dir == 'j') {snake.posX -= 1;}
        else if (snake.dir == 'k') {snake.posX += 1;}
        else if (snake.dir == 'l') {snake.posY += 1;}
        return true;
    }

    private boolean isCollision(int newX, int newY) {
        return false;
    }

    public void printState() {
        char[][] grid = new char[n][n];
        char face = 0;

        if (snake.dir == 'l') {face = '⮞';}
        else if (snake.dir == 'k') {face = '⮟';}
        else if (snake.dir == 'h') {face = '⮜';}
        else if (snake.dir == 'j') {face = '⮝';}

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) grid[i][j] = '.';
        }

        int[][] snakeIndex = snake.parseBody();
        // printMatrix(snakeIndex);
        for (int i = 0; i < snake.length + 1; i++) {
            if (i == 0) {grid[snakeIndex[i][0]][snakeIndex[i][1]] = face;}
            else {grid[snakeIndex[i][0]][snakeIndex[i][1]] = 'O';}
        }

        if (snake.posX == apple[0]-1 && snake.posY == apple[1]-1) {grid[apple[0] -1][apple[1] - 1] = face;}
        else grid[apple[0] -1][apple[1] - 1] = 'A';

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SnakeGame game = new SnakeGame(5);
        boolean gameState = true;
        game.printState();
        while(gameState) {
            char direction = 'n';
            Scanner sc = new Scanner(System.in);

            // Enter direction (n = null, l = right, k = down, h = left, j = up, any other number = exit )
            direction = sc.next().charAt(0);
            if (direction == 'l') {game.changeDirection(direction);}
            else if (direction == 'k') {game.changeDirection(direction);}
            else if (direction == 'h') {game.changeDirection(direction);}
            else if (direction == 'j') {game.changeDirection(direction);}
            else if (direction != 'l' || direction != 'k' || direction != 'h' || direction != 'j') {
                gameState = false;
                break;
            } else {continue;}

            if (!game.moveSnake()) {
                System.out.println("Illegal move");
                gameState = false;
            }

            game.printState();

            game.snake.stateSnake();
            if (game.snake.posX == game.apple[0]-1 && game.snake.posY == game.apple[1]-1) {game.generateApple();}
        }
    } 
}