package rip.skyland.essentials.server;

import lombok.Data;

@Data
public class ServerData {

    private final String serverName;

    public ServerData(String serverName) {
        this.serverName = serverName;
    }
}
