package me.sharpjaws.sharpSK.hooks.Towny;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import com.palmergames.bukkit.towny.exceptions.EmptyNationException;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.TownyUniverse;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.sharpjaws.sharpSK.main;;

public class EffTownyAddPlayerToNation extends Effect {
	
	private Expression<OfflinePlayer> p;
	private Expression<String> nat;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {	
		p = (Expression<OfflinePlayer>) expr[0];

		return true;
	}

	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "[towny] add %offlineplayer% to nation %string%";
	}

	@Override
	protected void execute(Event e) {
		main core = (main)Bukkit.getPluginManager().getPlugin("SharpSK");
		
		
			try {
				TownyUniverse.getDataSource().getResident(p.getSingle(e).getName()).setName(nat.getSingle(e));
			} catch (NotRegisteredException e1) {
				core.getLogger().warning("Could not add resident: "+"\""+ p.getSingle(e).getName()+"\""+" to nation " +"\"" + nat.getSingle(e) +"\"");
				core.getLogger().warning("Nation was not found in town: "+ "\"" + nat.getSingle(e) +"\"");
				return;
			}

	
	}
}	

