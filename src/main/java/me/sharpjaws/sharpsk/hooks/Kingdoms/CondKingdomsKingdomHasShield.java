package me.sharpjaws.sharpsk.hooks.Kingdoms;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.kingdoms.manager.game.GameManagement;

import javax.annotation.Nullable;

public class CondKingdomsKingdomHasShield extends Condition {

    private Expression<String> kingdom;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult result) {
        kingdom = (Expression<String>) expr[0];
        setNegated(result.mark == 1);
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return "[sharpsk] [kingdoms] kingdom %string% (0¦has|1¦doesn[']t (have|has)) [a] shield";
    }

    @Override
    public boolean check(Event e) {
        boolean a = isNegated();
        try {
            if (!a) {
                return GameManagement.getKingdomManager().getOrLoadKingdom(kingdom.getSingle(e)).isShieldUp();
            } else {
                return !GameManagement.getKingdomManager().getOrLoadKingdom(kingdom.getSingle(e)).isShieldUp();
            }

        } catch (NullPointerException ex) {
            return false;

        }
    }
}
