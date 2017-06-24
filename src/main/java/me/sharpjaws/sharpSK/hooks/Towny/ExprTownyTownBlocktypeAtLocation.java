package me.sharpjaws.sharpSK.hooks.Towny;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.event.Event;

import com.palmergames.bukkit.towny.object.TownBlockType;
import com.palmergames.bukkit.towny.object.TownyUniverse;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprTownyTownBlocktypeAtLocation extends SimpleExpression<TownBlockType> {
private Expression<Location> loc;
	
	@Override
	public Class<? extends TownBlockType> getReturnType() {
		return TownBlockType.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "[sharpsk] [towny] [town] (block|plot)type at %location%";
	}


	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean arg2, ParseResult result) {
		loc = (Expression<Location>) expr[0];
			return true ;		
	}

	@Override
	@Nullable
	protected TownBlockType[] get(Event e) {
		return new TownBlockType[] {TownyUniverse.getTownBlock(loc.getSingle(e)).getType()}; 
	}

}