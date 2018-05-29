package logik.game;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private final char X_MARK = 'X';
    private final char O_MARK = 'O';
    private char humanMark;
    private char computerMark;
    private int markForWin;

    public void runGame() {
        char winMark;
        Scanner scanner = new Scanner(System.in);
        GameMap gameMap = new GameMap();
        System.out.println("Начало игры.");
        System.out.println("Введите размер поля:");
        gameMap.setMapSize(scanner.nextInt());
        System.out.println("Введите длинну линии для выигрыша");
        markForWin = scanner.nextInt();
        gameMap.setCountLineForWin(markForWin);

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

        winMark = gameAlgorithm(gameMap);

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

    private char gameAlgorithm(GameMap gameMap) {
        int[] coordinatStep = new int[2];
        char lastStep = O_MARK;
        Random random = new Random();

        if (random.nextInt(2) == 0) {
            System.out.println("Вы ходите первым");
            lastStep = computerMark;
            for (int i = 0; i < gameMap.getMap().length; i++) {
                System.out.println(Arrays.toString(gameMap.getMap()[i]));
            }

        }
        else {
            System.out.println("Компьютер ходит первым");
            lastStep = humanMark;
        }

        for (int i = 0; i < gameMap.getMap().length*gameMap.getMap().length; i++) {
             if (lastStep == computerMark) {
                 for (;;){
                 coordinatStep = humanStep();
                    if (gameMap.isValidCoordinates(coordinatStep[0], coordinatStep[1])
                            && gameMap.isEmpty(coordinatStep[0], coordinatStep[1])) {
                        gameMap.setMarkCoordinates(humanMark, coordinatStep[0], coordinatStep[1]);
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

                 coordinatStep = computerStep(gameMap);

                 gameMap.setMarkCoordinates(computerMark, coordinatStep[0], coordinatStep[1]);
                 lastStep = computerMark;
             }

            for (int j = 0; j < gameMap.getSize(); j++) {
                System.out.println(Arrays.toString(gameMap.getMap()[j]));
            }

            if (isWin(gameMap, coordinatStep[0], coordinatStep[1])) {
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

    private int[] computerStep(GameMap gameMap) {
        int[] coord = new int[2];

        coord = screathStep(gameMap, computerMark);
        if (coord[0] == gameMap.getSize()) {
            coord = screathStep(gameMap, humanMark);
        }
        else {

            return coord;
        }

        if (coord[0] == gameMap.getSize()) {
            for (; ; ) {
                Random random = new Random();
                coord[0] = random.nextInt(gameMap.getCountLineForWin());
                coord[1] = random.nextInt(gameMap.getCountLineForWin());
                if (gameMap.isValidCoordinates(coord[0], coord[1])
                        && gameMap.isEmpty(coord[0], coord[1])) {

                    return coord;
                }
            }
        }
        else {

            return coord;
        }
    }

    private int[] screathStep(GameMap gameMap, char mark) {
        int[] coord = new int[2];

        GameMap gMap = new GameMap(gameMap);
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
                        gMap.setMap(gameMap.getMap());
                    }
                }
            }
        }

        coord[0] = gameMap.getSize();
        coord[1] = gameMap.getSize();

        return coord;
    }


    private boolean isWin(GameMap gameMap, int x, int y) {
        if ((isHorizontWin(gameMap.getMap(), x, y)) ||
                (isVerticalWin(gameMap.getMap(), x, y)) ||
                (isDiagonalWin(gameMap.getMap(), x, y))) {
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
