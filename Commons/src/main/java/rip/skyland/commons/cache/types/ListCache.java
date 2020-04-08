package rip.skyland.commons.cache.types;

import rip.skyland.commons.cache.Cache;

import java.util.List;

public interface ListCache<T> extends Cache<T> {

    List<T> getCache();

    /**
     * Check if the cache contains an element
     *
     * @param element the element
     * @return whether it contains the element
     */
    default boolean contains(T element) {
        return this.getCache().contains(element);
    }

    /**
     * Add an element to the cache
     *
     * @param element the element
     */
    default void add(T element) {
        this.getCache().add(element);
    }

}
