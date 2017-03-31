import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by masinogns on 2017. 3. 31..
 */
public interface StatementStragy {
    PreparedStatement makeStatement(Object object, Connection connection) throws SQLException;
}
