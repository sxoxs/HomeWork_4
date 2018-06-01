package logik.game.tic_tac_toe;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private final char X_MARK = 'X';
    private final char O_MARK = 'O';
    private char humanMark;
    private char computerMark;
    private int markForWin;

    public void runGame() {
        char winMark;
        Scanner scanner = new Scanner(System.in);
        MapTicTacToe mapTicTacToe = new MapTicTacToe();
        System.out.println("Начало игры.");
        System.out.println("Введите размер поля:");
        mapTicTacToe.setMapSize(scanner.nextInt());
        System.out.println("Введите длинну линии для выигрыша");
        markForWin = scanner.nextInt();
        mapTicTacToe.setCountLineForWin(markForWin);

        System.out.println("Выберите чем играть:");
        System.out.println(" 1. Х; \n 2. O.");

        if (1 == scanner.nextInt()) {
            System.out.println("Вы играете Х");
            humanMark = X_MARK;
            computerMark = O_MARK;
        }
        else {
            System.out.println("Вы играете О");
            humanMark = O_MARK;
            computerMark = X_MARK;
        }

        winMark = gameAlgorithm(mapTicTacToe);

        if ('*' == winMark) {
            System.out.println("Ничья, попробуй ещё...");
        }
        else {
            if (humanMark == winMark) {
                System.out.println("Поздравляю, Вы выиграли!!!");
            } else {
                System.out.println("Поражение, в следующий раз повезёт");
            }
        }
    }

    private char gameAlgorithm(MapTicTacToe mapTicTacToe) {
        int[] coordinatStep = new int[2];
        char lastStep = O_MARK;
        Random random = new Random();

        if (random.nextInt(2) == 0) {
            System.out.println("Вы ходите первым");
            lastStep = computerMark;
            for (int i = 0; i < mapTicTacToe.getMap().length; i++) {
                System.out.println(Arrays.toString(mapTicTacToe.getMap()[i]));
            }

        }
        else {
            System.out.println("Компьютер ходит первым");
            lastStep = humanMark;
        }

        for (int i = 0; i < mapTicTacToe.getMap().length* mapTicTacToe.getMap().length; i++) {
             if (lastStep == computerMark) {
                 for (;;){
                 coordinatStep = humanStep();
                    if (mapTicTacToe.isValidCoordinates(coordinatStep[0], coordinatStep[1])
                            && mapTicTacToe.isEmpty(coordinatStep[0], coordinatStep[1])) {
                        mapTicTacToe.setMarkCoordinates(humanMark, coordinatStep[0], coordinatStep[1]);
                        lastStep = humanMark;
                        break;
                    }
                    else {
                        System.out.println("Проверте координаты и повторите");
                    }
                 }
             }
             else {
                 System.out.println("Ход компьютера");

                 coordinatStep = computerStep(mapTicTacToe);

                 mapTicTacToe.setMarkCoordinates(computerMark, coordinatStep[0], coordinatStep[1]);
                 lastStep = computerMark;
             }

            for (int j = 0; j < mapTicTacToe.getSize(); j++) {
                System.out.println(Arrays.toString(mapTicTacToe.getMap()[j]));
            }

            if (isWin(mapTicTacToe, coordinatStep[0], coordinatStep[1])) {
                return lastStep;
            }
        }
        return '*';
    }

    private int[] humanStep() {
        int[] coord = new int[2];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ваш ход");
        System.out.print("х:");
        coord[1] = scanner.nextInt() - 1;
        System.out.println();
        System.out.print("y: ");
        coord[0] = scanner.nextInt() - 1;
        System.out.println();

        return coord;
    }

    private int[] computerStep(MapTicTacToe mapTicTacToe) {
        int[] coord = new int[2];

        coord = screathStep(mapTicTacToe, computerMark);
        if (coord[0] == mapTicTacToe.getSize()) {
            coord = screathStep(mapTicTacToe, humanMark);
        }
        else {

            return coord;
        }

        if (coord[0] == mapTicTacToe.getSize()) {
            for (; ; ) {
                Random random = new Random();
                coord[0] = random.nextInt(mapTicTacToe.getCountLineForWin());
                coord[1] = random.nextInt(mapTicTacToe.getCountLineForWin());
                if (mapTicTacToe.isValidCoordinates(coord[0], coord[1])
                        && mapTicTacToe.isEmpty(coord[0], coord[1])) {

                    return coord;
                }
            }
        }
        else {

            return coord;
        }
    }

    private int[] screathStep(MapTicTacToe mapTicTacToe, char mark) {
        int[] coord = new int[2];

        MapTicTacToe gMap = new MapTicTacToe(mapTicTacToe);
        for (int i = 0; i < gMap.getSize(); i++) {
            for (int j = 0; j < gMap.getSize(); j++) {
                if (gMap.getEMPTY_MARK() == gMap.getMap()[i][j]) {
                    gMap.setMarkCoordinates(mark, i, j);
                    if (isWin(gMap, i, j)) {
                        coord[0] = i;
                        coord[1] = j;
                        return coord;
                    }
                    else {
                        gMap.setMap(mapTicTacToe.getMap());
                    }
                }
            }
        }
        coord[0] = mapTicTacToe.getSize();
        coord[1] = mapTicTacToe.getSize();

        return coord;
    }


    private boolean isWin(MapTicTacToe mapTicTacToe, int x, int y) {
        if ((isHorizontWin(mapTicTacToe.getMap(), x, y)) ||
                (isVerticalWin(mapTicTacToe.getMap(), x, y)) ||
                (isDiagonalWin(mapTicTacToe.getMap(), x, y))) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isDiagonalWin(char[][] map, int x, int y) {
        char markPoint = map[x][y];
        int countPointInLine = 1;

        for (int i = x-1, j = y-1; (i >= 0 && j >= 0); i--, j--) {
            if (markPoint == map[i][j]) {
                countPointInLine++;
            }
            else {
                break;
            }
            if (countPointInLine >= markForWin) {
                return true;
            }
        }
        for (int i = x+1, j = y+1; (i < map.length && j < map.length ) ; i++, j++) {
            if (markPoint == map[i][j]) {
                countPointInLine++;
            }
            else {
                break;
            }
            if (countPointInLine >= markForWin) {
                return true;
            }
        }
        countPointInLine = 1;

        for (int i = x+1, j = y-1; (i < map.length && j >= 0); i++, j--) {
            if (markPoint == map[i][j]) {
                countPointInLine++;
            }
            else {
                break;
            }
            if (countPointInLine >= markForWin) {
                return true;
            }
        }
        for (int i = x-1, j = y+1; (i >= 0 && j < map.length); i--, j++) {
            if (markPoint == map[i][j]) {
                countPointInLine++;
            }
            else {
                break;
            }
            if (countPointInLine >= markForWin) {
                return true;
            }
        }

        return false;
    }

    private boolean isVerticalWin(char[][] map, int x, int y) {
        char markPoint = map[x][y];
        int countPointInLine = 1;
        for (int i = x-1; i >= 0; i--) {
            if (markPoint == map[i][y]) {
                countPointInLine++;
            }
            else {
                break;
            }
            if (countPointInLine >= markForWin) {
                return true;
            }
        }
        for (int i = x+1; i < map.length ; i++) {
            if (markPoint == map[i][y]) {
                countPointInLine++;
            }
            else {
                break;
            }
            if (countPointInLine >= markForWin) {
                return true;
            }
        }

        return false;
    }

    private boolean isHorizontWin(char[][] map, int x, int y) {
        char markPoint = map[x][y];
        int countPointInLine = 1;
        for (int i = y-1; i >= 0; i--) {
            if (markPoint == map[x][i]) {
                countPointInLine++;
            }
            else {
                break;
            }
            if (countPointInLine >= markForWin) {
                return true;
            }
        }
        for (int i = y+1; i < map.length ; i++) {
            if (markPoint == map[x][i]) {
                countPointInLine++;
            }
            else {
                break;
            }
            if (countPointInLine >= markForWin) {
                return true;
            }
        }

        return false;
    }


}
