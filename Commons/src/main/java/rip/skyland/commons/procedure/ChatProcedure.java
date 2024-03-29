package rip.skyland.commons.procedure;

import lombok.Data;
import org.bukkit.entity.Player;
import rip.skyland.commons.CommonsPlugin;

@Data
public abstract class ChatProcedure {

    private final Player player;

    /**
     * Constructor for creating a new ChatProcedure
     *
     * @param player the player
     */
    public ChatProcedure(Player player) {
        this.player = player;
    }

    /**
     * Start the procedure
     */
    public void start() {
        final ChatProcedureModule module = CommonsPlugin.getInstance().getHandler().findModule(ChatProcedureModule.class);

        module.getProcedures().put(player, this);
    }

    /**
     * Finish the procedure
     *
     * @param result the result
     */
    public void finish(String result) {
        final ChatProcedureModule module = CommonsPlugin.getInstance().getHandler().findModule(ChatProcedureModule.class);

        module.getProcedures().remove(player);
    }
}