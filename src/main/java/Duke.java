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
        while(!input.equals("bye")){
            System.out.println(border + "\n  " + input + "\n" + border);
            input = sc.nextLine();
        }
        System.out.println("Goodbye. I hope I was useful. See you again.");
        sc.close();
    }
}
