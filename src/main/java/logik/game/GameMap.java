package logik.game;

public class GameMap {
    private char[][] map;
    private final char EMPTY_MARK = '*';
    private int countLineForWin;
    private int size;

    public GameMap() {
    }

    public GameMap(GameMap outGameMap) {
        this.setCountLineForWin(outGameMap.countLineForWin);
        this.setMapSize(outGameMap.size);
        this.setMap(outGameMap.map);
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCountLineForWin() {
        return countLineForWin;
    }

    public void setCountLineForWin(int countLineForWin) {
        this.countLineForWin = countLineForWin;
    }

    public int getSize() {
        return size;
    }

    public char getEMPTY_MARK() {
        return EMPTY_MARK;
    }


    public char[][] getMap() {
        return map;
    }

    public void setMap(char[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                this.map[i][j] = map[i][j];
            }
        }
    }

    public void setMapSize(int n){
        this.size = n;
        this.map = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = EMPTY_MARK;
            }
        }
    }

    public void setMarkCoordinates(char mark, int x, int y){
        this.map[x][y] = mark;
    }

    public boolean isEmpty (int x, int y) {
        if (this.map[x][y] == EMPTY_MARK) {
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isValidCoordinates(int x, int y) {
        if ( (x < 0 || x >= map.length) || (y < 0 || y >= map.length) ) {
            return false;
        }
        else {
            return true;
        }
    }


}
