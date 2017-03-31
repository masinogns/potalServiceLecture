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

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = connectionMaker.getConnection();

            StatementStragy statementStragy = new GetUserStatementStragy();
            preparedStatement = statementStragy.makeStatement(id, connection);
//            preparedStatement = connection.prepareStatement("SELECT * from user where id = ?");
//            preparedStatement.setLong(1,id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            throw e;
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }

            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
        }




        return user;
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        Connection connection =null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Long id = null;

        try {
            connection = connectionMaker.getConnection();

            StatementStragy statementStragy = new AddUserStatementStragy();
            preparedStatement = statementStragy.makeStatement(user, connection);
//            preparedStatement = connection.prepareStatement("INSERT into user(name, password) VALUES (?,?)");
//            preparedStatement.setString(1, user.getName());
//            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("SELECT last_insert_id()");

            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            id = resultSet.getLong(1);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            throw e;
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }

            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
        }



        return id;
    }

    public void update(User user) throws ClassNotFoundException, SQLException {

        Connection connection =null;
        PreparedStatement preparedStatement = null;


        try {
            connection = connectionMaker.getConnection();

            StatementStragy statementStragy = new UpdateUserStatementStragy();
            preparedStatement = statementStragy.makeStatement(user, connection);
//            preparedStatement = connection.prepareStatement("update user set name = ?, password = ? where id = ?");
//            preparedStatement.setString(1, user.getName());
//            preparedStatement.setString(2, user.getPassword());
//            preparedStatement.setLong(3,user.getId());
            preparedStatement.executeUpdate();

        }catch (ClassNotFoundException e){
            e.printStackTrace();
            throw e;
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        } finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    public void delete(Long id) throws ClassNotFoundException, SQLException {

        Connection connection =null;
        PreparedStatement preparedStatement = null;


        try {

            connection = connectionMaker.getConnection();

            StatementStragy statementStragy = new DeleteUserStatementStragy();
            preparedStatement = statementStragy.makeStatement(id, connection);
//            preparedStatement = connection.prepareStatement("delete from user where id = ?");
//            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        }catch (ClassNotFoundException e){
            e.printStackTrace();
            throw e;
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        } finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
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
