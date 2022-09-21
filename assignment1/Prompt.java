package assignment1;
import java.util.Scanner;

class Prompt {
    public static Scanner s = new Scanner(System.in);
    
    public static String Input(String msg){
        System.out.println(msg+": ");
        return s.nextLine();
    }
}
