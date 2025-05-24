
import java.io.Serializable;


public class TicTacModel implements Serializable{

    private char[][] plan;
    private int height;
    private int width;

    public TicTacModel(int width, int height) {
        if (width < 3 || height < 3) {
            throw new RuntimeException("Incorrect size, must be at least 3x3.");
        }
        this.width = width;
        this.height = height;
        this.plan = new char[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                plan[i][j] = ' ';
            }
        }
    }

    public char getMarkAt(int x, int y) {
        if (x < 0 || x >= this.width) {
            throw new RuntimeException("Incorrect x coordinate");
        }
        if (y < 0 || y >= this.height) {
            throw new RuntimeException("Incorrect y coordinate");
        }
        return plan[x][y];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setMarkAt(int x, int y, char mark) {

        if (x < 0 || x >= this.width) {
            throw new RuntimeException("Setting incorrect x coordinate");
        }
        if (y < 0 || y >= this.height) {
            throw new RuntimeException("Setting incorrect y coordinate");
        }
        if (plan[x][y] != ' ') {
            System.out.println("Clicked on an occupied position");
        }
        if (mark != 'x' && mark != 'o') {
            throw new RuntimeException("Bad symbol, must be x or o");
        }
        this.plan[x][y] = mark;
    }
}
