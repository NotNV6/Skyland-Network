package rip.skyland.essentials.server;

import lombok.Data;
import rip.skyland.commons.module.Module;
import rip.skyland.essentials.EssentialsPlugin;

@Data
public class ServerDataModule extends Module {

    private final ServerData data;

    public ServerDataModule() {
        this.data = new ServerData(EssentialsPlugin.getPlugin(EssentialsPlugin.class).getConfig().getString("server.server_name"));
    }
}
