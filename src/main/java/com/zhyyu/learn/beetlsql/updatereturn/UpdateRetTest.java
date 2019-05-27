package com.zhyyu.learn.beetlsql.updatereturn;

import com.zhyyu.learn.beetlsql.SQLManagerBuilder;
import com.zhyyu.learn.beetlsql.po.User;
import org.beetl.sql.core.SQLManager;

/**
 * update 返回值为where 条件match 数, 不为实际更新数
 * <pre>
 *     update 符合where 条件, 但update 值实际为跟新, 则 CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 不跟新
 * </pre>
 *
 * @author juror
 * @datatime 2019/5/27 18:24
 */
public class UpdateRetTest {

    public static void main(String[] args) {
        SQLManager sqlManager = SQLManagerBuilder.build();

        User newUser = new User();
        newUser.setName("xiandafu2");

        int updateCount = sqlManager.update("user.updateAllName", newUser);
        System.out.println(updateCount);
    }

}
