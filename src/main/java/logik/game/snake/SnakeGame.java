package logik.game.snake;

import java.io.IOException;
import java.util.Arrays;

public class SnakeGame {
    public void RunGame() throws IOException {
        System.out.println("Добро пожаловать в змейку!");
        SnakeMap snakeMap = new SnakeMap();
        Snake snake = new Snake();
        snakeAlgorithm(snake, snakeMap);
    }

    private void snakeAlgorithm(Snake snake, SnakeMap snakeMap) throws IOException {
        int i = 0;
        for (;;) {
            paintSteepGame(snake, snakeMap);
            i++;
            if ( i == 10) {
                break;
            }
        }
    }

    private void paintSteepGame(Snake snake, SnakeMap snakeMap) throws IOException {

        StringBuffer stringBuffer = new StringBuffer();
        System.out.println("\\u001b[2J");
        stringBuffer.append("Score: ");
        stringBuffer.append(snake.getCountBody());
        stringBuffer.append("\n");

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
