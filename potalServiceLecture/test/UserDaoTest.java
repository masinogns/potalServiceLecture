
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by masinogns on 2017. 3. 15..
 */
public class UserDaoTest {

    UserDao userDao;

    @Before
    public void setup(){
        ApplicationContext context = new GenericXmlApplicationContext("daoFactory.xml");
        userDao = context.getBean("userDao", UserDao.class);

    }

    @Test
    public void getJejnu() throws SQLException, ClassNotFoundException {
        Long id = 10L;
        String name = "이익전";
        String password = "1234";



        User user = userDao.get(id);

        // asserThat아 id랑 가져온 아이디랑 맞는지 알려줘
        assertThat(id, is(user.getId()));
        assertThat(name, is(user.getName()));
        assertThat(password, is(user.getPassword()));
    }

    @Test
    public void addJeju() throws SQLException, ClassNotFoundException {
        User user = new User();

        String name = "kihoon";
        String password = "0101010";

        user.setName(name);
        user.setPassword(password);


        Long id = userDao.add(user);
        User resultUser = userDao.get(id);

        assertThat(id, is(resultUser.getId()));
        assertThat(name, is(resultUser.getName()));
        assertThat(password, is(resultUser.getPassword()));
    }

    @Test
    public void getHalla() throws SQLException, ClassNotFoundException {
        Long id = 10L;
        String name = "이익전";
        String password = "1234";

        UserDao userDao = new UserDao(new HallaConnectionMake());
        User user = userDao.get(id);

        // asserThat아 id랑 가져온 아이디랑 맞는지 알려줘
        assertThat(id, is(user.getId()));
        assertThat(name, is(user.getName()));
        assertThat(password, is(user.getPassword()));

    }

    @Test
    public void addHalla() throws SQLException, ClassNotFoundException {
        User user = new User();

        String name = "kihoon";
        String password = "0101010";

        user.setName(name);
        user.setPassword(password);

        UserDao userDao = new UserDao(new HallaConnectionMake());
        Long id = userDao.add(user);
        User resultUser = userDao.get(id);

        assertThat(id, is(resultUser.getId()));
        assertThat(name, is(resultUser.getName()));
        assertThat(password, is(resultUser.getPassword()));
    }
}
