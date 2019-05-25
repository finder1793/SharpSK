package me.sharpjaws.sharpsk.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.World;
import org.bukkit.event.Event;

public class EffSaveWorlds extends Effect {
    private Expression<?> worlds;

    public boolean init(Expression<?>[] expresion, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.worlds = expresion[0];
        return true;
    }

    public String toString(Event e, boolean debug) {
        return "save world(s)";
    }

    protected void execute(Event event) {
        Object[] arrayOfObject;
        int j = (arrayOfObject = this.worlds.getArray(event)).length;
        for (int i = 0; i < j; i++) {
            Object world = arrayOfObject[i];
            if ((world instanceof World)) {
                ((World) world).save();
            }
        }
    }
}
