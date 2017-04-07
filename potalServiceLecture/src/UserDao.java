
import javax.sql.DataSource;
import java.sql.*;

import static java.lang.Class.forName;

/**
 * Created by masinogns on 2017. 3. 15..
 */

public class UserDao {
    private JdbcContext jdbcContext;

    public User get(Long id) throws ClassNotFoundException, SQLException {
        StatementStragy statementStragy = new GetUserStatementStragy(id);

        return jdbcContext.jdbcContextWithStatementStrategyForGet(statementStragy);
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        StatementStragy statementStragy = new AddUserStatementStragy(user);

        return jdbcContext.jdbcContextWithStatementStrategyForInsert(statementStragy);
    }

    public void update(User user) throws ClassNotFoundException, SQLException {
        StatementStragy statementStragy = new UpdateUserStatementStragy(user);
        jdbcContext.jdbcContextWithStatementStrategyForUpdate(statementStragy);
    }

    public void delete(Long id) throws ClassNotFoundException, SQLException {
        StatementStragy statementStragy = new DeleteUserStatementStragy(id);
        jdbcContext.jdbcContextWithStatementStrategyForUpdate(statementStragy);
    }

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

}
