import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Created by masinogns on 2017. 3. 31..
 */
public class GetUserStatementStragy implements StatementStragy {

    @Override
    public PreparedStatement makeStatement(Object object, Connection connection) throws SQLException {
        Long id = (Long) object;
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from user where id = ?");
        preparedStatement.setLong(1,id);

        return preparedStatement;
    }
}
