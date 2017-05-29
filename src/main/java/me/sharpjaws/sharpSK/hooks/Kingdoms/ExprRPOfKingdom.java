package me.sharpjaws.sharpSK.hooks.Kingdoms;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.kingdoms.constants.kingdom.Kingdom;
import org.kingdoms.manager.game.GameManagement;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprRPOfKingdom extends SimpleExpression<Number>{

	private GameManagement kman;
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
		int rp = 0;
		try {
			
			kdm = GameManagement.getKingdomManager().getOrLoadKingdom(kingdom.getSingle(e));
			rp = kdm.getResourcepoints();
		}catch(NullPointerException ex) {
			return new Number[] {0};
		}
		
		
		
		
		return new Number[] {rp};
	}

}
