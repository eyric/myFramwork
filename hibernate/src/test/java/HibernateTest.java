import com.levin.framework.hibernate.core.Session;
import com.levin.framework.hibernate.model.User;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class HibernateTest {
    @Test
    public void test() throws SQLException {
        Session session = new Session();
        User user = new User(1L, "levin", "123456", 18);
        session.save(user);
    }
}
