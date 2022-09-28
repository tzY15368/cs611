//package assignment1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("choose your game, 1 for tiktaktoe and 2 for orderNchaos");
        Scanner s = new Scanner(System.in);
        int g = s.nextInt();
        Boardgame game = g==1?new TikTakToe() : new OrderNChaos();
        game.runGame();
    }
}
