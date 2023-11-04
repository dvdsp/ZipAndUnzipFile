package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
// lop co so tao menu
public abstract class Menu<T> {

    protected String title;
    protected ArrayList<T> list = new ArrayList<>();

    public Menu() { 
    }

    public Menu(String title, String[] mc) {
        this.title = title;
        for (String item : mc) {
            this.list.add((T) item);
        }
    }

    public void display() {
        System.out.println(title);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("[" + (i + 1) + "]" + ". " + list.get(i));
        }
       
    }

    public int getChoice() {
        Scanner sc = new Scanner(System.in);
        int selection = -1;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Enter selection:      ");
                selection = sc.nextInt();
                if (selection >= 1 && selection <= list.size()) {
                    validInput = true;
                } else {
                    System.out.println("*Invalid choice. Please try again.*");
                }
            } catch (InputMismatchException e) {
                System.out.println("*Invalid input. Please enter a valid number.*");
                sc.next(); // Clear the input buffer(xoa du lieu khong hop khoi bo dem dau vao cua dt Scanner )
            }
        }
        return selection;
    }

    public abstract void execute(int n);// trien khai tu lop con

    // main control threat 
    public void run() {
        int choice;
        while (true) {
            display();
            choice = getChoice();
             if (choice == list.size()) {
                break; 
            } else {
                execute (choice);
            }

        }
    }

    
    
}
