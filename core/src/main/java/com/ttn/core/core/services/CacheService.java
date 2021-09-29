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
     * @param cacheName the cache name
     * @param itemId    the item id
     * @return the from cache
     */
    Object getFromCache(final String cacheName, final String itemId);

    /**
     * Gets from cache.
     *
     * @param <T>          the type parameter
     * @param expectedType the expected type
     * @param cacheName    the cache name
     * @param id           the id
     * @return the from cache
     */
    <T> Optional<T> getFromCache(final Class<T> expectedType, final String cacheName, final String id);

    /**
     * Clean cache.
     *
     * @param cacheName the cache name
     */
    void cleanCache(final String cacheName);

}
