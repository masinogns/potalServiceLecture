import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by masinogns on 2017. 3. 24..
 */
@Configuration
public class DaoFactory {

    @Bean
    public UserDao getUserDao() {
        return new UserDao(getConnectionMaker());
    }

    @Bean
    public ConnectionMaker getConnectionMaker(){
        return new JejuConnectionMaker();
    }
}
