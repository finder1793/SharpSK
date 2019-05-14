package me.sharpjaws.sharpsk.hooks.Towny;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.palmergames.bukkit.towny.exceptions.AlreadyRegisteredException;
import com.palmergames.bukkit.towny.exceptions.EconomyException;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.*;
import me.sharpjaws.sharpsk.SharpSK;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffTownyCreateTown extends Effect {
	private Expression<String> s;
	private Expression<Number> sb;
	private Expression<Location> homespawn;
	private Expression<OfflinePlayer> owner;
	private Expression<OfflinePlayer> members;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {
		s = (Expression<String>) expr[0];
		homespawn = (Expression<Location>) expr[1];
		sb = (Expression<Number>) expr[2];
		owner = (Expression<OfflinePlayer>) expr[3];
		members = (Expression<OfflinePlayer>) expr[4];

		return true;
	}

	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "[towny] create town %string% at %location% [with [starting] balance %-number%] [[and] with mayor %-offlineplayer%] [and residents %-offlineplayers%]";
	}

	@Override
	protected void execute(Event e) {
		SharpSK core = SharpSK.instance;

		// Town Generator

		try {

			TownyWorld world = TownyUniverse.getDataSource().getWorld(homespawn.getSingle(e).getWorld().getName());
			Coord loc = Coord.parseCoord(homespawn.getSingle(e));

			world.newTownBlock(loc);
			TownyUniverse.getDataSource().newTown(s.getSingle(e));
			Town town = TownyUniverse.getDataSource().getTown(s.getSingle(e));
			if (owner != null) {
				Resident resident = TownyUniverse.getDataSource().getResident(owner.getSingle(e).getName());
				town.addResident(resident);
				town.setMayor(resident);
				TownyUniverse.getDataSource().saveResident(resident);
			}
			if (members != null) {

				for (OfflinePlayer member : members.getAll(e)) {
					Resident loopresident = TownyUniverse.getDataSource().getResident(member.getName());
					town.addResident(loopresident);
					TownyUniverse.getDataSource().saveResident(loopresident);
				}

			}

			TownBlock TB = world.getTownBlock(loc);
			TB.setTown(town);
			town.setHomeBlock(TB);

			TB.setType(TB.getType());
			town.setSpawn(homespawn.getSingle(e));
			if (sb != null) {
				town.setBalance(sb.getSingle(e).doubleValue(), "Town Creation");
			} else {
				town.setBalance(0, "Town Creation");
			}

			TownyUniverse.getDataSource().saveTownBlock(TB);
			TownyUniverse.getDataSource().saveTown(town);
			TownyUniverse.getDataSource().saveWorld(world);

			TownyUniverse.getDataSource().saveTownList();
			TownyUniverse.getDataSource().saveTownBlockList();

		} catch (NotRegisteredException ex1) {
			core.getLogger().warning("Could not register town: " + "\"" + s.getSingle(e) + "\"");
		} catch (AlreadyRegisteredException ex2) {
			core.getLogger()
					.warning("Could not register town: " + "\"" + s.getSingle(e) + "\"" + ". Town already exists");
		} catch (TownyException ex3) {
			core.getLogger().warning("Could not register town: " + "\"" + s.getSingle(e) + "\"");
		} catch (EconomyException ex4) {
			core.getLogger().warning("Could not register town: " + "\"" + s.getSingle(e) + "\"");
		}

	}

}
