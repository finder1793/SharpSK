package me.sharpjaws.sharpsk.hooks.Kingdoms;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.kingdoms.manager.game.GameManagement;

import javax.annotation.Nullable;

class ExprKingdomsKingdomOfPlayer extends SimpleExpression<String> {
	private Expression<OfflinePlayer> p;

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		p = (Expression<OfflinePlayer>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "[sharpsk] [kingdoms] [kingdom] of %offlineplayer%";
	}

	@Override
	@Nullable
	protected String[] get(Event e) {
		String kingdom = null;
		try {
			kingdom = GameManagement.getPlayerManager().getOfflineKingdomPlayer(p.getSingle(e)).getKingdomName();
		} catch (NullPointerException ex) {
			return null;
		}

		return new String[] { kingdom };
	}

}
