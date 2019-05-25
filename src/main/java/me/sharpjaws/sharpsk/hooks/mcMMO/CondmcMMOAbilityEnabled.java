package me.sharpjaws.sharpsk.hooks.mcMMO;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.gmail.nossr50.datatypes.skills.AbilityType;
import com.gmail.nossr50.util.player.UserManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class CondmcMMOAbilityEnabled extends Condition {
    private Expression<OfflinePlayer> p;
    private Expression<AbilityType> ability;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean arg2, SkriptParser.ParseResult arg3) {
        p = (Expression<OfflinePlayer>) expr[0];
        ability = (Expression<AbilityType>) expr[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "%abilitytype% is enabled";
    }

    @Override
    public boolean check(Event e) {
        if (p == null) {
            return false;
        }
        if (p.getSingle(e).isOnline()) {
            return UserManager.getPlayer(p.getSingle(e).getPlayer()).getAbilityMode(ability.getSingle(e));
        } else {
            return UserManager.getOfflinePlayer(p.getSingle(e)).getAbilityMode(ability.getSingle(e));
        }
    }
}
