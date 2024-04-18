import org.com.bmw.Main;
import org.com.bmw.controller.UserController;
import org.com.bmw.dao.UserDao;
import org.com.bmw.model.ReturnMsg;
import org.com.bmw.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
public class UserTest{
    @Autowired
    private UserController userController;
    @Test
    public void testQuery() {
        ReturnMsg record = new ReturnMsg();
        record = userController.selectUserByPrimaryKey(1L);
        System.out.println(record);
    }
}
