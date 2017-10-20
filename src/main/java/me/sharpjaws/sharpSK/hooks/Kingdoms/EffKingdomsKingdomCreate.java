package me.sharpjaws.sharpSK.hooks.Kingdoms;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.kingdoms.manager.game.GameManagement;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

public class EffKingdomsKingdomCreate extends Effect {
	private Expression<String> k;
	private Expression<Player> p;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {
		k = (Expression<String>) expr[0];
		p = (Expression<Player>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "[sharpsk] [kingdoms] create kingdom %string% [with] king %player%";
	}

	@Override
	protected void execute(Event e) {

		try {
			GameManagement.getKingdomManager().createNewKingdom(k.getSingle(e),
					GameManagement.getPlayerManager().getOfflineKingdomPlayer(p.getSingle(e)).getKingdomPlayer());
		} catch (NullPointerException ex) {
			return;

		}
	}
}
