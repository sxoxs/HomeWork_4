package logik.game.snake;

public class SnakeMap {
    private final char WALL = 'X';
    private final int SIZE_MAP = 10;
    private final int FULL_SIZE = 12;
    private char[][] map = new char[FULL_SIZE][FULL_SIZE];
    private final char EAT_MARK = '8';
    private int[] eatCoordinate = new int[2];

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


    public void setEatCoordinate(int[] eatCoordinate) {
        this.eatCoordinate = eatCoordinate;
    }

    public char[][] getMap() {
        return map;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }

    public int getSIZE_MAP() {
        return SIZE_MAP;
    }

    public int getFULL_SIZE() {
        return FULL_SIZE;
    }

    public void clearMap() {
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

    private void addEatOnMap(){
        this.map[this.eatCoordinate[0]][this.eatCoordinate[1]] = EAT_MARK;
    }


    public boolean isCrashedWall(int[] headCoordinate) {
        if ((headCoordinate[0] == 0) || (headCoordinate[0] == (FULL_SIZE-1)) ||
                (headCoordinate[1] == 0) || (headCoordinate[1] == (FULL_SIZE-1))) {
            return true;
        }
        else {
            return false;
        }


    }

    public void addSnakeOnMap(Snake snake) {
        clearMap();
        map[snake.getHeadCoordinate()[0]][snake.getHeadCoordinate()[1]] = snake.getHEAD_MARK() ;

        for (int i = 1; i < snake.getCountBody(); i++) {
            map[snake.getCoordinateList().get(i)[0]][snake.getCoordinateList().get(i)[1]] = snake.getBODY_MARK() ;
        }
        addEatOnMap();
    }
}
