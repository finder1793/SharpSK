package me.sharpjaws.sharpsk.hooks.Kingdoms;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.kingdoms.constants.kingdom.Kingdom;
import org.kingdoms.manager.game.GameManagement;

import javax.annotation.Nullable;

public class ExprKingdomsKingOfKingdom extends SimpleExpression<OfflinePlayer> {

    private GameManagement kman;
    private Expression<String> kingdom;

    @Override
    public Class<? extends OfflinePlayer> getReturnType() {
        return OfflinePlayer.class;
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
        return "[kingdoms] king of [kingdom] %string%";
    }

    @Override
    @Nullable
    protected OfflinePlayer[] get(Event e) {
        OfflinePlayer king = null;
        try {
            Kingdom kdm = GameManagement.getKingdomManager().getOrLoadKingdom(kingdom.getSingle(e));
            king = Bukkit.getOfflinePlayer(kdm.getKing());
        } catch (NullPointerException ex) {
            return new OfflinePlayer[]{};
        }

        return new OfflinePlayer[]{king};
    }

}
