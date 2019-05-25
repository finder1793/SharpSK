package me.sharpjaws.sharpsk.hooks.Kingdoms;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.event.Event;
import org.kingdoms.constants.kingdom.Kingdom;
import org.kingdoms.manager.game.GameManagement;

import javax.annotation.Nullable;

public class ExprKingdomsMightOfKingdom extends SimpleExpression<Number> {
    private Expression<String> kingdom;

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
        kingdom = (Expression<String>) expr[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return "[sharpsk] [kingdoms] might of [kingdom] %string%";
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        Kingdom kdm = null;
        try {

            kdm = GameManagement.getKingdomManager().getOrLoadKingdom(kingdom.getSingle(e));
        } catch (NullPointerException ex) {
            return new Number[] { 0 };
        }

        return new Number[] { kdm.getMight() };
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            try {
                Kingdom kdm = GameManagement.getKingdomManager().getOrLoadKingdom(kingdom.getSingle(e));
                kdm.setMight((int) delta[0]);

            } catch (NullPointerException ex) {
                return;
            }
        }
        if (mode == Changer.ChangeMode.ADD) {
            try {
                Kingdom kdm = GameManagement.getKingdomManager().getOrLoadKingdom(kingdom.getSingle(e));
                kdm.setMight(kdm.getMight() + (int) delta[0]);

            } catch (NullPointerException ex) {
                return;
            }
        }
        if (mode == Changer.ChangeMode.REMOVE) {
            try {
                Kingdom kdm = GameManagement.getKingdomManager().getOrLoadKingdom(kingdom.getSingle(e));
                kdm.setMight(kdm.getMight() - (int) delta[0]);

            } catch (NullPointerException ignored) {
            }
        }
    }

    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Number.class);
        } else if (mode == Changer.ChangeMode.ADD) {
            return CollectionUtils.array(Number.class);
        } else if (mode == Changer.ChangeMode.REMOVE) {
            return CollectionUtils.array(Number.class);
        }
        return null;
    }

}
