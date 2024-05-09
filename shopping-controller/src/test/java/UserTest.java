import org.com.bmw.Main;
import org.com.bmw.controller.UserController;
import org.com.bmw.dao.ActivityDao;
import org.com.bmw.dao.ActivityEnrollDao;
import org.com.bmw.dao.ProductDao;
import org.com.bmw.dao.UserDao;
import org.com.bmw.model.*;
import org.com.bmw.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    @Autowired
    RedisUtil redisUtil;
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
//            activity.setActivityStartTimeString("2024-5-1 00:00:00");
//            activity.setActivityEndTimeString("2024-6-1 00:00:00");
            activity.setCreateTime(new Date());
            activity.setUpdateTime(new Date());
            activity.setDelFlag(0);
            activityDao.insertActivity(activity);
        }

    }
    @Test
    public void insertActivityEnroll(){

//        redisUtil.add(123,234,11);
        redisUtil.add("1234","345",22);
        redisUtil.add("1234","14",13);
        redisUtil.add("1234","22",25);
        Set<Object> ids = redisUtil.range("1234",0L,4L);
        List<Object> list = new ArrayList<Object>(ids);
        for(Object id : list){
            System.out.println(id);
        }

    }
}
