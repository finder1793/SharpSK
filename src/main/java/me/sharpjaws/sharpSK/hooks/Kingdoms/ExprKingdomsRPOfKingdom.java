package me.sharpjaws.sharpSK.hooks.Kingdoms;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.kingdoms.constants.kingdom.Kingdom;
import org.kingdoms.manager.game.GameManagement;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

public class ExprKingdomsRPOfKingdom extends SimpleExpression<Number> {
	private Expression<String> kingdom;

	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		kingdom = (Expression<String>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "[kingdoms] (RP|resource[ ]points) of [kingdom] %string%";
	}

	@Override
	@Nullable
	protected Number[] get(Event e) {
		Kingdom kdm = null;
		try {

			kdm = GameManagement.getKingdomManager().getOrLoadKingdom(kingdom.getSingle(e));
			return new Number[] { kdm.getResourcepoints() };
		} catch (NullPointerException ex) {
			return new Number[] { 0 };
		}

	}

	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.SET) {
			try {
				Kingdom kdm = GameManagement.getKingdomManager().getOrLoadKingdom(kingdom.getSingle(e));
				kdm.setResourcepoints(((Number) delta[0]).intValue());

			} catch (NullPointerException ex) {
				return;
			}
		}
		if (mode == Changer.ChangeMode.ADD) {
			try {
				Kingdom kdm = GameManagement.getKingdomManager().getOrLoadKingdom(kingdom.getSingle(e));
				kdm.setResourcepoints(kdm.getResourcepoints() + ((Number) delta[0]).intValue());

			} catch (NullPointerException ex) {
				return;
			}
		}
		if (mode == Changer.ChangeMode.REMOVE) {
			try {
				Kingdom kdm = GameManagement.getKingdomManager().getOrLoadKingdom(kingdom.getSingle(e));
				kdm.setResourcepoints(kdm.getResourcepoints() - ((Number) delta[0]).intValue());

			} catch (NullPointerException ex) {
				return;
			}
		}
	}

	@Override
	public Class<?>[] acceptChange(Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.SET) {
			return CollectionUtils.array(new Class[] { Number.class });
		} else if (mode == Changer.ChangeMode.ADD) {
			return CollectionUtils.array(new Class[] { Number.class });
		} else if (mode == Changer.ChangeMode.REMOVE) {
			return CollectionUtils.array(new Class[] { Number.class });
		}
		return null;
	}

}
