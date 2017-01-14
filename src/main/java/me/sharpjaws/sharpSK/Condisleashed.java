package me.sharpjaws.sharpSK;

import javax.annotation.Nullable;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

public class Condisleashed extends Condition {
	private Expression<Entity> en;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean arg2, SkriptParser.ParseResult arg3) {
		en = (Expression<Entity>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "%entity% is leashed";
	}

	
	
	@Override
	public boolean check(Event e) {
	
		
		Boolean check = false;
		try {
		LivingEntity en2 = (LivingEntity) en.getSingle(e);
		if (en2.isLeashed() == true) {
			check = true;
		} else {
			check = false;
		}
		}catch (NullPointerException ex){	
		}
	return check;
	}
	
}
