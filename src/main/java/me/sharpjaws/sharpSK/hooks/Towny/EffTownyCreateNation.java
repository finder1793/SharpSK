package me.sharpjaws.sharpSK.hooks.Towny;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.palmergames.bukkit.towny.exceptions.AlreadyRegisteredException;
import com.palmergames.bukkit.towny.exceptions.EconomyException;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownyUniverse;
import me.sharpjaws.sharpSK.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffTownyCreateNation extends Effect {
	private Expression<String> nat;
	private Expression<String> tow;
	private Expression<Number> bal;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {
		nat = (Expression<String>) expr[0];
		tow = (Expression<String>) expr[1];
		bal = (Expression<Number>) expr[2];

		return true;
	}

	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "[towny] create nation %string% in town %string% [with [bank] balance %-number%]";
	}

	@Override
	protected void execute(Event e) {
		Main core = (Main) Bukkit.getPluginManager().getPlugin("SharpSK");

		// Nation Generator

		try {

			TownyUniverse.getDataSource().newNation(nat.getSingle(e));
			Nation nation = TownyUniverse.getDataSource().getNation(nat.getSingle(e));
			Town town = TownyUniverse.getDataSource().getTown(tow.getSingle(e));
			nation.addTown(town);
			nation.setCapital(town);
			if (bal != null) {
				nation.setBalance(bal.getSingle(e).doubleValue(), "Nation Creation");
			} else {
				nation.setBalance(0, "Nation Creation");
			}
			TownyUniverse.getDataSource().saveTown(town);
			TownyUniverse.getDataSource().saveNation(nation);
			TownyUniverse.getDataSource().saveNationList();

		} catch (NotRegisteredException ex1) {
			core.getLogger().warning("Could not register nation: " + "\"" + nat.getSingle(e) + "\"");
		} catch (AlreadyRegisteredException ex2) {
			core.getLogger().warning(
					"Could not register nation: " + "\"" + nat.getSingle(e) + "\"" + ". Nation already exists in town");
		} catch (EconomyException ex3) {
			core.getLogger().warning("Could not register nation: " + "\"" + nat.getSingle(e) + "\"");
		}

	}

}
