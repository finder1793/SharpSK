package me.sharpjaws.sharpsk.hooks.Towny;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.palmergames.bukkit.towny.exceptions.EmptyTownException;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.TownyUniverse;
import me.sharpjaws.sharpsk.SharpSK;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffTownyKickPlayerFromNation extends Effect {
    private Expression<String> s;
    private Expression<OfflinePlayer> p;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
                        SkriptParser.ParseResult paramParseResult) {
        p = (Expression<OfflinePlayer>) expr[0];
        s = (Expression<String>) expr[1];

        return true;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "[towny] kick %offlineplayer% from [town] %string%";
    }

    @Override
    protected void execute(Event e) {
        SharpSK core = SharpSK.instance;

        try {
            TownyUniverse.getDataSource().getTown(s.getSingle(e))
                    .removeResident(TownyUniverse.getDataSource().getResident(p.getSingle(e).getName()));
        } catch (EmptyTownException ignored) {

        } catch (NotRegisteredException ex2) {
            core.getLogger().warning("Could not kick resident: " + "\"" + p.getSingle(e).getName() + "\""
                    + " from town " + "\"" + s.getSingle(e) + "\"");
            core.getLogger().warning("Resident is not in town: " + "\"" + s.getSingle(e) + "\"");
        }

    }
}
