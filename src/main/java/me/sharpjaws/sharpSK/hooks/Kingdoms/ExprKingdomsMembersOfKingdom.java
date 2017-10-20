package me.sharpjaws.sharpSK.hooks.Kingdoms;

import java.util.ArrayList;
import java.util.UUID;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.kingdoms.manager.game.GameManagement;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprKingdomsMembersOfKingdom extends SimpleExpression<OfflinePlayer> {
	private Expression<String> kingdom;
	private GameManagement kman;

	@Override
	public Class<? extends OfflinePlayer> getReturnType() {
		return OfflinePlayer.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult Result) {
		kingdom = (Expression<String>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "[sharpsk] [kingdoms] members (of|in) kingdom %string%";
	}

	@Override
	@Nullable
	protected OfflinePlayer[] get(Event e) {

		ArrayList<OfflinePlayer> narr = new ArrayList<OfflinePlayer>();
		for (UUID u : GameManagement.getKingdomManager().getOfflineKingdom(kingdom.getSingle(e)).getMembersList()) {

			narr.add(Bukkit.getOfflinePlayer(u));
		}

		return narr.toArray(new OfflinePlayer[narr.size()]);

	}

	@Override
	public boolean isSingle() {
		return false;
	}

}
