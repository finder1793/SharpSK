package me.sharpjaws.sharpsk.hooks.Kingdoms;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.kingdoms.manager.game.GameManagement;

import javax.annotation.Nullable;

class EffKingdomsKingdomAddMember extends Effect {
	private Expression<String> k;
	private Expression<OfflinePlayer> p;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {
		k = (Expression<String>) expr[0];
		p = (Expression<OfflinePlayer>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "[sharpsk] [kingdoms] add member %offlineplayer% to kingdom %string%";
	}

	@Override
	protected void execute(Event e) {
		try {
			GameManagement.getKingdomManager().getOrLoadKingdom(k.getSingle(e)).addMember(p.getSingle(e).getUniqueId());
		} catch (NullPointerException ignored) {

        }
	}
}
