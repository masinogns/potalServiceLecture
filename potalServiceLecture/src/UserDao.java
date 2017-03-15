import java.sql.*;

import static java.lang.Class.forName;

/**
 * Created by masinogns on 2017. 3. 15..
 */
public class UserDao {
    public User get(Long id) throws ClassNotFoundException, SQLException {

        // User 어디에있어? Mysql
        // Class 를 로딩해야겠네
        Class.forName("com.mysql.jdbc.Driver");
        // 커넥션을 맺어야겠네
        Connection connection = DriverManager.getConnection("jdbc:mysql://113.198.162.186/test","root","as0109247");
        // 쿼리를 만들어야겠네
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from user where id = ?");
        preparedStatement.setLong(1,id);
        // 쿼리를 실행해야겠네
        ResultSet resultSet = preparedStatement.executeQuery();
        // 실행된 결과를 객체에 매핑해야겠네
        resultSet.next();
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        // 자원들을 해제하고
        resultSet.close();
        preparedStatement.close();
        connection.close();
        // 결과를 리턴해야겠네

        return user;
    }
}
