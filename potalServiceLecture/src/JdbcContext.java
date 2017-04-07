import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by masinogns on 2017. 4. 7..
 */
public class JdbcContext {

    private DataSource dataSource;


    public User jdbcContextWithStatementStrategyForGet(StatementStragy statementStragy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = dataSource.getConnection();

            preparedStatement = statementStragy.makeStatement(connection);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e){
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

    public Long jdbcContextWithStatementStrategyForInsert(StatementStragy statementStragy) throws SQLException {
        Connection connection =null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Long id = null;

        try {
            connection = dataSource.getConnection();

            preparedStatement = statementStragy.makeStatement(connection);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("SELECT last_insert_id()");

            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            id = resultSet.getLong(1);
        } catch (SQLException e){
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

    public void jdbcContextWithStatementStrategyForUpdate(StatementStragy statementStragy) throws SQLException {
        Connection connection =null;
        PreparedStatement preparedStatement = null;


        try {
            connection = dataSource.getConnection();

            preparedStatement = statementStragy.makeStatement(connection);
            preparedStatement.executeUpdate();

        } catch (SQLException e){
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

    public void setDataSource(org.springframework.jdbc.datasource.SimpleDriverDataSource dataSource) {
        this.dataSource = dataSource;
    }
}


