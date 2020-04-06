package rip.skyland.ranks.commands.rank.adapter;

import org.bukkit.command.CommandSender;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.command.adapter.TypeAdapter;
import rip.skyland.ranks.ranks.Rank;
import rip.skyland.ranks.ranks.RankModule;

public class RankTypeAdapter implements TypeAdapter<Rank> {

    private final RankModule rankModule = CommonsPlugin.getInstance().getHandler().findModule(RankModule.class);

    @Override
    public Rank convert(CommandSender sender, String source) {
        return rankModule.findRank(source);
    }

    @Override
    public Class<Rank> getType() {
        return Rank.class;
    }
}
