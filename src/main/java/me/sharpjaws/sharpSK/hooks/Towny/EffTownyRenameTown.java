package me.sharpjaws.sharpSK.hooks.Towny;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import com.palmergames.bukkit.towny.exceptions.AlreadyRegisteredException;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.TownyUniverse;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.sharpjaws.sharpSK.main;;

public class EffTownyRenameTown extends Effect {
	private Expression<String> s;
	private Expression<String> s2;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {
		s = (Expression<String>) expr[0];
		s2 = (Expression<String>) expr[1];

		return true;
	}

	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "[towny] rename town %string% to %string%";
	}

	@Override
	protected void execute(Event e) {
		main core = (main) Bukkit.getPluginManager().getPlugin("SharpSK");

		try {
			try {
				TownyUniverse.getDataSource().renameTown(TownyUniverse.getDataSource().getTown(s.getSingle(e)),
						s2.getSingle(e));
			} catch (AlreadyRegisteredException e1) {
				core.getLogger().warning(
						"Could not rename town: " + "\"" + s.getSingle(e) + "\"" + " Town name already in use.");
			}
		} catch (NotRegisteredException ex) {
			core.getLogger().warning("Could not rename town: " + "\"" + s.getSingle(e) + "\"" + " Town does not exist");
			return;
		}

	}
}
