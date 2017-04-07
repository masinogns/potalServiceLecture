
import javax.sql.DataSource;
import java.sql.*;

import static java.lang.Class.forName;

/**
 * Created by masinogns on 2017. 3. 15..
 */

public class UserDao {
    private JdbcContext jdbcContext;

    public User get(Long id) throws ClassNotFoundException, SQLException {
//        StatementStragy statementStragy = new GetUserStatementStragy(id);
        StatementStragy statementStragy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from user where id = ?");
            preparedStatement.setLong(1,id);

            return preparedStatement;
        };


        return jdbcContext.jdbcContextWithStatementStrategyForGet(statementStragy);
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        StatementStragy statementStragy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into user(name, password) VALUES (?,?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());

            return preparedStatement;
        };

        return jdbcContext.jdbcContextWithStatementStrategyForInsert(statementStragy);
    }

    public void update(User user) throws ClassNotFoundException, SQLException {
        StatementStragy statementStragy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("update user set name = ?, password = ? where id = ?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3,user.getId());

            return preparedStatement;
        };

        jdbcContext.jdbcContextWithStatementStrategyForUpdate(statementStragy);
    }

    public void delete(Long id) throws ClassNotFoundException, SQLException {
        StatementStragy statementStragy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from user where id = ?");
            preparedStatement.setLong(1, id);
            return preparedStatement;
        };
        jdbcContext.jdbcContextWithStatementStrategyForUpdate(statementStragy);
    }

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

}
