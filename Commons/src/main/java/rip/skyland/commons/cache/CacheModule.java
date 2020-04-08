package rip.skyland.commons.cache;

import rip.skyland.commons.module.Module;

import java.util.ArrayList;
import java.util.List;

public class CacheModule extends Module {

    private final List<Cache<?>> cacheList = new ArrayList<>();

    @Override
    public void enable() {
        super.enable();
    }

    /**
     * Add a cache to the cache list
     *
     * @param cache the cache
     */
    public void addCache(Cache<?> cache) {
        this.cacheList.add(cache);
    }

    /**
     * Find a Cache by Cache Type of Cache Class
     *
     * @param clazz the class of the type or the cache
     * @param <T> the type
     * @return the cache
     */
    public <T> T findCache(Class<T> clazz) {
        return clazz.cast(cacheList.stream()
                .filter(cache -> cache.getClass().equals(clazz) || cache.getType().equals(clazz))
                .findFirst().orElse(null));
    }

}
