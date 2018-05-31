package logik.game.snake;

import java.util.ArrayList;

public class Snake {
    private final char BODY_MARK = 'o';
    private final char HEAD_MARK = 'O';
    private int countBody;
    private int[] coordinate;
    private ArrayList<int[]> coordinateList = new ArrayList();

    public Snake() {
        this.countBody = 1;
        int[] array = {3,3};
        this.coordinateList.add(array);
    }

    public char getBODY_MARK() {
        return BODY_MARK;
    }

    public char getHEAD_MARK() {
        return HEAD_MARK;
    }

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

    public int[] getHeadCoordinate() {
        return this.coordinateList.get(0);
    }

    public void stepRight() {
        int[] nextHeadCoord= new int[2];
        nextHeadCoord = this.coordinateList.get(0);
        nextHeadCoord[1] += 1;

        for (int i = this.countBody-1; i > 0; i--) {
            this.coordinateList.set(i, this.coordinateList.get(i-1));
        }

        this.coordinateList.set(0, nextHeadCoord);
    }

    public void stepLeft() {
        int[] nextHeadCoord= new int[2];
        nextHeadCoord = this.coordinateList.get(0);
        nextHeadCoord[1] -= 1;

        for (int i = this.countBody-1; i > 0; i--) {
            this.coordinateList.set(i, this.coordinateList.get(i-1));
        }

        this.coordinateList.set(0, nextHeadCoord);
    }

    public void stepTop() {
        int[] nextHeadCoord= new int[2];
        nextHeadCoord = this.coordinateList.get(0);
        nextHeadCoord[0] -= 1;

        for (int i = this.countBody-1; i > 0; i--) {
            this.coordinateList.set(i, this.coordinateList.get(i-1));
        }

        this.coordinateList.set(0, nextHeadCoord);
    }

    public void stepBottom() {
        int[] nextHeadCoord= new int[2];
        nextHeadCoord = this.coordinateList.get(0);
        nextHeadCoord[0] += 1;

        for (int i = this.countBody-1; i > 0; i--) {
            this.coordinateList.set(i, this.coordinateList.get(i-1));
        }

        this.coordinateList.set(0, nextHeadCoord);
    }

    public void addBody(int[] eatCoordinate) {
        this.countBody++;
        int[] body = new int[2];
        body[0] = this.coordinateList.get(this.countBody-2)[0];
        body[1] = this.coordinateList.get(this.countBody-2)[1];
        this.coordinateList.add(body);

        for (int i = this.countBody-2; i > 0; i--) {
            int[] bodyTemp = new int[2];
            bodyTemp[0] = this.coordinateList.get(this.countBody-2)[0];
            bodyTemp[1] = this.coordinateList.get(this.countBody-2)[1];

            this.coordinateList.set(i, bodyTemp);
        }
        int[] bodyTemp = new int[2];
        bodyTemp[0] = eatCoordinate[0];
        bodyTemp[1] = eatCoordinate[1];
        this.coordinateList.set(0, bodyTemp);

    }


    public boolean itsEat(int[] eatCoordinate) {

        if ((eatCoordinate[0] == this.coordinateList.get(0)[0])&&(eatCoordinate[1] == this.coordinateList.get(0)[1])) {
            this.addBody(eatCoordinate);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isCrashedIntoSelf() {
        for (int i = 1; i < this.countBody; i++) {
            if (this.coordinateList.get(0) == this.coordinateList.get(i)) {
                return true;
            }
        }

        return false;
    }
}
