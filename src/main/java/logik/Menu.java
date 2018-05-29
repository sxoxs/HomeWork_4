package logik;

import logik.game.Game;

import java.util.Scanner;

public class Menu {
    public void showMenu() {
        System.out.println("Добро пожаловать в игру крестики нолики!");
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();
        for (;;) {
            game.runGame();
            System.out.println("Сыграть ещё?");
            System.out.println(" 1. да \n 2. нет");
            if (1 != scanner.nextInt()) {
                break;
            }
        }
        System.out.println("Спасибо за игру!");
    }
}
