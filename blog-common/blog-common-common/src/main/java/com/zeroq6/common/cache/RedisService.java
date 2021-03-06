package com.zeroq6.common.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 连接池和连接池配置整个应用只有一份，每个线程只持有一个资源，线程结束时自动关闭
 *
 * Created by yuuki asuna on 2016/10/20.
 */
/**
 * @author icgeass@hotmail.com
 * @date 2017-05-17
 */
public class RedisService implements CacheServiceApi {


    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 写入成功redis返回
     */
    private final static String OK = "OK";

    private static ThreadLocal<Jedis> jedisThreadLocal = new ThreadLocal<Jedis>();

    private static JedisPoolConfig jedisPoolConfig;

    private static JedisPool jedisPool;


    private int expiredInSeconds = DEFAULT_EXPIRED_IN_SECONDS;

    private String host = "127.0.0.1";

    private int port = 6379;

    private int minIdle = 5;
    private int maxIdle = 8;

    private int maxTotal = 40;

    private int maxWaitMillis = 5 * 1000;


    public RedisService() {

    }

    public void setExpiredInSeconds(int expiredInSeconds) {
        this.expiredInSeconds = expiredInSeconds;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public void setMaxWaitMillis(int maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    /**
        * 获得一个连接实例
        *
        * @return
        */
    public Jedis getResource() {
        if (null == jedisPool) {
            jedisPoolConfig = new JedisPoolConfig();
            // 连接池配置
            jedisPoolConfig.setMinIdle(minIdle);
            jedisPoolConfig.setMaxIdle(maxIdle);
            jedisPoolConfig.setMaxTotal(maxTotal);
            jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
            // 如果线程池满阻塞则等待
            jedisPoolConfig.setBlockWhenExhausted(true);
            // 测试实例是否可用
            jedisPoolConfig.setTestOnBorrow(true);
            jedisPoolConfig.setTestOnCreate(true);
            jedisPoolConfig.setTestOnReturn(true);
            // 扫描空闲实例是否有效，如果验证失败则销毁，setTimeBetweenEvictionRunsMillis必须大于0才有效
            jedisPoolConfig.setTestWhileIdle(true);
            // 扫描空闲实例是否有效的时间间隔，毫秒
            jedisPoolConfig.setTimeBetweenEvictionRunsMillis(120 * 1000);
            jedisPool = new JedisPool(jedisPoolConfig, host, port);
        }
        if (null == jedisThreadLocal.get()) {
            final Jedis jedis = jedisPool.getResource();
            final Thread targetThread = Thread.currentThread();
            jedisThreadLocal.set(jedis);
            new Thread() {
                @Override
                public void run() {
                    try {
                        targetThread.join();
                    } catch (Exception e) {
                        logger.error("加入线程阻塞失败", e);
                    } finally {
                        try {
                            returnResource(jedis);
                        } catch (Exception e) {
                            logger.error("归还redis连接失败", e);
                        }
                    }
                }
            }.start();
        }
        return jedisThreadLocal.get();
    }

    /**
     * 归还连接实例
     */
    public void returnResource(Jedis jedis) {
        // 方法jedisPool.returnResource(jedis); 已被弃用
        if (null != jedis) {
            jedis.close();
        }
    }

    /**
     * 当系统关闭时调用，可在spring中配置
     *
     */
    public void destroy() {
        jedisPool.destroy();
    }


    @Override
    public String get(String key) {
        return getResource().get(key);
    }

    @Override
    public boolean set(String key, String value) throws Exception {
        return set(key, value, expiredInSeconds);
    }

    @Override
    public boolean set(String key, String value, int expiredInSeconds) throws Exception {
        return OK.equals(getResource().setex(key, expiredInSeconds, value));
    }


    @Override
    public boolean remove(String key) {
        return getResource().del(key) > 0;
    }

    @Override
    public Long hset(String key, String field, String value) throws Exception {
        throw new RuntimeException("No implements");
    }

    @Override
    public Long hset(String key, String field, String value, int expiredInSeconds) throws Exception {
        throw new RuntimeException("No implements");
    }

    @Override
    public String hget(String key, String field) throws Exception {
        throw new RuntimeException("No implements");
    }

    @Override
    public Long hdel(String key, String... fields) throws Exception {
        throw new RuntimeException("No implements");
    }

}
