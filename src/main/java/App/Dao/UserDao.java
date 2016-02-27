package App.Dao;

import App.model.User;
import java.sql.*;
import static App.model.DataBaseHelper.*;

public class UserDao implements  Dao<User> {
    public final static String SQL_INSERT = "INSERT INTO users(name, age, email) VALUES ( ?, ?, ?)";
    public final static String SQL_SELECT_BY_NAME = "SELECT * FROM users WHERE NAME = ?";
    public final static String SQL_SELECT_BY_EMAIL = "SELECT * FROM users WHERE EMAIL = ?";

    @Override
    public User create(User user) {
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getByName(String name) {
        String sql = SQL_SELECT_BY_NAME;
        return creatingUserFromSQL(sql, name);
    }

    @Override
    public User getByEmail(String email) {
        String sql = SQL_SELECT_BY_EMAIL;
        return creatingUserFromSQL(sql, email);
    }

    private User creatingUserFromSQL(String sql, String columnName){
        User usr = null;
        try(Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD)){
            PreparedStatement pt = connection.prepareStatement(sql);
            pt.setString(1, columnName);
            usr = fillUserAttributes(usr, pt);
            connection.close();
        } catch( SQLException e) {
            e.printStackTrace();
        }
        return usr;
    }

    private User fillUserAttributes(User usr, PreparedStatement pt) throws SQLException{
        ResultSet rs = pt.executeQuery();
        if (rs.next()){
            usr = new User();
            usr.setId(rs.getInt("id"));
            usr.setName(rs.getString("name"));
            usr.setAge(rs.getInt("age"));
            usr.setEmailFromDB(rs.getString("email"));
        }
        return usr;
    }
}
