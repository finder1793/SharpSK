package me.sharpjaws.sharpsk.hooks.mcMMO;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.gmail.nossr50.api.ChatAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffmcMMOPartyMsg extends Effect {
    private Expression<String> s;
    private Expression<String> s2;
    private Expression<String> s3;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
                        SkriptParser.ParseResult paramParseResult) {
        s = (Expression<String>) expr[0];
        s2 = (Expression<String>) expr[1];
        s3 = (Expression<String>) expr[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "[mcmmo] send %string% to party %string%";
    }

    @Override
    protected void execute(Event e) {
        try {
            ChatAPI.sendPartyChat(Bukkit.getPluginManager().getPlugin("SharpSK"), s3.getSingle(e), s2.getSingle(e),
                    s.getSingle(e));
        } catch (NullPointerException ignored) {
        }
    }
}
