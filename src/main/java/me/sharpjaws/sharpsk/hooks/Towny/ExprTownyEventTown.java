package me.sharpjaws.sharpsk.hooks.Towny;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.palmergames.bukkit.towny.event.DeleteTownEvent;
import com.palmergames.bukkit.towny.event.NationAddTownEvent;
import com.palmergames.bukkit.towny.event.NationRemoveTownEvent;
import com.palmergames.bukkit.towny.event.NewTownEvent;
import com.palmergames.bukkit.towny.event.RenameTownEvent;
import com.palmergames.bukkit.towny.event.TownAddResidentEvent;
import com.palmergames.bukkit.towny.event.TownClaimEvent;
import com.palmergames.bukkit.towny.event.TownRemoveResidentEvent;
import com.palmergames.bukkit.towny.event.TownUnclaimEvent;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprTownyEventTown extends SimpleExpression<String> {

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean paramBoolean) {
        return "event-town";
    }

    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean arg2, ParseResult result) {
        return ScriptLoader.isCurrentEvent(TownAddResidentEvent.class)
                || ScriptLoader.isCurrentEvent(TownRemoveResidentEvent.class)
                || ScriptLoader.isCurrentEvent(RenameTownEvent.class)
                || ScriptLoader.isCurrentEvent(DeleteTownEvent.class)
                || ScriptLoader.isCurrentEvent(NewTownEvent.class)
                || ScriptLoader.isCurrentEvent(TownClaimEvent.class)
                || ScriptLoader.isCurrentEvent(TownUnclaimEvent.class)
                || ScriptLoader.isCurrentEvent(TownAddResidentEvent.class)
                || ScriptLoader.isCurrentEvent(TownRemoveResidentEvent.class)
                || ScriptLoader.isCurrentEvent(NationAddTownEvent.class)
                || ScriptLoader.isCurrentEvent(NationRemoveTownEvent.class);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        switch (e.getEventName()) {
            case "TownAddResidentEvent":
                return new String[]{((TownAddResidentEvent) e).getTown().getName()};
            case "TownRemoveResidentEvent":
                return new String[]{((TownRemoveResidentEvent) e).getTown().getName()};
            case "RenameTownEvent":
                return new String[]{((RenameTownEvent) e).getTown().getName()};
            case "DeleteTownEvent":
                return new String[]{((DeleteTownEvent) e).getTownName()};
            case "NewTownEvent":
                return new String[]{((NewTownEvent) e).getTown().getName()};
            case "TownClaimEvent":
                try {
                    return new String[]{((TownClaimEvent) e).getTownBlock().getTown().getName()};
                } catch (NotRegisteredException e1) {
                    return new String[]{};
                }
            case "TownUnclaimEvent":
                return new String[]{((TownUnclaimEvent) e).getTown().getName()};
            case "NationAddTownEvent":
                return new String[]{((NationAddTownEvent) e).getTown().getName()};
            case "NationRemoveTownEvent":
                return new String[]{((NationRemoveTownEvent) e).getTown().getName()};
        }
        return null;
    }

}
