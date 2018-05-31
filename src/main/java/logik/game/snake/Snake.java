package logik.game.snake;

import java.util.ArrayList;

public class Snake {
    private final char BODY = 'o';
    private final char HEAD = 'O';
    private final char EAT = '8';
    private int eatCoordinate;

    public int getEatCoordinate() {
        return eatCoordinate;
    }

    public void setEatCoordinate(int eatCoordinate) {
        this.eatCoordinate = eatCoordinate;
    }

    private int countBody;
    private int[] coordinate;
    private ArrayList<int[]> coordinateList = new ArrayList();

    public int getCountBody() {
        return countBody;
    }

    public void setCountBody(int countBody) {
        this.countBody = countBody;
    }

    public int[] getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(int[] coordinate) {
        this.coordinate = coordinate;
    }

    public ArrayList<int[]> getCoordinateList() {
        return coordinateList;
    }

    public void setCoordinateList(ArrayList<int[]> coordinateList) {
        this.coordinateList = coordinateList;
    }
}
