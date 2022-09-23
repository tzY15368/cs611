package assignment1;
import java.util.Scanner;

class Prompt {
    private static Scanner s = new Scanner(System.in);
    
    public static Scanner input(String msg){
        System.out.println(msg+": ");
        return s;
    }

    public static void reset(){
        s = new Scanner(System.in);
    }

    public static void say(String msg){
        System.out.println(msg);
    }
}
