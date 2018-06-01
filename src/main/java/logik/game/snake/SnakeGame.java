package logik.game.snake;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class SnakeGame {
    public void RunGame()  {
        System.out.println("Добро пожаловать в змейку!");
        SnakeMap snakeMap = new SnakeMap();
        Snake snake = new Snake();
        snakeAlgorithm(snake, snakeMap);
    }

    private void snakeAlgorithm(Snake snake, SnakeMap snakeMap) {
        int[] eatCoordinate = new int[2];
        boolean exitCondition = false;

        for (;;) {
            eatCoordinate = generationEatCoordinate(snake, snakeMap);
            snakeMap.setEatCoordinate(eatCoordinate);
            paintSteepGame(snake, snakeMap);
            for (;;) {//идти пока не скушать или не врезаться
                step(snake, snakeMap);
                if (snake.itsEat(eatCoordinate)){
                    break;
                }
                if (snakeMap.isCrashedWall(snake.getHeadCoordinate()) || snake.isCrashedIntoSelf()) {
                    exitCondition = true;
                    break;
                }
                paintSteepGame(snake, snakeMap);
            }
            if (exitCondition) {
                paintSteepGame(snake, snakeMap);
                System.out.println("Вы проиграли(");
                break;
            }
        }
    }

    private void step(Snake snake, SnakeMap snakeMap) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите куда пойти: ");
        switch (scanner.nextLine().toLowerCase().charAt(0)) {
            case 'd': {
                snake.stepRight();
                break;
            }
            case 'a': {
                snake.stepLeft();
                break;
            }
            case 'w': {
                snake.stepTop();
                break;
            }
            case 's': {
                snake.stepBottom();
                break;
            }
        }
    }

    private int[] generationEatCoordinate(Snake snake, SnakeMap snakeMap) {
        int[] coord = new int[2];
        Random random = new Random();
        for (;;) {
            coord[0] = random.nextInt(snakeMap.getSIZE_MAP() - 1) + 1;
            coord[1] = random.nextInt(snakeMap.getSIZE_MAP() - 1) + 1;
            if (isValidCoordinate(snake, coord)) {
                return coord;
            }
        }
    }

    private boolean isValidCoordinate(Snake snake, int[] coord) {
        for (int j = 0; j < snake.getCountBody(); j++) {
            if (Arrays.equals(coord, snake.getCoordinateList().get(j))) {
                return false;
            }
        }

        return true;
    }


    private void paintSteepGame(Snake snake, SnakeMap snakeMap) {




        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Score: ");
        stringBuffer.append(snake.getCountBody() -1 );
        stringBuffer.append("\n");

        snakeMap.addSnakeOnMap(snake);

        for (char[] array : snakeMap.getMap()) {
            stringBuffer.append(Arrays.toString(array));
            stringBuffer.append("\n");
        }
        stringBuffer.append("Управление: \n");
        stringBuffer.append(" D - вправо; \n");
        stringBuffer.append(" A - влево;\n");
        stringBuffer.append(" W - вверх;\n");
        stringBuffer.append(" S - вниз.\n");

        System.out.println(stringBuffer);
    }
}
