package me.sharpjaws.sharpsk.hooks.AuthmeReloaded;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import fr.xephi.authme.api.v3.AuthMeApi;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class CondAuthIsRegistered extends Condition {
    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean arg2, SkriptParser.ParseResult arg3) {
        player = (Expression<Player>) expr[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "%player% is registered";
    }

    @Override
    public boolean check(Event e) {
        return AuthMeApi.getInstance().isRegistered(player.getSingle(e).getName());

    }
}
