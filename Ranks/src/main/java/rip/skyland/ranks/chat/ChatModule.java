package rip.skyland.ranks.chat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import rip.skyland.commons.module.Module;
import rip.skyland.ranks.chat.format.ChatFormat;


@EqualsAndHashCode(callSuper = true)
@Data
public class ChatModule extends Module {

    private ChatHandler chatHandler;
    private final ChatFormat format;

}