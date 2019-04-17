package tbcloud.agent.admin.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: Dmm
 * @date: 2019/4/2 18:50
 */
@Slf4j
@Component
public class RedisClient<T> {

    @Autowired
    private JedisPool jedisPool;

    //增加
    public String set(String key, String value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(key, value);
        } catch (Exception e){
            e.printStackTrace();
            log.info(e.getMessage(),e);
            return null;
        }finally {
            //返还到连接池
            jedis.close();
        }
    }

    public String get(String key) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } catch (Exception e){
            e.printStackTrace();
            log.info(e.getMessage(),e);
            return null;
        }finally {
            //返还到连接池
            jedis.close();
        }
    }

    public Long setObj(String key, T value){
        Jedis jedis = null;
        try {
            Set<T> set = new HashSet<T>();
            set.add(value);
            jedis = jedisPool.getResource();
            return jedis.sadd(key, String.valueOf(set));
        } catch (Exception e){
            log.info(e.getMessage(),e);
            e.printStackTrace();
            return null;
        }finally {
            //返还到连接池
            jedis.close();
        }
    }


    //set
    public Long sAdd(String key, String... members){
        Jedis jedis = null;
        try {
            jedis=jedisPool.getResource();
            return jedis.sadd(key, members);
        }catch (Exception e){
            e.printStackTrace();
            log.info(e.getMessage(),e);
            return null;
        }finally {

            if(jedis!=null){
                jedis.close();
            }
        }
    }
    //失效
    public Long expire(String key, int seconds) {
        Jedis jedis=null;

        try {
            jedis=jedisPool.getResource();
            return jedis.expire(key,seconds);
        }catch (Exception e){
            e.printStackTrace();
            log.info(e.getMessage(),e);
            return null;
        }finally {

            if(jedis!=null){
                jedis.close();
            }
        }

    }

    /**
     * 删除
     * @param key
     * @return
     */
    public Long del(String key) {
        Jedis jedis=null;

        try {
            jedis=jedisPool.getResource();
            return jedis.del(key);
        }catch (Exception e){
            e.printStackTrace();
            log.info(e.getMessage(),e);
            return null;
        }finally {

            if(jedis!=null){
                jedis.close();
            }
        }
    }

    public Long ttl(String key) {
        Jedis jedis=null;

        try {
            jedis=jedisPool.getResource();
            return jedis.ttl(key);
        }catch (Exception e){
            e.printStackTrace();
            log.info(e.getMessage(),e);
            return null;
        }finally {

            if(jedis!=null){
                jedis.close();
            }
        }

    }
}
