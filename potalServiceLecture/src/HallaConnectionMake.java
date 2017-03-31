import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by masinogns on 2017. 3. 24..
 */
public class HallaConnectionMake implements ConnectionMaker {
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://113.198.165.184/test?characterEncoding=utf-8", "root", "as0109247");

    }
}
