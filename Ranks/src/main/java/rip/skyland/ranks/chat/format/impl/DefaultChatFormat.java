package rip.skyland.ranks.chat.format.impl;

import lombok.Data;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.player.PlayerData;
import rip.skyland.commons.player.PlayerDataModule;
import rip.skyland.commons.util.CC;
import rip.skyland.ranks.chat.format.ChatFormat;
import rip.skyland.ranks.chat.format.FormatAddition;
import rip.skyland.ranks.player.GrantData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DefaultChatFormat implements ChatFormat {

    private final List<FormatAddition> formatAdditions = new ArrayList<>();


    @Override
    public String format(Player player, String message) {
        final PlayerDataModule playerDataModule = CommonsPlugin.getInstance().getHandler().findModule(PlayerDataModule.class);
        final PlayerData playerData = playerDataModule.findPlayerData(player.getUniqueId());
        final GrantData grantData = playerData.findData(GrantData.class);
        final StringBuilder format = new StringBuilder();

        final List<FormatAddition> additionsBeforePrefix = formatAdditions.stream()
                .filter(addition -> addition.getPosition().equals(FormatAddition.AdditionPosition.BEFORE_PREFIX))
                .collect(Collectors.toList());

        final List<FormatAddition> additionsAfterPrefix = formatAdditions.stream()
                .filter(addition -> addition.getPosition().equals(FormatAddition.AdditionPosition.AFTER_PREFIX))
                .collect(Collectors.toList());

        final List<FormatAddition> additionsAfterName = formatAdditions.stream()
                .filter(addition -> addition.getPosition().equals(FormatAddition.AdditionPosition.AFTER_NAME))
                .collect(Collectors.toList());

        additionsBeforePrefix.forEach(addition -> format.append(addition.getAddition()));
        format.append(CC.translate(grantData.getRank().getPrefix()));
        additionsAfterPrefix.forEach(addition -> format.append(addition.getAddition()));
        format.append(CC.translate(ChatColor.getLastColors(grantData.getRank().getPrefix()) + player.getName()));
        additionsAfterName.forEach(addition -> format.append(addition.getAddition()));

        format.append(ChatColor.GRAY)
                .append(": ")
                .append(ChatColor.WHITE)
                .append(message);

        return format.toString();
    }

    /**
     * Add a FormatAddition
     *
     * @param formatAddition the addition
     */
    public DefaultChatFormat add(FormatAddition formatAddition) {
        this.formatAdditions.add(formatAddition);

        return this;
    }
}