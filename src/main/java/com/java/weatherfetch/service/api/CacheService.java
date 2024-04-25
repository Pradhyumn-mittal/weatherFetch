package com.java.weatherfetch.service.api;

public interface CacheService {
  <T> T findCacheByKey(String key, Class<T> clazz);

  Boolean createCache(String key, Object value, long expirySeconds);
}
