package assignment1;
import java.util.Scanner;

class Prompt {
    private static Scanner s = new Scanner(System.in);
    
    public static String input(String msg){
        System.out.println(msg+": ");
        return s.nextLine();
    }

    public static void say(String msg){
        System.out.println(msg);
    }
}
