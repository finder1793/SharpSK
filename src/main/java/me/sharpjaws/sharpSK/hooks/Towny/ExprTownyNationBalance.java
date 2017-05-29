package me.sharpjaws.sharpSK.hooks.Towny;

import java.util.ArrayList;

import javax.annotation.Nullable;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.palmergames.bukkit.towny.exceptions.EconomyException;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownyUniverse;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprTownyNationBalance extends SimpleExpression<Number> {

	
	
	private Expression<String> nation;

	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult Result) {
		return true;
	}
	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "[sharpsk] [towny] nation balance of nation %string%";
	}


	@Override
	@Nullable
	protected Number[] get(Event e) {
	try {
		return new Number[] {TownyUniverse.getDataSource().getNation(nation.getSingle(e)).getHoldingBalance()};
	} catch (NotRegisteredException e1) {
		return new Number[]{0};
	} catch (EconomyException e1) {
	return new Number[]{0};
	}	
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	

}


