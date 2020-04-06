package rip.skyland.commons.cache.types;

import rip.skyland.commons.cache.Cache;

import java.util.List;

public interface ListCache<T> extends Cache<T> {

    List<T> getCache();

}
