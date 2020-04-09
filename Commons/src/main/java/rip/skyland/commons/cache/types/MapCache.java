package rip.skyland.commons.cache.types;

import rip.skyland.commons.cache.Cache;

import java.util.Map;

public interface MapCache<K, T> extends Cache<T> {

    Map<K, T> getCache();

    /**
     * Find a value inside of the map
     *
     * @param key the key
     * @return the value
     */
    default T findValue(K key) {
        return this.getCache().get(key);
    }

    /**
     * Add an element to the cache
     *
     * @param key the key/path
     * @param value the value
     */
    default void put(K key, T value) {
        this.getCache().put(key, value);
    }

}
