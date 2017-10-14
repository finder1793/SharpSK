package me.sharpjaws.sharpSK.hooks.Towny;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import com.palmergames.bukkit.towny.exceptions.EconomyException;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.TownyUniverse;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

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
		nation = (Expression<String>) expr[0];
		return true;
	}
	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "[sharpsk] [towny] balance of nation %string%";
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
	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.SET) {
			try {
				TownyUniverse.getDataSource().getNation(nation.getSingle(e)).setBalance(((Number)delta[0]).doubleValue(), null);
				
			}catch (NullPointerException ex){
				ex.printStackTrace();
				return;
			} catch (NotRegisteredException ex2) {		
				ex2.printStackTrace();
				return;
			} catch (EconomyException ex3) {
				ex3.printStackTrace();
			return;
			}
		}
	}
	@Override
	public Class<?>[] acceptChange(Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.SET) {
			return CollectionUtils.array(new Class[] { Number.class });
	}
		return null;
	}

	

}


