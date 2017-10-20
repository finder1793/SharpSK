package me.sharpjaws.sharpSK.Expressions;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprEventAnvilCost extends SimpleExpression<Number> {

	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2, ParseResult arg3) {
		if (!ScriptLoader.isCurrentEvent(PrepareAnvilEvent.class)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "anvil-cost";
	}

	@Override
	@Nullable
	protected Number[] get(Event e) {
		try {
			AnvilInventory a = ((PrepareAnvilEvent) e).getInventory();
			return new Number[] { a.getRepairCost() };
		} catch (NullPointerException ex) {
			return new Number[] { 0 };
		}
	}

}
