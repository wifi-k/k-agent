//package tbcloud.agent.admin.common;
//
//import java.util.List;
//
///**Redis 操作封装类
// * @author: Dmm
// * @date: 2019/4/2 18:46
// */
//public interface RedisService {
//
//    boolean set(String key, String value);
//
//    String get(String key);
//
//    boolean expire(String key, long expire);
//
//    <T> boolean setList(String key, List<T> list);
//
//    <T> List<T> getList(String key, Class<T> clz);
//
//    long lpush(String key, Object obj);
//
//    long rpush(String key, Object obj);
//
//    String lpop(String key);
//
//    boolean set(final String key, final String value, long expire);
//
//
//    Long incr(String key, long growthLength);
//
//    boolean del(String key);
//}
