import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by masinogns on 2017. 3. 31..
 */
public class GetUserStatementStragy implements StatementStragy {

    private Long id;

    public GetUserStatementStragy(Long id) {
        this.id = id;
    }

    @Override
    public PreparedStatement makeStatement(Connection connection) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from user where id = ?");
        preparedStatement.setLong(1,id);

        return preparedStatement;
    }
}
