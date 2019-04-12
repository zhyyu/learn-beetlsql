package com.zhyyu.learn.beetlsql;

import java.util.Date;
import java.util.List;

import com.zhyyu.learn.beetlsql.mapper.UserDao;
import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.ConnectionSource;
import org.beetl.sql.core.ConnectionSourceHelper;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.SQLLoader;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.core.query.Query;
import org.beetl.sql.ext.DebugInterceptor;

import com.zhyyu.learn.beetlsql.po.User;

public class Main1 {

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
        
        //使用内置的生成的sql 新增用户，如果需要获取主键，可以传入KeyHolder
        /*
         * User user = new User(); user.setAge(19); user.setName("xiandafu");
         * user.setCreateDate(new Date()); sqlManager.insert(user);
         */
        
        //使用内置sql查询用户
        /*
         * int id = 1; user = sqlManager.unique(User.class,id);
         * System.out.println(user);
         */
        
        //模板更新,仅仅根据id更新值不为null的列
        /*
         * User newUser = new User(); newUser.setId(1); newUser.setAge(20);
         * sqlManager.updateTemplateById(newUser);
         */
        
        // Query查询
        /*
         * Query userQuery = sqlManager.query(User.class); List<User> userList =
         * userQuery.lambda().andEq("name", "xiandafu").select();
         * System.out.println(userList);
         */
         
        
        //使用user.md 文件里的select语句，参考下一节。
        /*User query2 = new User();
        query2.setName("xiandafu");
        List<User> userList2 = sqlManager.select("user.select",User.class,query2);
        System.out.println(userList2);*/

        UserDao userDao = sqlManager.getMapper(UserDao.class);
        List<User> userList3 = userDao.select("xiandafu");
        System.out.println(userList3);

    }

}
