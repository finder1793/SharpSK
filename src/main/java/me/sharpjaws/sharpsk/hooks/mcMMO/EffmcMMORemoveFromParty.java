package me.sharpjaws.sharpsk.hooks.mcMMO;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.gmail.nossr50.api.PartyAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffmcMMORemoveFromParty extends Effect {
    private Expression<Player> p;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
                        SkriptParser.ParseResult paramParseResult) {
        p = (Expression<Player>) expr[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "[mcmmo] (kick|remove) %player% from (its [own]|own) party";
    }

    @Override
    protected void execute(Event e) {
        PartyAPI.removeFromParty(p.getSingle(e));
    }
}
