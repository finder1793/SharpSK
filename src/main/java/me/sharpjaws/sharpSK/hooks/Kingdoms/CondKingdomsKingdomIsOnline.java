package me.sharpjaws.sharpSK.hooks.Kingdoms;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import org.kingdoms.manager.game.GameManagement;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class CondKingdomsKingdomIsOnline extends Condition {
	
private Expression<String> kingdom;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult result) {
		kingdom = (Expression<String>) expr[0];
		setNegated(result.mark == 1);
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "kingdom %string% is [not] online";
	}

	@Override
	public boolean check(Event e) {
		Boolean a = isNegated();
		if (!a){
		return GameManagement.getKingdomManager().isOnline(kingdom.getSingle(e));
		}else{
			return !GameManagement.getKingdomManager().isOnline(kingdom.getSingle(e));	
		}
	}

}
