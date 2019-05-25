package me.sharpjaws.sharpsk.hooks.Multiverse;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.onarandombox.MultiverseCore.MultiverseCore;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffMVCloneWorld extends Effect {

    private Expression<String> wn;
    private Expression<String> wn2;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
                        SkriptParser.ParseResult paramParseResult) {
        wn = (Expression<String>) expr[0];
        wn2 = (Expression<String>) expr[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return "mv clone world %string% to %string%";
    }

    @Override
    protected void execute(Event e) {

        MultiverseCore mv = null;
        mv = MultiverseCore.getPlugin(MultiverseCore.class);

        mv.getMVWorldManager().cloneWorld(wn.getSingle(e), wn2.getSingle(e));
    }

}
