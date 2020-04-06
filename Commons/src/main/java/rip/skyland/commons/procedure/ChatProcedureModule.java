package rip.skyland.commons.procedure;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bukkit.entity.Player;
import rip.skyland.commons.module.Module;

import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class ChatProcedureModule extends Module {
    private final Map<Player, ChatProcedure> procedures = new HashMap<>();
}