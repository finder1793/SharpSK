package me.sharpjaws.sharpsk.hooks.Kingdoms;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.kingdoms.constants.kingdom.OfflineKingdom;
import org.kingdoms.manager.game.GameManagement;

import javax.annotation.Nullable;
import java.util.ArrayList;

class ExprKingdomsAllKingdoms extends SimpleExpression<String> {
	private GameManagement kman;

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult Result) {
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "groups of %player%/%offlineplayer%";
	}

	@Override
	@Nullable
	protected String[] get(Event e) {

		ArrayList<String> narr = new ArrayList<>();
		for (OfflineKingdom k : GameManagement.getKingdomManager().getKingdomList().values()) {

			narr.add(k.getKingdomName());
		}

		return narr.toArray(new String[0]);

	}

	@Override
	public boolean isSingle() {
		return false;
	}

}
