package me.sharpjaws.sharpsk.hooks.mcMMO;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.gmail.nossr50.events.chat.McMMOPartyChatEvent;
import com.gmail.nossr50.events.party.McMMOPartyLevelUpEvent;
import com.gmail.nossr50.events.party.McMMOPartyXpGainEvent;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprEvtParty extends SimpleExpression<String> {

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
        return "event-[mcmmo]party";
    }

    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean arg2, ParseResult result) {
        return ScriptLoader.isCurrentEvent(McMMOPartyChatEvent.class)
                || ScriptLoader.isCurrentEvent(McMMOPartyLevelUpEvent.class)
                || ScriptLoader.isCurrentEvent(McMMOPartyXpGainEvent.class);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        switch (e.getEventName()) {
            case "McMMOPartyChatEvent":
                return new String[]{((McMMOPartyChatEvent) e).getParty()};
            case "McMMOPartyLevelUpEvent":
                return new String[]{((McMMOPartyLevelUpEvent) e).getParty().getName()};
            case "McMMOPartyXpGainEvent":
                return new String[]{((McMMOPartyXpGainEvent) e).getParty().getName()};
        }
        return null;
    }

}
