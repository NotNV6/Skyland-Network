package rip.skyland.ranks.chat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import rip.skyland.commons.module.Module;
import rip.skyland.ranks.chat.format.ChatFormat;


@Data
public class ChatModule extends Module {

    private ChatHandler chatHandler;
    private ChatFormat format;

    public ChatModule(ChatFormat format) {
        this.format = format;
    }

}