package assignment1;

import java.util.Scanner;

class Pair {
    public int m;
    public int n;

    public Pair(int m, int n) {
        this.m = m;
        this.n = n;
    }

    public String toString() {
        return "Pair<" + m + "," + n + ">";
    }
}

class Point {
    public int m;
    public int n;

    public Point(int m, int n) {
        this.m = m;
        this.n = n;
    }

    public String toString() {
        return String.format("Point[m=%s, n=%s]", m, n);
    }
}

class Prompt {
    private static Scanner s = new Scanner(System.in);

    public static Scanner input(String msg) {
        System.out.println(msg + ": ");
        reset();
        return s;
    }

    public static void reset() {
        s = new Scanner(System.in);
    }

    public static void say(String msg) {
        System.out.println(msg);
    }
}