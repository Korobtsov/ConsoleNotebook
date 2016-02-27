package App;

import App.model.DataBaseHelper;
import App.model.HelpLogic;

import java.io.*;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {
        DataBaseHelper.init();
        HelpLogic helpLogic = new HelpLogic();
        helpLogic.mainMenu();
    }
}
