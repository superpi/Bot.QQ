package ninja.skyrocketing.bot.fuyao.util;

import net.mamoe.mirai.message.data.Message;
import ninja.skyrocketing.bot.fuyao.pojo.group.GroupMessage;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author skyrocketing Hong
 * @date 2020-11-28 028 15:51:39
 */

public class InvokeUtil {
    public static Message runByInvoke(String str, GroupMessage groupMessage) throws Exception {
        String[] className = str.split("\\.");
        Class<?> clz = Class.forName("ninja.skyrocketing.bot.fuyao." + className[0] + "." + className[1]);
        Method method = clz.getMethod(className[2], GroupMessage.class);
        Constructor<?> constructor = clz.getConstructor();
        Object object = constructor.newInstance();
        return (Message) method.invoke(object, groupMessage);
    }
}
