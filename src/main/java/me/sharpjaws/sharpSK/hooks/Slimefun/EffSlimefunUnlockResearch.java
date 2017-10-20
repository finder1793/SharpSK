package me.sharpjaws.sharpSK.hooks.Slimefun;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.mrCookieSlime.Slimefun.Objects.Research;

public class EffSlimefunUnlockResearch extends Effect {
	private Expression<String> rs;
	private Expression<Player> pl;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		rs = (Expression<String>) expr[0];
		pl = (Expression<Player>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "[sharpsk] [(slimefun|sf)] unlock research %string%";
	}

	@Override
	protected void execute(Event e) {
		for (Research res : Research.list()) {
			if (res.getName().replaceAll(" ", "_").equalsIgnoreCase(rs.getSingle(e))) {
				if (!res.hasUnlocked(pl.getSingle(e))) {
					res.unlock(pl.getSingle(e), true);
				}
				break;
			}

		}
	}

}
