package logik;

import logik.game.TicTacToe;

import java.util.Scanner;

public class Menu {
    public void showMenu() {
        System.out.println("Добро пожаловать в игру крестики нолики!");
        Scanner scanner = new Scanner(System.in);
        TicTacToe ticTacToe = new TicTacToe();
        for (;;) {
            ticTacToe.runGame();
            System.out.println("Сыграть ещё?");
            System.out.println(" 1. да \n 2. нет");
            if (1 != scanner.nextInt()) {
                break;
            }
        }
        System.out.println("Спасибо за игру!");
    }
}
