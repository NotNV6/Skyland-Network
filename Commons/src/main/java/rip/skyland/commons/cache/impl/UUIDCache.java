package rip.skyland.commons.cache.impl;

import lombok.Data;
import rip.skyland.commons.cache.types.MapCache;

import java.util.Map;
import java.util.UUID;

@Data
public class UUIDCache implements MapCache<String, UUID> {

    private Map<String, UUID> cache;

    @Override
    public Class<UUID> getType() {
        return UUID.class;
    }

    @Override
    public UUID findValue(String key) {
        return this.cache.get(key);
    }
}
