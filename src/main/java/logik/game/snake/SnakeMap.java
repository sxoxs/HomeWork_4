package logik.game.snake;

public class SnakeMap {
    private final char WALL = 'X';
    private final int SIZE_MAP = 10;
    private final int FULL_SIZE = 12;
    private char[][] map = new char[FULL_SIZE][FULL_SIZE];

    public SnakeMap() {
        for (int i = 0; i < FULL_SIZE; i++) {
            for (int j = 0; j < FULL_SIZE; j++) {
                if ((i == 0) || (j == 0) || (i == FULL_SIZE - 1 ) || (j == FULL_SIZE - 1)) {
                    map[i][j] = WALL;
                }
                else {
                    map[i][j] = ' ';
                }
            }
        }
    }

    public char[][] getMap() {
        return map;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }
}
