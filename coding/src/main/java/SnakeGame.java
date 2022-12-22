import java.util.ArrayDeque;
import java.util.Deque;

class SnakeGame {
    Deque<int[]> snakePos = new ArrayDeque<>();
    final int ROW = 0, COL = 1;
    int cols, rows;
    int[][] food;
    int nextFoodPos = 0;
    
    public SnakeGame(int[] snakeInitialPos, int width, int height, int[][] food) {
        this.snakePos.addFirst(snakeInitialPos);
        this.cols = width;
        this.rows = height;
        this.food = food;
    }
    
    int move(char direction) {
        if (nextFoodPos >= food.length) {
            return snakePos.size();
        }
        int[] headPos = snakePos.peek();
        assert headPos != null;
        if (direction == 'L') {
            if (headPos[COL] - 1 < 0) {
                return -1;
            }
            snakePos.addFirst(new int[] {headPos[ROW], headPos[COL]-1});
        } else if (direction == 'R') {
            if (headPos[COL] + 1 >= cols) {
                return -1;
            }
            snakePos.addFirst(new int[] {headPos[ROW], headPos[COL]+1});
        } else if (direction == 'U') {
            if (headPos[ROW] - 1 < 0) {
                return -1;
            }
            snakePos.addFirst(new int[] {headPos[ROW]-1, headPos[COL]});
        } else if (direction == 'D') {
            if (headPos[ROW] + 1 >= rows) {
                return -1;
            }
            snakePos.addFirst(new int[] {headPos[ROW]+1, headPos[COL]});
        }
        final int[] newHead = snakePos.peek();
        assert newHead != null;
        if (!(newHead[ROW] == food[nextFoodPos][ROW] && newHead[COL] == food[nextFoodPos][COL])) {
            snakePos.removeLast();
        } else {
            nextFoodPos++;
        }
        return snakePos.size();
    }
    
    public static void main(String[] args) {
        int[][] food = new int[2][2];
        food[0] = new int[] {1, 1};
        food[1] = new int[] {0, 1};
        SnakeGame snakeGame = new SnakeGame(new int[]{0, 0}, 3, 2, food);
        System.out.println(snakeGame.move('R'));
        System.out.println(snakeGame.move('D'));
        System.out.println(snakeGame.move('L'));
        System.out.println(snakeGame.move('L'));
        System.out.println(snakeGame.move('L'));
    }
}