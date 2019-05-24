package com.zhyyu.learn.beetlsql.autoincr;

import com.zhyyu.learn.beetlsql.mapper.UserDao;
import com.zhyyu.learn.beetlsql.po.User;
import org.beetl.sql.core.*;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.KeyHolder;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;

import java.util.Date;
import java.util.List;

/**
 * 测试autoincrement
 * <pre>
 *      org.beetl.sql.core.SQLScript#insert(java.lang.Object, org.beetl.sql.core.db.KeyHolder) 执行完插入语句后, 访问
 *     java.sql.Statement#getGeneratedKeys(), 获取自增id
 *     底层应该使用 LAST_INSERT_ID 函数
 *     spring jdbc 中使用方式: org.springframework.jdbc.support.incrementer.MySQLMaxValueIncrementer#getNextKey()
 * </pre>
 *
 * @author juror
 * @datatime 2019/5/24 10:56
 */
public class AutoincrTest {

    public static void main(String[] args) {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/my_db?serverTimezone=Asia/Shanghai&characterEncoding=utf8";
        String userName = "root";
        String password = "root";

        ConnectionSource source = ConnectionSourceHelper.getSimple(driver, url, userName, password);
        DBStyle mysql = new MySqlStyle();
        // sql语句放在classpagth的/sql 目录下
        SQLLoader loader = new ClasspathLoader("/sql");
        // 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的，
        UnderlinedNameConversion nc = new UnderlinedNameConversion();
        // 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况
        SQLManager sqlManager = new SQLManager(mysql, loader, source, nc, new Interceptor[] { new DebugInterceptor() });




        UserDao userDao = sqlManager.getMapper(UserDao.class);
//        List<User> userList3 = userDao.select("xiandafu");
//        System.out.println(userList3);

        User user = new User();
        user.setAge(19);
        user.setName("xiandafu");
        user.setCreateDate(new Date());

        System.out.println(user);
        KeyHolder keyHolder = userDao.insertReturnKey(user);
        System.out.println(user);
        System.out.println(keyHolder);
        System.out.println(keyHolder.getKey());
        System.out.println(keyHolder.getInt());
    }

}
