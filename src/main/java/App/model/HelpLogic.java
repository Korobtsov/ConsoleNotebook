package App.model;

import java.io.*;
import App.Dao.*;

public class HelpLogic {

    public void printAllMenuItems() {
        for (MenuItems a : MenuItems.values()) {
            System.out.println(a.getName());
        }
    }
    public void mainMenu(){
        printAllMenuItems();
        System.out.println("\nPlease, insert numbers from 0 to 3");
        String input = readConsoleInput();
        checkInput(input);
    }

    public String readConsoleInput(){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        try {
            input = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    public void checkInput(String input){
        switch (input) {
            case "0":
                System.out.println("Bye bye");
                break;
            case "1":
                User user = new User();
                user.addUser();
                break;
            case "2":
                searchUserByName();
                break;
            case "3":
                searchUserByEmail();
                break;
            default:
                System.out.println("Should be digits from 0 to 3 \n");
                mainMenu();
        }
    }

    public void searchUserByName(){
        Dao<User> userdao = new UserDao();
        System.out.println("Please, insert Name");
        User user = userdao.getByName(readConsoleInput());
        if (user != null){
            System.out.println("Success! \n");
            System.out.println(user);
            mainMenu();
        } else {
            System.out.println("Nothing found");
            searchUserByName();
        }
    }

    public void searchUserByEmail(){
        Dao<User> userdao = new UserDao();
        System.out.println("Please, insert Email");
        User user = userdao.getByEmail(readConsoleInput());
        if (user != null){
            System.out.println("Success! \n");
            System.out.println(user);
            mainMenu();
        } else {
            System.out.println("Nothing found");
            searchUserByEmail();
        }
    }
}
