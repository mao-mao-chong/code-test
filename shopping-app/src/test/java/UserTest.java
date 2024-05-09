import org.com.bmw.dao.ActivityDao;
import org.com.bmw.dao.ActivityEnrollDao;
import org.com.bmw.dao.ProductDao;
import org.com.bmw.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
public class UserTest{
    @Autowired
    private UserController userController;
    @Autowired
    ProductDao productDao;
    @Autowired
    ActivityDao activityDao;
    @Autowired
    ActivityEnrollDao activityEnrollDao;
    @Test
    public void testQuery() {
        ReturnMsg record = new ReturnMsg();
        record = userController.selectUserByPrimaryKey(1L);
        System.out.println(record);
    }
    @Test
    public void insertProduct(){
        for(int i=0;i<100000;i++){
            Product product = new Product();
            product.setStoreId(3L);
            product.setProductName("测试"+i);
            product.setProductInventory(10000L);
            product.setProductPrice(new BigDecimal("5"));
            product.setProductTypeId(4L);
            product.setPictureUrl("暂无");
            product.setDelFlag(0);
            product.setCreateTime(new Date());
            product.setUpdateTime(new Date());
            product.setProductIntroduce("测试插入");
            productDao.insertProduct(product);
        }

    }

    @Test
    public void insertActivity(){
        for(int i=0;i<100000;i++){
            Activity activity = new Activity();
            activity.setActivityName("测试"+1);
            activity.setActivityStartTimeString("2024-5-1 00:00:00");
            activity.setActivityEndTimeString("2024-6-1 00:00:00");
            activity.setCreateTime(new Date());
            activity.setUpdateTime(new Date());
            activity.setDelFlag(0);
            activityDao.insertActivity(activity);
        }

    }
    @Test
    public void insertActivityEnroll(){
        for(int i=0;i<1000000;i++){
            ActivityEnroll activityEnroll = new ActivityEnroll();
            activityEnroll.setActivityName("测试"+1);
            activityEnroll.setStoreName("注册商户1");
            activityEnroll.setStoreId(3L);
            activityEnroll.setProductId((long)i);
            activityEnroll.setProductName("卫龙辣条250g");
            activityEnroll.setActivityId((long)i);
            activityEnroll.setActivityName("测试"+1);
            activityEnroll.setProductPrice(new BigDecimal("1.5"));
            activityEnroll.setProductInventory(100L);
            activityEnroll.setCreateTime(new Date());
            activityEnroll.setUpdateTime(new Date());
            activityEnroll.setDelFlag(0);
            activityEnrollDao.insertActivityEnroll(activityEnroll);
        }

    }
}
