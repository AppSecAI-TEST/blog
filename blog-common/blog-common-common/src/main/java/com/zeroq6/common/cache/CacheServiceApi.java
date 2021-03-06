package com.zeroq6.common.cache;

/**
 * @author icgeass@hotmail.com
 * @date 2017-05-17
 */
public interface CacheServiceApi {


    int DEFAULT_EXPIRED_IN_SECONDS = 60 * 60 * 24 * 30;


    String get(String key) throws Exception;

    boolean set(String key, String value) throws Exception;

    boolean set(String key, String value, int expiredInSeconds) throws Exception;

    boolean remove(String key) throws Exception;

    Long hset(String key, String field, String value) throws Exception;

    Long hset(String key, String field, String value, int expiredInSeconds) throws Exception;

    String hget(String key, String field) throws Exception;

    Long hdel(String key, String... fields) throws Exception;

}
