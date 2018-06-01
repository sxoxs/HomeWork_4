package logik.game.snake;

import java.util.ArrayList;
import java.util.Arrays;

public class Snake {
    private final char BODY_MARK = 'o';
    private final char HEAD_MARK = 'O';
    private int countBody;
    private int[] coordinate;
    private ArrayList<int[]> coordinateList = new ArrayList();
    private String lastStep = "";

    public Snake() {
        this.countBody = 2;
        int[] array = {3,3};
        this.coordinateList.add(array);
        int[] arr = {3,4};
        this.coordinateList.add(arr);
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
        nextHeadCoord[0] = this.coordinateList.get(0)[0];
        nextHeadCoord[1] = this.coordinateList.get(0)[1];
        nextHeadCoord[1] += 1;

        for (int i = this.countBody-1; i > 0; i--) {
            int[] bodyTemp = new int[2];
            bodyTemp[0] = this.coordinateList.get(i-1)[0];
            bodyTemp[1] = this.coordinateList.get(i-1)[1];

            this.coordinateList.set(i, bodyTemp);
        }

        this.coordinateList.set(0, nextHeadCoord);
        this.lastStep = "right";
    }

    public void stepLeft() {
        int[] nextHeadCoord= new int[2];
        nextHeadCoord[0] = this.coordinateList.get(0)[0];
        nextHeadCoord[1] = this.coordinateList.get(0)[1];
        nextHeadCoord[1] -= 1;

        for (int i = this.countBody-1; i > 0; i--) {
            int[] bodyTemp = new int[2];
            bodyTemp[0] = this.coordinateList.get(i-1)[0];
            bodyTemp[1] = this.coordinateList.get(i-1)[1];

            this.coordinateList.set(i, bodyTemp);
        }
        this.lastStep = "left";
        this.coordinateList.set(0, nextHeadCoord);
    }

    public void stepTop() {
        int[] nextHeadCoord= new int[2];
        nextHeadCoord[0] = this.coordinateList.get(0)[0];
        nextHeadCoord[1] = this.coordinateList.get(0)[1];
        nextHeadCoord[0] -= 1;

        for (int i = this.countBody-1; i > 0; i--) {
            int[] bodyTemp = new int[2];
            bodyTemp[0] = this.coordinateList.get(i-1)[0];
            bodyTemp[1] = this.coordinateList.get(i-1)[1];

            this.coordinateList.set(i, bodyTemp);
        }
        this.lastStep = "top";
        this.coordinateList.set(0, nextHeadCoord);
    }

    public void stepBottom() {
        int[] nextHeadCoord= new int[2];
        nextHeadCoord[0] = this.coordinateList.get(0)[0];
        nextHeadCoord[1] = this.coordinateList.get(0)[1];

        nextHeadCoord[0] += 1;

        for (int i = this.countBody-1; i > 0; i--) {
            int[] bodyTemp = new int[2];
            bodyTemp[0] = this.coordinateList.get(i-1)[0];
            bodyTemp[1] = this.coordinateList.get(i-1)[1];

            this.coordinateList.set(i, bodyTemp);
        }
        this.lastStep = "bottom";
        this.coordinateList.set(0, nextHeadCoord);
    }

    public void addBody() {
        this.countBody++;

        int[] body = new int[2];
        switch (lastStep) {

            case "left": {
                body[0] = this.coordinateList.get(this.countBody-2)[0];
                body[1] = this.coordinateList.get(this.countBody-2)[1] + 1;
            }
            case "right": {
                body[0] = this.coordinateList.get(this.countBody-2)[0];
                body[1] = this.coordinateList.get(this.countBody-2)[1] - 1;
            }
            case "top": {
                body[0] = this.coordinateList.get(this.countBody-2)[0] + 1;
                body[1] = this.coordinateList.get(this.countBody-2)[1];
            }
            case "bottom": {
                body[0] = this.coordinateList.get(this.countBody-2)[0] - 1;
                body[1] = this.coordinateList.get(this.countBody-2)[1];
            }
        }
        this.coordinateList.add(body);
    }


    public boolean itsEat(int[] eatCoordinate) {

        if (Arrays.equals(eatCoordinate, this.coordinateList.get(0))) {
            this.addBody();
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isCrashedIntoSelf() {
        for (int i = 1; i < this.countBody; i++) {
            if (Arrays.equals(this.coordinateList.get(0), this.coordinateList.get(i))) {
                return true;
            }
        }

        return false;
    }
}
