import java.sql.*;

import static java.lang.Class.forName;

/**
 * Created by masinogns on 2017. 3. 15..
 */
public class UserDao {
    private ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker){
        this.connectionMaker = connectionMaker;

    }
    public User get(Long id) throws ClassNotFoundException, SQLException {

        Connection connection = connectionMaker.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from user where id = ?");
        preparedStatement.setLong(1,id);

        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return user;
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {


        Connection connection = connectionMaker.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT into user(name, password) VALUES (?,?)");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.executeUpdate();

        preparedStatement = connection.prepareStatement("SELECT last_insert_id()");

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        Long id = resultSet.getLong(1);

        resultSet.close();
        preparedStatement.close();
        connection.close();


        return id;
    }



//    public Connection getConnection() throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.jdbc.Driver");
//        // 커넥션을 맺어야겠네
//        Connection connection = DriverManager.getConnection("jdbc:mysql://113.198.162.186/test?characterEncoding=utf-8","root","as0109247");
//        // 쿼리를 만들어야겠네
//
//        return connection;
//    }
}
