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
    T findValue(K key);

}
