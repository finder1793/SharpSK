package me.sharpjaws.sharpSK.hooks.Towny;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.TownyUniverse;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.sharpjaws.sharpSK.main;;

public class EffTownyDeleteTown extends Effect {
	private Expression<String> s;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {
		s = (Expression<String>) expr[0];

		return true;
	}

	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "[towny] delete town %string%";
	}

	@Override
	protected void execute(Event e) {
		main core = (main) Bukkit.getPluginManager().getPlugin("SharpSK");

		try {
			TownyUniverse.getDataSource().removeTown(TownyUniverse.getDataSource().getTown(s.getSingle(e)));
		} catch (NotRegisteredException e1) {
			core.getLogger().warning("Could not delete town: " + "\"" + s.getSingle(e) + "\"" + " Town does not exist");
			return;
		}

	}
}
