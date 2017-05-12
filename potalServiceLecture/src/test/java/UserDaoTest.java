
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

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
    public void get() throws SQLException, ClassNotFoundException {
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
    public void add() throws SQLException, ClassNotFoundException {
        String name = "kihoon";
        String password = "0101010";
        User user = new User();


        user.setName(name);
        user.setPassword(password);


        Long id = userDao.add(user);
        User resultUser = userDao.get(id);

        assertThat(id, is(resultUser.getId()));
        assertThat(name, is(resultUser.getName()));
        assertThat(password, is(resultUser.getPassword()));
    }

   @Test
    public void update() throws SQLException, ClassNotFoundException {
        String name = "kihoon";
        String password = "0101010";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        Long id = userDao.add(user);

        String changedName = "kkkkkk";
        String changedPassword = "11111";

        user.setId(id);
        user.setName(changedName);
        user.setPassword(changedPassword);

        userDao.update(user);

        User changedUser = userDao.get(id);

        assertThat(id, is(changedUser.getId()));
        assertThat(changedName, is(changedUser.getName()));
        assertThat(changedPassword, is(changedUser.getPassword()));

   }

   @Test
   public void delete() throws SQLException, ClassNotFoundException {
       String name = "kihoon";
       String password = "0101010";
       User user = new User();


       user.setName(name);
       user.setPassword(password);


       Long id = userDao.add(user);

       userDao.delete(id);
       User deleteUser= userDao.get(id);
       assertThat(deleteUser, nullValue());
   }
}
