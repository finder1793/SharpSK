package me.sharpjaws.sharpsk.hooks.Kingdoms;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.kingdoms.manager.game.GameManagement;

import javax.annotation.Nullable;

public class EffKingdomsKingdomMakeAlly extends Effect {
	private Expression<String> k;
	private Expression<String> k2;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {
		k = (Expression<String>) expr[0];
		k2 = (Expression<String>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "[sharpsk] [kingdoms] make kingdom %string% ally of kingdom %string%";
	}

	@Override
	protected void execute(Event e) {
		try {
			GameManagement.getKingdomManager().getOrLoadKingdom(k.getSingle(e))
					.addAlly(GameManagement.getKingdomManager().getOrLoadKingdom(k2.getSingle(e)).getKingdomName());
		} catch (NullPointerException ex) {
			return;

		}
	}
}
