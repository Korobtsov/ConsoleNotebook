package App.model;

import App.Dao.UserDao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
            "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);

    private int id;
    private int age;
    private String name;
    private String email;

    public User(){

    }

    public void setAge(int age) {
        if(0<age && age<100){
        this.age = age;
    } else {
            System.out.println("Age should be more than 0 and less than 100");
            String input = callInput();
            setAge(tryParseToInt(input));
        }
    }

    public void setName(String name) {
        if(name.length()>3 && name.length()< 100){
        this.name = name;
        } else {
            System.out.println("Name should be more than 3 digits and less than 100");
            setName(callInput());
        }
    }

    public boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    public void setEmail(String email) {
        if(email.length() == 0 || validate(email)){
            this.email = email;
        } else {
            System.out.println("Please, insert valid Email");
            setEmail(callInput());
        }
    }

    public void setEmailFromDB(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        if(email == null){
            email = "";
        }
        return email;
    }

    public void addUser(){
        User user = new User();
        UserDao userdao = new UserDao();
        System.out.println("Please, insert Name");
        user.setName(callInput());
        System.out.println("Please, insert Age");
        user.setAge(tryParseToInt(callInput()));
        System.out.println("Please, insert Email");
        user.setEmail(callInput());
        userdao.create(user);
        System.out.println("User added \n");
        HelpLogic helpLogic = new HelpLogic();
        helpLogic.mainMenu();
    }

    public String callInput(){
        HelpLogic helpLogic = new HelpLogic();
        return helpLogic.readConsoleInput();
    }

    public Integer tryParseToInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            System.out.println("Only digits allowed, please, try again");
            String input = callInput();
            return tryParseToInt(input);
        }
    }

    @Override
    public String toString() {
        if(email.length() == 0) {email = "No email";}
        return "User name = " + name +
                ", age = " + age +
                ", email = " + email + '\n';
    }
}
