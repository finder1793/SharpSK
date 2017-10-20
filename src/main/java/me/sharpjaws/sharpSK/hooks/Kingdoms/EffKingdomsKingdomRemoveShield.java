package me.sharpjaws.sharpSK.hooks.Kingdoms;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.kingdoms.manager.game.GameManagement;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

public class EffKingdomsKingdomRemoveShield extends Effect {
	private Expression<String> k;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {
		k = (Expression<String>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "[sharpsk] [kingdoms] remove shield from kingdom %string%";
	}

	@Override
	protected void execute(Event e) {

		try {
			GameManagement.getKingdomManager().getOrLoadKingdom(k.getSingle(e)).giveShield();
		} catch (NullPointerException ex) {
			return;

		}
	}
}
