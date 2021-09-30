package com.ttn.core.core.services;

import java.util.Optional;

/**
 * The interface Cache service.
 */
public interface CacheService {

    /**
     * Add to cache.
     *
     * @param cacheName the cache name
     * @param item      the item
     */
    void addToCache(final String cacheName, final Object item);

    /**
     * Gets from cache.
     *
     * @param itemId the item id
     * @return the from cache
     */
    Object getFromCache(final String itemId);

    /**
     * Gets from cache.
     *
     * @param <T>          the type parameter
     * @param expectedType the expected type
     * @param id           the id
     * @return the from cache
     */
    <T> Optional<T> getFromCache(final Class<T> expectedType, final String id);

    /**
     * Clean cache.
     *
     */
    void cleanAllCache();


    /**
     * Is cache exist boolean.
     *
     * @param key the key
     * @return the boolean
     */
    boolean isCacheExist(String key);

}
