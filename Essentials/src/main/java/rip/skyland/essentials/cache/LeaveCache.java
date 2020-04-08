package rip.skyland.essentials.cache;

import lombok.Getter;
import rip.skyland.commons.cache.types.ListCache;
import rip.skyland.commons.util.Locale;
import rip.skyland.commons.util.MethodUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class LeaveCache implements ListCache<UUID> {

    private final List<UUID> cache = new ArrayList<>();

    @Override
    public void add(UUID element) {
        // add the element to the cache
        this.cache.add(element);

        // remove the element from the cache again
        MethodUtil.executeLater(() -> this.cache.remove(element), Locale.LOGOUT_TIMEOUT);
    }

    @Override
    public Class<UUID> getType() {
        return UUID.class;
    }
}
