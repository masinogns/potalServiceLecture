
import javax.sql.DataSource;
import java.sql.*;

import static java.lang.Class.forName;

/**
 * Created by masinogns on 2017. 3. 15..
 */

public class UserDao {
    private JdbcContext jdbcContext;

    public User get(Long id) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * from user where id = ?";
        Object[] params = new Object[]{id};
        return jdbcContext.queryForObject(id, sql);
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        String sql = "INSERT into user(name, password) VALUES (?,?)";
        Object[] params = new Object[]{user.getName(), user.getPassword()};


        return jdbcContext.insert(sql, params);
    }

    public void update(User user) throws ClassNotFoundException, SQLException {
        String sql = "update user set name = ?, password = ? where id = ?";
        Object[] params = new Object[]{user.getName(),user.getPassword(), user.getId()};


        jdbcContext.update(sql, params);
    }


    public void delete(Long id) throws ClassNotFoundException, SQLException {
        String sql = "delete from user where id = ?";
        Object[] params = new Object[] {id};

        jdbcContext.update(sql, params);
    }


    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

}
