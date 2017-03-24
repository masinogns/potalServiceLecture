import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by masinogns on 2017. 3. 24..
 */
public interface ConnectionMaker {
    public Connection getConnection() throws ClassNotFoundException, SQLException ;
}
