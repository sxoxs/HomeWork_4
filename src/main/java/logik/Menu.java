package logik;

import logik.game.snake.SnakeGame;
import logik.game.tic_tac_toe.TicTacToe;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    public void showMenu() {
        System.out.println("Добро пожаловать!");
        Scanner scanner = new Scanner(System.in);
        for (;;) {
            System.out.println("Выберите игру:");
            System.out.println("1. Крестики нолики");
            System.out.println("2. Змейка");

            switch (scanner.nextInt()){
                case 1:{
                    TicTacToe ticTacToe = new TicTacToe();
                    ticTacToe.runGame();
                    break;
                }
                case 2: {
                    SnakeGame snakeGame = new SnakeGame();
                    snakeGame.RunGame();
                    break;
                }
                default:{
                    System.out.println("Повторите выбор, вы что то не то нажали");
                }
            }

            System.out.println("Сыграть ещё?");
            System.out.println(" 1. да \n 2. нет");
            if (1 != scanner.nextInt()) {
                break;
            }
        }
        System.out.println("Спасибо за игру!");
    }
}
