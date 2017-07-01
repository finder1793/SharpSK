package me.sharpjaws.sharpSK.hooks.Kingdoms;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.kingdoms.constants.kingdom.Kingdom;
import org.kingdoms.manager.game.GameManagement;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprKingOfKingdom extends SimpleExpression<String>{

	private GameManagement kman;
	private Expression<String> kingdom;
	
	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
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
		return "[kingdoms] king of [kingdom] %string%";
	}

	@Override
	@Nullable
	protected String[] get(Event e) {
		String king = null;
		try {
		Kingdom kdm = GameManagement.getKingdomManager().getOrLoadKingdom(kingdom.getSingle(e));
		king = kdm.getKingName();
		}catch(NullPointerException ex) {
			
		}
		
		
		
		return new String[] {king};
	}

}
