import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        echo();
    }
    public static void echo(){
        String border = "******************************************************";
        String greeting = "Hi! My name is Alfred. I aim to please.";
        System.out.println(border + "\n  " + greeting + "\n" + border);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        List<String> added = new ArrayList<>();
        while(!input.equals("bye")){
            if(input.equals("list")){
                System.out.println(border);
                for (int i = 1; i <= added.size(); i++) {
                    System.out.println("  " + (i) + ". " + added.get(i - 1));
                }
                System.out.println(border);
                input = sc.nextLine();
            }else {
                added.add(input);
                System.out.println(border + "\n  added: " + input + "\n" + border);
                input = sc.nextLine();
            }
        }
        System.out.println("Goodbye. I hope I was useful. See you again.");
        sc.close();
    }
}
