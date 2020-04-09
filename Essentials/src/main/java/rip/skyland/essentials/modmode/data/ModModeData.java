package rip.skyland.essentials.modmode.data;

import lombok.Getter;
import lombok.Setter;
import rip.skyland.commons.player.data.Data;
import rip.skyland.essentials.modmode.cache.CachedPlayerData;

@Getter
@Setter
public class ModModeData extends Data {

    private boolean modModeEnabled;
    private boolean canDestroyBlocks;

    private CachedPlayerData cachedPlayerData;

}
