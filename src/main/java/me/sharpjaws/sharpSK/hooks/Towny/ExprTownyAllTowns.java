package me.sharpjaws.sharpSK.hooks.Towny;

import java.util.ArrayList;

import javax.annotation.Nullable;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownyUniverse;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprTownyAllTowns extends SimpleExpression<String> {

	
	
	

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult Result) {
		return true;
	}
	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "groups of %player%/%offlineplayer%";
	}


	@Override
	@Nullable
	protected String[] get(Event e) {
		
		
		
		ArrayList<String> narr = new ArrayList<String>(); 
		for (Town a1 : TownyUniverse.getDataSource().getTowns()) {
		
			 narr.add(a1.getName());
		 }
		
		 
		 return narr.toArray(new String[narr.size()]);
		
			}

	@Override
	public boolean isSingle() {
		return false;
	}

	

}


