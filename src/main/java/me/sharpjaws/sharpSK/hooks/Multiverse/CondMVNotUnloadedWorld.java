package me.sharpjaws.sharpSK.hooks.Multiverse;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;


public class CondMVNotUnloadedWorld extends Condition{
	
	private Expression<String> w;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		w = (Expression<String>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return "(mv|multiverse) world %string% is unloaded";
	}

	@Override
	public boolean check(Event e) {
		MultiverseCore mv = null;		
		mv = MultiverseCore.getPlugin(MultiverseCore.class);
	
		if (mv.getMVWorldManager().hasUnloadedWorld(w.getSingle(e), false)){
		return false;
		}else{
			return true;
		}
	}

}
